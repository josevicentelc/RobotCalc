#ifndef VENTANA_H
#define VENTANA_H

#include <windows.h>
#include <string>
#include <gl/gl.h>

#include <iostream>

using namespace std;

class Ventana
{
    public:
        Ventana(HINSTANCE &hInstance, int &nCmdShow, int w, int h, const char*, const char*);
        virtual ~Ventana();
        void draw();
        void close();
        void enabledOpenGl();
        void disableOpenGl();
        void setBgColor(float r, float g, float b, float alpha);


    protected:

    private:
        bool openGlEnabled = false;
        int width;
        int height;

        float Cred = 0.0f;
        float Cgreen = 0.0f;
        float Cblue = 0.0f;
        float Calpha = 0.0f;

        string title;
        string className;
        MSG msg;
        WNDCLASSEX wcex;
        HWND hwnd;
        HDC hDC;
        HGLRC hRC;
        BOOL bQuit = FALSE;
        float theta = 0.0f;

};

#endif // VENTANA_H
