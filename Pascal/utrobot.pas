unit utrobot;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, utnodo, utmatriztransformacion, uTmatriz;

type

   tNodoList = array of TNodo;

  TRobot = class (TObject)
  private
    gradosLibertad : Integer;
    nodos : TNodoList;
    sistemaCoordenadas : TNodo;
  public
    constructor create(grados : integer);
    procedure trasladarX(nodo_ : integer; x_ : double);
    procedure trasladarY(nodo_ : integer; y_ : double);
    procedure trasladarZ(nodo_ : integer; z_ : double);
    procedure rotarX(nodo_ : integer; x_ : double);
    procedure rotarY(nodo_ : integer; y_ : double);
    procedure rotarZ(nodo_ : integer; z_ : double);
    function getPos(nodo_: integer): TMatriz;
    function getNodo(nodo_ : integer): TNodo;
    function getVectorTo(nodo_:integer; x_:double; y_:double; z_:double): TMatriz;
    function getVectorTo(x_:double; y_:double; z_:double): TMatriz;

  end;

implementation

constructor TRobot.create(grados : integer);
var
  I: Integer;
  ultimoNodo : TNodo;
begin
          SetLength(nodos, 0);
          SistemaCoordenadas := TNodo.create();
          ultimoNodo := SistemaCoordenadas;

          SetLength(nodos, length(nodos)+1);
          nodos[length(nodos)-1] := ultimoNodo;

          if (grados > 0) then
          begin
                gradosLibertad := grados;
                for i:= 1 to grados-1 do
                    begin
                      ultimoNodo := ultimoNodo.addNodo();
                      SetLength(nodos, length(nodos)+1);
                      nodos[length(nodos)-1]:= ultimoNodo;
                    end;
          end;
end;

procedure TRobot.trasladarX(nodo_ : integer; x_ : double);
begin
   if (nodo_ < gradosLibertad) and (nodo_ >= 0) then
        nodos[nodo_].setX(x_);
end;

procedure TRobot.trasladarY(nodo_ : integer; y_ : double);
begin
   if (nodo_ < gradosLibertad) and (nodo_ >= 0) then
        nodos[nodo_].setY(y_);
end;

procedure TRobot.trasladarZ(nodo_ : integer; z_ : double);
begin
   if (nodo_ < gradosLibertad) and (nodo_ >= 0) then
        nodos[nodo_].setZ(z_);
end;

procedure TRobot.rotarX(nodo_ : integer; x_ : double);
begin
    if (nodo_ < gradosLibertad) and (nodo_ >= 0) then
        nodos[nodo_].rotarX(x_);
end;

procedure TRobot.rotarY(nodo_ : integer; y_ : double);
begin
    if (nodo_ < gradosLibertad) and (nodo_ >= 0) then
        nodos[nodo_].rotarY(y_);
end;

procedure TRobot.rotarZ(nodo_ : integer; z_ : double);
begin
    if (nodo_ < gradosLibertad) and (nodo_ >= 0) then
        nodos[nodo_].rotarZ(z_);
end;

function TRobot.getPos(nodo_: integer): TMatriz;
begin
        if (nodo_ < gradosLibertad) and (nodo_ >= 0) then
        begin
           result := nodos[nodo_].getMatrizTransfGlobal().producto(nodos[nodo_].getCoordenadas());
        end
        else
        begin
             result := TMatriz.create(0, 0);
        end;
end;

function TRobot.getNodo(nodo_ : integer): TNodo;
begin
     if (nodo_<0) or (nodo_>= length(nodos)) then
          result := TNodo.create()
     else
          result := nodos[nodo_];
end;

function TRobot.getVectorTo(nodo_:integer; x_:double; y_:double; z_:double): TMatriz;
begin
          if (nodo_ >= 0) and (nodo_ < gradosLibertad) then
          begin
              result := nodos[nodo_].getGlobarVector(x_, y_, z_);
          end
          else
          begin
               result := TMatriz.create(0, 0);
          end;
end;

function TRobot.getVectorTo(x_:double; y_:double; z_:double): TMatriz;
begin
        result := nodos[gradosLibertad-1].getGlobarVector(x_, y_, z_);
end;


end.

