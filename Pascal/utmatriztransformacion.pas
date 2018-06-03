unit utmatriztransformacion;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, uTmatriz, math;

type
  TMatrizTransformacion = class(TMatriz)
    private
    public
      constructor create();
      constructor create(otra : TMatrizTransformacion);
      constructor createFromMatriz(otra : TMatriz);
      procedure setPosicion(x_ : double; y_ : double ; z_ : double);

      procedure trasladar(x_ : double; y_ : double ; z_ : double);
      procedure setMatriz(otra: TMatrizTransformacion);

      function getX() : double;
      function getY() : double;
      function getZ() : double;
      procedure setX(x_ : double);
      procedure setY(y_ : double);
      procedure setZ(z_ : double);

      procedure rotar(x_ : double; y_ : double; z_ : double);
      procedure rotarX(x_ : double);
      procedure rotarY(y_ : double);
      procedure rotarZ(z_ : double);

      function copia() : TMatrizTransformacion;
      function producto(otra : TMatrizTransformacion): TMatrizTransformacion;
      function producto(otra : TMatriz): TMatrizTransformacion;

  end;


implementation

constructor TMatrizTransformacion.create();
begin
     inherited create(4, 4);
//     angleX := 0;
//     angleY := 0;
//     angleZ := 0;
     setIdentidad();
end;

constructor TMatrizTransformacion.create(otra : TMatrizTransformacion);
begin
     inherited create((otra as TMatriz));
//     angleX := 0;
//     angleY := 0;
//     angleZ := 0;
end;

constructor TMatrizTransformacion.createFromMatriz(otra : TMatriz);
begin
     inherited create(otra);
//     angleX := 0;
//     angleY := 0;
//     angleZ := 0;
end;


procedure TMatrizTransformacion.setPosicion(x_ : double; y_ : double ; z_ : double);
begin
     setX(x_);
     setY(y_);
     setZ(z_);
end;

function TMatrizTransformacion.getX() : double;
begin
  if (cCount > 0) and (rCount >= 1) then
             result :=  matriz[0][cCount-1]
  else
             result := 0;
end;

function TMatrizTransformacion.getY() : double;
begin
  if (cCount > 0) and (rCount >= 2) then
             result :=  matriz[1][cCount-1]
  else
             result := 0;
end;

function TMatrizTransformacion.getZ() : double;
begin
  if (cCount > 0) and (rCount >= 3) then
             result :=  matriz[2][cCount-1]
  else
             result := 0;
end;

procedure TMatrizTransformacion.setX(x_ : double);
begin
     matriz[0][3] := x_;
end;

procedure TMatrizTransformacion.setY(y_ : double);
begin
  matriz[1][3] := y_;
end;

procedure TMatrizTransformacion.setZ(z_ : double);
begin
  matriz[2][3] := z_;
end;

procedure TMatrizTransformacion.setMatriz(otra: TMatrizTransformacion);
var
I,J:Integer;
begin
     for I:=0 to otra.RowCount()-1 do
       for J:=0 to otra.ColCount()-1 do
         setValue(J,I, otra.getValue(J,I));
end;

procedure TMatrizTransformacion.trasladar(x_ : double; y_ : double ; z_ : double);
begin
  setX(x_);
  setY(y_);
  setZ(z_);
end;

procedure TMatrizTransformacion.rotar(x_ : double; y_ : double; z_ : double);
begin
  rotarX(x_);
  rotarY(y_);
  rotarZ(z_);
end;

procedure TMatrizTransformacion.rotarX(x_ : double);
var
  angleInRadias : double;
  rotMat : TMatrizTransformacion;
begin
     rotMat:=TMatrizTransformacion.create();
     angleInRadias:=degtograd(x_);
     rotMat.setValue(1,1,cos(angleInRadias));
     rotMat.setValue(1,2,sin(angleInRadias));
     rotMat.setValue(2,1,sin(angleInRadias));
     rotMat.setValue(2,2,cos(angleInRadias));
     setMatriz(producto(rotMat));
end;

procedure TMatrizTransformacion.rotarY(y_ : double);
var
  angleInRadias : double;
  rotMat : TMatrizTransformacion;
begin
     rotMat:=TMatrizTransformacion.create();
     angleInRadias:=degtograd(y_);
     rotMat.setValue(0,0, cos(angleInRadias));
     rotMat.setValue(0,2, sin(angleInRadias) * -1);
     rotMat.setValue(2,0, sin(angleInRadias));
     rotMat.setValue(2,2, cos(angleInRadias));
     setMatriz(producto(rotMat));
end;

procedure TMatrizTransformacion.rotarZ(z_ : double);
var
  angleInRadias : double;
  rotMat : TMatrizTransformacion;
begin
     rotMat:=TMatrizTransformacion.create();
     angleInRadias:=degtograd(z_);
     rotMat.setValue(0,0,cos(angleInRadias));
     rotMat.setValue(0,1,sin(angleInRadias) * -1);
     rotMat.setValue(1,0,sin(angleInRadias));
     rotMat.setValue(1,1,cos(angleInRadias));
     setMatriz(producto(rotMat));
end;

function TMatrizTransformacion.copia() : TMatrizTransformacion;
begin
  result := TMatrizTransformacion.create(self);
end;

function TMatrizTransformacion.producto(otra : TMatrizTransformacion): TMatrizTransformacion;
var
  m : TMatriz;
begin
     m := inherited producto( (otra as TMatriz)  );
     result := TMatrizTransformacion.createFromMatriz(m);
end;

function TMatrizTransformacion.producto(otra : TMatriz): TMatrizTransformacion;
var
  m : TMatriz;
begin
     m := inherited producto( otra  );
     result := TMatrizTransformacion.createFromMatriz(m);
end;


end.

