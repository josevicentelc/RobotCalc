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
      procedure trasladarX(x_ : double);
      procedure trasladarY(y_ : double);
      procedure trasladarZ(z_ : double);
      procedure setPosicion(x_ : double; y_ : double ; z_ : double);
      procedure trasladar(x_ : double; y_ : double ; z_ : double);
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
     setIdentidad();
end;

constructor TMatrizTransformacion.create(otra : TMatrizTransformacion);
begin
     inherited create((otra as TMatriz));
end;

constructor TMatrizTransformacion.createFromMatriz(otra : TMatriz);
begin
     inherited create(otra);
end;


procedure TMatrizTransformacion.trasladarX(x_ : double);
begin
     matriz[0][3] := matriz[0][3] + x_;
end;

procedure TMatrizTransformacion.trasladarY(y_ : double);
begin
     matriz[1][3] := matriz[1][3] + y_;
end;

procedure TMatrizTransformacion.trasladarZ(z_ : double);
begin
     matriz[2][3] := matriz[2][3] + z_;
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

procedure TMatrizTransformacion.trasladar(x_ : double; y_ : double ; z_ : double);
begin
  trasladarX(x_);
  trasladarY(y_);
  trasladarZ(z_);
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
begin
     angleInRadias:=degtograd(x_);
     matriz[1][1] := cos(angleInRadias);
     matriz[1][2] := sin(angleInRadias) * -1;
     matriz[2][1] := sin(angleInRadias);
     matriz[2][2] := cos(angleInRadias);
end;

procedure TMatrizTransformacion.rotarY(y_ : double);
var
  angleInRadias : double;
begin
     angleInRadias:=degtograd(y_);
     matriz[0][0] := cos(angleInRadias);
     matriz[0][2] := sin(angleInRadias) * -1;
     matriz[2][0] := sin(angleInRadias);
     matriz[2][2] := cos(angleInRadias);
end;

procedure TMatrizTransformacion.rotarZ(z_ : double);
var
  angleInRadias : double;
begin
     angleInRadias:=degtograd(z_);
     matriz[0][0] := cos(angleInRadias);
     matriz[0][1] := sin(angleInRadias) * -1;
     matriz[1][0] := sin(angleInRadias);
     matriz[1][1] := cos(angleInRadias);
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

