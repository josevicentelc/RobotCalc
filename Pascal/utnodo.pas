unit utnodo;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, utmatriztransformacion, uTmatriz, Dialogs;

type

  TNodo = class;

  TNodoList = array of TNodo;

  TNodo = class(TObject)
  private
         nodos : TNodoList;
         parent : TNodo;
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

         procedure setX(x_ : double);
         procedure setY(y_ : double);
         procedure setZ(z_ : double);
         function getX() : double;
         function getY() : double;
         function getZ() : double;
         procedure rotarX(x_ : double);
         procedure rotarY(y_ : double);
         procedure rotarZ(z_ : double);
         procedure rotar(x_ : double; y_ : double; z_ : double);
         function getRotacionX() : double;
         function getRotacionY() : double;
         function getRotacionZ() : double;


         function getGlobarVector(x_ : double; y_ : double; z_ : double): TMatriz;
         function getGlobarVector(vector : TMatriz): TMatriz;
         function getGlobalCoordinates() : TMatriz;
         function getGlobalCoordinates(x_:double;y_:double;z_:double) : TMatriz;
         function getVectorTo(vector : TMatriz): TMatriz;
         function getMatrizTransf() : TMatrizTransformacion;
         function getMatrizTransfGlobal() : TMatrizTransformacion;
         function getNodos() : TNodoList;
         function getCoordenadas(): TMatriz;
  end;

implementation

constructor TNodo.create();
begin
     rotx:=0; roty:=0; rotz := 0;
     trax:=0;  tray:=0; traz := 0;
     SetLength(nodos, 0);
end;

function TNodo.getRotacionX() : double;        begin     result := rotx;          end;
function TNodo.getRotacionY() : double;        begin     result := roty;          end;
function TNodo.getRotacionZ() : double;        begin     result := rotz;          end;
procedure TNodo.rotarX(x_ : double);           begin     rotx:=x_;                end;
procedure TNodo.rotarY(y_ : double);           begin     roty:=y_;                end;
procedure TNodo.rotarZ(z_ : double);           begin     rotz:=z_;                end;

procedure TNodo.setX(x_ : double);             begin     trax:=x_;                end;
procedure TNodo.setY(y_ : double);             begin     tray:=y_;                end;
procedure TNodo.setZ(z_ : double);             begin     traz:=z_;                end;
function TNodo.getX() : double;     begin     result := trax;          end;
function TNodo.getY() : double;     begin     result := tray;          end;
function TNodo.getZ() : double;     begin     result := traz;          end;


constructor TNodo.create(n : TNodo);
var
  I : Integer;
begin
     rotx:=0; roty:=0; rotz := 0;
     trax:=0;  tray:=0; traz := 0;
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
     SetLength(nodos, 0);
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






procedure TNodo.rotar(x_ : double; y_ : double; z_ : double);
begin
     rotarX(x_);
     rotarY(y_);
     rotarZ(z_);
end;

function TNodo.getGlobarVector(x_ : double; y_ : double; z_ : double): TMatriz;
begin
     //Todo
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
     //result := (origen as TMatriz).producto(vector);
end;

function TNodo.getMatrizTransf() : TMatrizTransformacion;
begin
     result := TMatrizTransformacion.create();
     result.setX(trax);
     result.setY(tray);
     result.setZ(traz);
     result.rotar(rotx, roty, rotz);
end;

function TNodo.getGlobalCoordinates() : TMatriz;
var
  pos : TMatriz;
  transf : TMatrizTransformacion;
begin
     pos := getCoordenadas();
     transf := getMatrizTransfGlobal();
     result := transf.producto(pos);
     transf.free;
end;

function TNodo.getGlobalCoordinates(x_:double;y_:double;z_:double) : TMatriz;
var
  pos : TMatriz;
  transf : TMatrizTransformacion;
begin
     pos := TMatriz.create(4,1);
     pos.setValue(0,0,x_);
     pos.setValue(1,0,y_);
     pos.setValue(2,0,z_);
     pos.setValue(3,0,1);
     transf := getMatrizTransfGlobal();
     result := transf.producto(pos);
     transf.free;
end;


function TNodo.getMatrizTransfGlobal() : TMatrizTransformacion;
var
  C : TMatrizTransformacion;
begin
     if parent = nil then
     begin
          result := getMatrizTransf();
     end
     else
     begin
          C := getMatrizTransf();
          result :=parent.getMatrizTransfGlobal().producto(C);
          C.free;
     end;
end;

function TNodo.getNodos() : TNodoList;
begin
     result := nodos;
end;

function TNodo.getCoordenadas(): TMatriz;
begin
     result := TMatriz.create(4,1);
     result.setValue(3,0,1);
end;

end.

