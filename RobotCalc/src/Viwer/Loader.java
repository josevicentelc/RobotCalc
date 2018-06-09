
package Viwer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.openvr.Texture;

import java.nio.*;



/**
 * @author Josev
 */
public class Loader {

    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();

    public void cleanUp(){
        for (int vao:vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for (int vbo:vbos){
            GL15.glDeleteBuffers(vbo);
        }
    }
    
    public Modelo loadToVao(float[] positions){
        int vaoId = createVao();
        storeDataInAttributeList(0, positions);
        unbindVao();
        return new Modelo(vaoId, positions.length/3);
    }
    
    
    public void loadTexture(String file){
        Texture texture = null;
        try{
            //texture = TextureLoader("PNG", new FileInputStream("res/"+file));
            //Texture.
        }catch(Exception e){
            System.err.println("Error cargando la textura: " + file);
        }
    }

    private void setupTextures(String filename) {
      /*  IntBuffer tmp = BufferUtils.createIntBuffer(1);
        GL11.glGenTextures(tmp);
        tmp.rewind();
        try {
            InputStream in = new FileInputStream(filename);
            PNGDecoder decoder = new PNGDecoder(in);

            System.out.println("width=" + decoder.getWidth());
            System.out.println("height=" + decoder.getHeight());

            ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buf.flip();

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, tmp.get(0));
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
                    GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
                    GL11.GL_NEAREST);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 4);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
            int unsigned = (buf.get(0) & 0xff);
            System.out.println(unsigned);
            System.out.println(buf.get(1));
            System.out.println(buf.get(2));
            System.out.println(buf.get(3));

        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Error " + filename + " not found");
        } catch (java.io.IOException e) {
            System.out.println("Error decoding " + filename);
        }
        tmp.rewind();
        return tmp.get(0);*/
    }

    private int createVao(){
        int vaoId = GL30.glGenVertexArrays();
        vaos.add(vaoId);
        GL30.glBindVertexArray(vaoId);
        return vaoId;
    }
    
    private void storeDataInAttributeList(int atributeNumber, float[] data){
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(atributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    private void unbindVao(){
        GL30.glBindVertexArray(0);
    }
    
    
    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
        
    }
}
