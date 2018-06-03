unit utnodo;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, utmatriztransformacion, uTmatriz;

type

  TNodo = class;

  TNodoList = array of TNodo;

  TNodo = class(TObject)
  private
         nodos : TNodoList;
         parent : TNodo;
         origen : TMatrizTransformacion;

         rotx, roty, rotz : double;
         trax, tray, traz : double;
  protected
  public
         constructor create();
         constructor create(n : TNodo);
         constructor create(x_ : double; y_ : double; z_ : double);
         procedure setParent(n : TNodo);
         procedure setTCP(x_ : double; y_ : double; z_ : double);
         procedure quitarHijo(n : TNodo);
         procedure separarDePadre();
         procedure addNodo(n : TNodo);
         function addNodo(): TNodo;
         function getOrigen(): TMatrizTransformacion;
         procedure trasladarX(x_ : double);
         procedure trasladarY(y_ : double);
         procedure trasladarZ(z_ : double);
         procedure trasladar(x_ : double; y_ : double; z_ : double);
         procedure rotarX(x_ : double);
         procedure rotarY(y_ : double);
         procedure rotarZ(z_ : double);
         procedure rotar(x_ : double; y_ : double; z_ : double);
         function getTranslacionX() : double;
         function getTranslacionY() : double;
         function getTranslacionZ() : double;
         function getRotacionX() : double;
         function getRotacionY() : double;
         function getRotacionZ() : double;
         function getGlobarVector(x_ : double; y_ : double; z_ : double): TMatriz;
         function getGlobarVector(vector : TMatriz): TMatriz;
         function getVectorTo(vector : TMatriz): TMatriz;
         function getMatrizTransfGlobal() : TMatrizTransformacion;
         function getNodos() : TNodoList;
         function getCoordenadas(): TMatriz;
  end;

implementation

constructor TNodo.create();
begin
     rotx:=0; roty:=0; rotz := 0;
     trax:=0;  tray:=0; traz := 0;
     origen := TMatrizTransformacion.create();
     SetLength(nodos, 0);
end;

function TNodo.getTranslacionX() : double;
begin
     result := trax;
end;

function TNodo.getTranslacionY() : double;
begin
     result := tray;
end;

function TNodo.getTranslacionZ() : double;
begin
     result := traz;
end;

function TNodo.getRotacionX() : double;
begin
     result := trax;
end;

function TNodo.getRotacionY() : double;
begin
     result := tray;
end;

function TNodo.getRotacionZ() : double;
begin
     result := traz;
end;

constructor TNodo.create(n : TNodo);
var
  I : Integer;
begin
     rotx:=0; roty:=0; rotz := 0;
     trax:=0;  tray:=0; traz := 0;
     origen := n.getOrigen().copia();
     SetLength(nodos, 0);
     for I := 0 to length(n.getNodos()) -1 do
     begin
          SetLength(nodos, length(nodos)+1);
          nodos[length(nodos)-1] := TNodo.create(n.getNodos()[i]);
     end;
end;

constructor TNodo.create(x_ : double; y_ : double; z_ : double);
begin
     rotx:=0; roty:=0; rotz := 0;
     trax:=0;  tray:=0; traz := 0;
   origen := TMatrizTransformacion.create;
   SetLength(nodos, 0);
   trasladar(x_, y_, z_);
end;

procedure TNodo.setParent(n : TNodo);
begin
     parent := n;
end;


procedure TNodo.setTCP(x_ : double; y_ : double; z_ : double);
begin
     trax:=x_;
     tray:=y_;
     traz:=z_;
     origen.setX(x_);
     origen.setY(y_);
     origen.setZ(z_);
end;

procedure TNodo.quitarHijo(n : TNodo);
var
  I, J : Integer;
begin
     for I := length(nodos)-1 downto 0 do
     begin
          if nodos[i] = n then
          begin
            nodos[i].Free;
            nodos[i] := nil;
            for j := i to length(nodos)-2 do
            begin
                 nodos[i] := nodos[I+1];
            end;
            setLength(nodos, length(nodos)-1);
          end;
     end;
end;

procedure TNodo.separarDePadre();
var
  nuevoOrigen : TMatriz;
begin
     if parent <> nil then
     begin
          nuevoOrigen := getGlobarVector(parent.getcoordenadas());
          parent.quitarHijo(self);
          parent := nil;
          setTCP(nuevoOrigen.getValue(0, 0), nuevoOrigen.getValue(1, 0),nuevoOrigen.getValue(2, 0));
     end;
end;

procedure TNodo.addNodo(n : TNodo);
begin
     n.setParent(self);
     setLength(nodos, length(nodos)+1);
     nodos[length(nodos)-1] := n;
end;

function TNodo.addNodo(): TNodo;
var
  n : TNodo;
begin
     n := TNodo.create;
     n.setParent(self);
     setLength(nodos, length(nodos)+1);
     nodos[length(nodos)-1] := n;
     result := n;
end;

function TNodo.getOrigen(): TMatrizTransformacion;
begin
     result := origen;
end;

procedure TNodo.trasladarX(x_ : double);
begin
     origen.setX(x_);
end;

procedure TNodo.trasladarY(y_ : double);
begin
     origen.setY(y_);
end;

procedure TNodo.trasladarZ(z_ : double);
begin
     origen.setZ(z_);
end;

procedure TNodo.trasladar(x_ : double; y_ : double; z_ : double);
begin
     origen.trasladar(x_, y_, z_);
end;

procedure TNodo.rotarX(x_ : double);
begin
     rotx:=x_;
     origen.rotarX(x_);
end;

procedure TNodo.rotarY(y_ : double);
begin
     roty:=y_;
     origen.rotarY(y_);
end;

procedure TNodo.rotarZ(z_ : double);
begin
     rotz:=z_;
     origen.rotarz(z_);
end;

procedure TNodo.rotar(x_ : double; y_ : double; z_ : double);
begin
     rotarX(x_);
     rotarY(y_);
     rotarZ(z_);
end;

function TNodo.getGlobarVector(x_ : double; y_ : double; z_ : double): TMatriz;
var
  m : TMatriz;
begin
     m := tMatriz.create(4, 1);
     m.setValue(0, 0, x_);
     m.setValue(1, 0, y_);
     m.setValue(2, 0, z_);
     m.setValue(3, 0, 1);
     result := getGlobarVector(m);
end;

function TNodo.getGlobarVector(vector : TMatriz): TMatriz;
var
  t : TMatriz;
begin
     if parent <> nil then
     begin
          t := getVectorTo(vector);
          result := parent.getGlobarVector(t);
     end
     else
     begin
          result := vector;
     end;
end;

function TNodo.getVectorTo(vector : TMatriz): TMatriz;
begin
     result := (origen as TMatriz).producto(vector);
end;

function TNodo.getMatrizTransfGlobal() : TMatrizTransformacion;
var
  C : TMatrizTransformacion;
begin
     origen := TMatrizTransformacion.create();
     origen.setX(trax);
     origen.setY(tray);
     origen.setZ(traz);
     origen.rotar(rotx, roty, rotz);
     if parent = nil then
     begin
          result := origen;
     end
     else
     begin
          result :=parent.getMatrizTransfGlobal().producto(origen);
     end;
end;

function TNodo.getNodos() : TNodoList;
begin
     result := nodos;
end;

function TNodo.getCoordenadas(): TMatriz;
begin
     result := origen.getCol(3);
end;

end.

