#include <windows.h>
#include "GlWindow.h"
#include "Ventana.h"
#include <iostream>

using namespace std;


int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow){

    Ventana a(hInstance, nCmdShow, 400, 400, "Titulo Ventana 1", "clase1");
    a.setBgColor(0.3f, 1.0f, 0.1f, 0.0f);
    a.enabledOpenGl();

    for (int i=1;i<2000;i++){
        a.draw();
    }
    a.close();

    return 0;
}
