package Viwer;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.Version;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
import sun.awt.Mutex;

/**
 *
 * @author Josev
 */
public class Display extends Thread {

    private Mutex mutex = new Mutex();
    
    private int width = 0;
    
    private int height = 0;
    
    float FondoR = 0.5f;
    float FondoG = 0.5f;
    float FondoB = 0.5f;
    
    String titulo = "";

    private long window;

    private Loader loader = new Loader();

    private List<Modelo> modelos = new ArrayList<Modelo>();
    
    private boolean running = false;
    

    
    public Display(int w, int h, String t){
        width = w;
        height = h;
        titulo = t;
    }

    public void addModel(float[] m){
        while (!running){
            System.out.println("Espero");
            try{sleep(1);}catch(Exception e){}
        }
            
        mutex.lock();
        System.out.println("Añado");
        Modelo model = loader.loadToVao(m);
        System.out.println("Tengo modelo");
        modelos.add(model);
        System.out.println("Modelo añadido");
        mutex.unlock();
    }
    
    private void loop() {
        GL.createCapabilities();
        running = true;
        System.out.println("Runiiiiiing");

        while ( !glfwWindowShouldClose(window) ) {
            mutex.lock();
            System.out.println("1");
            prepare();
            System.out.println("2");
            for (Modelo m:modelos)
                render(m);
            System.out.println("3");
            flush();
            System.out.println("4");
            mutex.unlock();
        }
        closeDisplay();
    }


    
    public void run() {
        init();
	loop();
	glfwFreeCallbacks(window);
	glfwDestroyWindow(window);
	glfwTerminate();
	glfwSetErrorCallback(null).free();
    }

    private void init() {
	GLFWErrorCallback.createPrint(System.err).set();
	if ( !glfwInit() )
		throw new IllegalStateException("Unable to initialize GLFW");
	glfwDefaultWindowHints(); 
	glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); 
	glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); 
	window = glfwCreateWindow(width, height, titulo, NULL, NULL);
	if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

	glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
		glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

	try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); 
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            
            glfwSetWindowPos(
		window,
		(vidmode.width() - pWidth.get(0)) / 2,
		(vidmode.height() - pHeight.get(0)) / 2
		);
	} 
	glfwMakeContextCurrent(window);
	glfwSwapInterval(1);
	glfwShowWindow(window);
        
	}


        private void prepare(){
            glClearColor(FondoR, FondoG, FondoB, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
        }

        private void flush(){
            glfwSwapBuffers(window); 
            glfwPollEvents();
        }
        
        private void render(Modelo m){
            GL30.glBindVertexArray(m.getVaoId());
            GL20.glEnableVertexAttribArray(0);
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, m.getVertexCount());
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
        }
        
        private void closeDisplay(){
            loader.cleanUp();
        }
        
        
	public static Display createDisplay(int w, int h, String title) {
            Display d = new Display(w, h, title);
            d.start();
            return d;
	}
    
    
    
}
