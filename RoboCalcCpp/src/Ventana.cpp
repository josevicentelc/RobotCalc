#include "Ventana.h"
#include <windows.h>
#include <gl/gl.h>

LRESULT CALLBACK WindowProc(HWND, UINT, WPARAM, LPARAM);
void EnableOpenGL(HWND hwnd, HDC*, HGLRC*);
void DisableOpenGL(HWND, HDC, HGLRC);

void EnableOpenGL(HWND hwnd, HDC* hDC, HGLRC* hRC)
{
    PIXELFORMATDESCRIPTOR pfd;
    int iFormat;
    *hDC = GetDC(hwnd);
    ZeroMemory(&pfd, sizeof(pfd));

    pfd.nSize = sizeof(pfd);
    pfd.nVersion = 1;
    pfd.dwFlags = PFD_DRAW_TO_WINDOW |
                  PFD_SUPPORT_OPENGL | PFD_DOUBLEBUFFER;
    pfd.iPixelType = PFD_TYPE_RGBA;
    pfd.cColorBits = 24;
    pfd.cDepthBits = 16;
    pfd.iLayerType = PFD_MAIN_PLANE;

    iFormat = ChoosePixelFormat(*hDC, &pfd);

    SetPixelFormat(*hDC, iFormat, &pfd);

    *hRC = wglCreateContext(*hDC);
    wglMakeCurrent(*hDC, *hRC);
}


LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam)
{
    switch (uMsg)
    {
        case WM_CLOSE:
            PostQuitMessage(0);
        break;

        case WM_DESTROY:
            return 0;

        case WM_KEYDOWN:
        {
            switch (wParam)
            {
                case VK_ESCAPE:
                    PostQuitMessage(0);
                break;
            }
        }
        break;

        default:
            return DefWindowProc(hwnd, uMsg, wParam, lParam);
    }

    return 0;
}


void DisableOpenGL (HWND hwnd, HDC hDC, HGLRC hRC)
{
    wglMakeCurrent(NULL, NULL);
    wglDeleteContext(hRC);
    ReleaseDC(hwnd, hDC);
}

//***************************************************************************
//***************************************************************************

void Ventana::setBgColor(float r, float g, float b, float alpha){
    Cred = r;
    Cgreen = g;
    Cblue = b;
    Calpha = alpha;
}


void Ventana::draw(){
        if (PeekMessage(&msg, NULL, 0, 0, PM_REMOVE))
        {
            /* handle or dispatch messages */
            if (msg.message == WM_QUIT)
            {
                bQuit = TRUE;
            }
            else
            {
                TranslateMessage(&msg);
                DispatchMessage(&msg);
            }
        }
        else
        {
            if (openGlEnabled){
                glClearColor(Cred, Cgreen, Cblue, Calpha);
                glClear(GL_COLOR_BUFFER_BIT);
                glPushMatrix();
                glRotatef(theta, 0.0f, theta, 1.0f);
                glBegin(GL_TRIANGLES);
                    glColor3f(1.0f, 0.0f, 0.0f);   glVertex3f(0.0f,   1.0f, 1.0f);
                    glColor3f(0.0f, 1.0f, 0.0f);   glVertex3f(0.87f,  -0.5f, 0.0f);
                    glColor3f(0.0f, 0.0f, 1.0f);   glVertex3f(-0.87f, -0.5f, -1.0f);
                glEnd();
                glPopMatrix();
                SwapBuffers(hDC);
                theta += 0.1f;
            }
            Sleep (1);
        }
}

void Ventana::close(){
    if (openGlEnabled)
        DisableOpenGL(hwnd, hDC, hRC);

    DestroyWindow(hwnd);
}




void Ventana::enabledOpenGl(){
    EnableOpenGL(hwnd, &hDC, &hRC);
    openGlEnabled = true;
}

void Ventana::disableOpenGl(){
    DisableOpenGL(hwnd, hDC, hRC);
}


Ventana::Ventana(HINSTANCE &hInstance, int &nCmdShow, int w, int h, const char* n, const char* cn)
{
    /* register window class */
    wcex.cbSize = sizeof(WNDCLASSEX);
    wcex.style = CS_OWNDC;
    wcex.lpfnWndProc = WindowProc;
    wcex.cbClsExtra = 0;
    wcex.cbWndExtra = 0;
    wcex.hInstance = hInstance;
    wcex.hIcon = LoadIcon(NULL, IDI_APPLICATION);
    wcex.hCursor = LoadCursor(NULL, IDC_ARROW);
    wcex.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
    wcex.lpszMenuName = NULL;
    wcex.lpszClassName = cn;
    wcex.hIconSm = LoadIcon(NULL, IDI_APPLICATION);;


    RegisterClassEx(&wcex);

    /* create main window */
    hwnd = CreateWindowEx(0,
                          cn,
                          n,
                          WS_OVERLAPPEDWINDOW,
                          CW_USEDEFAULT,
                          CW_USEDEFAULT,
                          1024,
                          768,
                          NULL,
                          NULL,
                          hInstance,
                          NULL);

    ShowWindow(hwnd, nCmdShow);



}

Ventana::~Ventana()
{
    //dtor
}
