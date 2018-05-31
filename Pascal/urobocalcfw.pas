unit uRoboCalcFw;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils;

type

  tnumbers = array of double;
  tmatrix = array of tnumbers;

  TMatriz = class(TObject)
    private
           matriz : TMatrix;
           rCount : integer;
           cCount : integer;
    public
           constructor create(rows: integer; cools : integer);
           constructor create(otra : TMatriz);
           procedure setCero();
           function X() : double;
           function Y() : double;
           function Z() : double;
           function RowCount() : integer;
           function ColCount() : integer;
           function getValue(row : integer; col : integer): double;
           procedure setValue(row : integer; col : integer ; value : double);
           function equals(otra : TMatriz) : boolean;
           function getCol(col : integer): TMatriz;
           function getRow(row : integer): TMatriz;
           function traspuesta() : TMatriz;
           function producto(otra : TMatriz) : TMatriz;
           function suma(otra : TMatriz) : TMatriz;
           function resta(otra : TMatriz) : TMatriz;
           procedure setIdentidad();
           function abs() : TMatriz;
           procedure random(min : double; max : double);
           procedure intercambioFilas(fila1 : integer; fila2 : integer);
           procedure setRow(fila : integer; m : tnumbers);
           procedure setRow(fila : integer; m : TMatriz);
           function reordenarParaReducir(): TMatriz;
           procedure eliminarImprecision();
           function reducir(): TMatriz;
           function escalonada(): TMatriz;
           function subMatriz(fOrigen: integer; cOrigen: integer; fDestino : integer; cDestino : integer): TMatriz;
           function copia(): TMatriz;
           function ToString(): String;
           function ToString(pos: integer): String;
           function esInvertible(): boolean;
           function adjunta(): TMatriz;
           function adjunta(fila: integer; columna : integer): TMatriz;
           function inversa(): TMatriz;
           function determinante(): double;
           function producto(value : double): TMatriz;
           function division(value : double): TMatriz;


  end;

  TMatrizTransformacion = class(TObject)
    private
    public
  end;

  TNodo = class(TObject)
    private
    public
  end;

  TRobot = class(TObject)
    private
    public
  end;

implementation

constructor TMatriz.create(rows: integer; cools : integer);
var
  I : Integer;
begin
  inherited create;
  rCount:=rows;
  cCount:=cools;
  SetLength(matriz, rCount);
  for I := 0 to rCount-1 do
  begin
    SetLength(matriz[I], cCount);
  end;
  setCero();
end;

constructor TMatriz.create(otra : TMatriz);
var
  I, J: Integer;
begin
     rCount:=otra.RowCount();
     cCount:=otra.ColCount();
     SetLength(matriz, rCount);
     for I := 0 to rCount-1 do
     begin
       SetLength(matriz[I], cCount);
     end;
     for I := 0 to rCount-1 do
         for j := 0 to cCount -1 do
             matriz[I][J] := otra.GetValue(I, J);
end;

function TMatriz.equals(otra : TMatriz) : boolean;
var
  I, J: Integer;
begin
     result := true;
     if (rCount <> otra.RowCount()) or (cCount <> otra.RowCount()) then result := false
     else
         for I := 0 to rCount-1 do
             for J := 0 to cCount -1 do
                 if (matriz[I][J] <> otra.getValue(I, J)) then result := false;
end;

procedure TMatriz.setCero();
var
  I, J: Integer;
begin
     for I := 0 to rCount-1 do
         for j := 0 to cCount -1 do
             matriz[I][J] := 0;
end;

function TMatriz.X() : double;
begin
  if (cCount > 0) and (rCount >= 1) then
             result :=  matriz[0][cCount-1]
  else
             result := 0;
end;

function TMatriz.Y() : double;
begin
  if (cCount > 0) and (rCount >= 2) then
             result :=  matriz[1][cCount-1]
  else
             result := 0;
end;

function TMatriz.Z() : double;
begin
  if (cCount > 0) and (rCount >= 3) then
             result :=  matriz[2][cCount-1]
  else
             result := 0;
end;

function TMatriz.RowCount() : integer;
begin
  result := rCount;
end;

function TMatriz.ColCount() : integer;
begin
  result := cCount;
end;

function TMatriz.getValue(row : integer; col : integer): double;
begin
  if (row < 0) or (row >= rCount) then result := 0
  else
  if (col < 0) or (col >= cCount) then result := 0
  else
  result := matriz[row][col];
end;

procedure TMAtriz.setValue(row : integer; col : integer ; value : double);
begin
  if (row >= 0) and (row < rCount) then
     if (col >= 0 ) and (col < cCount) then
        matriz[row][col] := value;
end;

function TMatriz.getCol(col : integer): TMatriz;
var
  I : Integer;
begin
     result := tMatriz.create(rCount, 1);
     for I := 0 to rCount -1 do
         result.setValue(0, I, matriz[I][col]);
end;

function TMatriz.getRow(row : integer): TMatriz;
var
  I : Integer;
begin
  result := tMatriz.create(1, cCount);
  for I := 0 to cCount -1 do
      result.setValue(I, 0, matriz[row][I]);
end;

function TMatriz.traspuesta() : TMatriz;
var
  I, J: Integer;
begin
  result := TMatriz.create(cCount, rCount);
  for i :=0 to rCount-1 do
      for j := 0 to cCount-1 do
          result.setValue(j, i, matriz[i][j]);
end;


function TMatriz.producto(otra : TMatriz) : TMatriz;
var
  I, J, R : Integer;
  T : double;
begin

    if (cCount = otra.rowCount()) then
       begin
            result := TMatriz.create(rCount, cCount);
            for I := 0 to otra.RowCount()-1 do
                for J := 0 to otra.ColCount() -1 do
                begin
                  t := 0;
                  for R := 0 to cCount-1 do
                  begin
                    t := t + matriz[i][r] * otra.getValue(r, j);
                  end;
                  result.setValue(i, j, t);
                end;
       end
    else
    begin
         result := TMatriz.create(0, 0);
    end;


end;

function TMatriz.suma(otra : TMatriz) : TMatriz;
var
  I, J : Integer;
begin
      if (rCount = otra.rowCount()) and (cCount = otra.colCount()) then
         begin
              result:= TMatriz.create(rCount, cCount);
              for I := 0 to rCount -1 do
                  for J := 0 to cCount -1 do
                      result.setValue(i, j, matriz[i][j] + otra.getValue(i, j));
         end
      else
         begin
              result := TMatriz.create(0, 0);
         end;
end;

function TMatriz.resta(otra : TMatriz) : TMatriz;
var
  I, J : Integer;
begin
      if (rCount = otra.rowCount()) and (cCount = otra.colCount()) then
         begin
              result:= TMatriz.create(rCount, cCount);
              for I := 0 to rCount -1 do
                  for J := 0 to cCount -1 do
                      result.setValue(i, j, matriz[i][j] - otra.getValue(i, j));
         end
      else
         begin
              result := TMatriz.create(0, 0);
         end;
end;

procedure TMatriz.setIdentidad();
var
  I, min : Integer;
begin
     setCero();
     if rCount < cCount then     min := rCount
     else                        min := cCount;
     for I := 0 to min-1 do
         matriz[I][I] := 1;
end;

function TMatriz.inversa(): TMatriz;
var
  deter : double;
begin
        deter := determinante();
        if (deter = 0) then
            result := TMatriz.create(0, 0)
        else
            result := traspuesta().adjunta().producto(1/deter);
end;

function TMatriz.adjunta(): TMatriz;
var
  I, J : Integer;
begin
         if (rCount <> cCount) then result := TMatriz.create(0,0)
         else
         begin
              result := TMatriz.create(rCount, cCount);
              for i := 0 to rCount -1 do
                  for j := 0 to cCount -1 do
                  begin
                      result.setValue(i, j, adjunta(i, j).determinante()  );
                      // si el elemento i,j tiene un i+j impar, se invierte su signo
                      if (  (i+j) mod 2 <> 0  ) then result.setValue(i, j, result.getValue(i, j) * -1);
                  end;
         end;
end;

function TMatriz.determinante(): double;
var
  det1, det2 : double;
  sum : boolean;
  I : Integer;
begin
     if rCount = 0 then        result := 0
     else
     if rCount = 1 then        result := matriz[0][0]
     else
     if rCount = 2 then        result := matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0]
     else
     if rCount = 3 then
        begin
             det1 := 0;
             det2 := 0;
             det1 := det1 + matriz[0][0] * matriz[1][1] * matriz[2][2];
             det1 := det1 + matriz[1][0] * matriz[2][1] * matriz[0][2];
             det1 := det1 + matriz[2][0] * matriz[0][1] * matriz[1][2];
             det2 := det2 + matriz[0][2] * matriz[1][1] * matriz[2][0];
             det2 := det2 + matriz[1][2] * matriz[2][1] * matriz[0][0];
             det2 := det2 + matriz[2][2] * matriz[0][1] * matriz[1][0];
             result := det1 - det2;
        end
     else
        begin
          result :=0;
          sum := true;
          for i := 0 to rCount -1 do
          begin
          end;
             if (sum)then
                begin
                     result := result + adjunta(i,0).determinante() * matriz[i][0];
                     sum := false;
                end
             else
             begin
               result :=  result - adjunta(i,0).determinante() * matriz[i][0];
               sum := true;
             end;
        end;
end;

function TMatriz.producto(value : double): TMatriz;
var
  I, J : Integer;
begin
     result := TMatriz.create(rCount, cCount);
     for i := 0 to rCount -1 do
         for j := 0 to cCount -1 do
             result.setValue(i, j, matriz[i][j] * value);
end;

function TMatriz.division(value : double): TMatriz;
var
  I, J : Integer;
begin
        result := TMatriz.create(rCount, cCount);
        if (value <> 0) then
           begin
                for i := 0 to rCount-1 do
                    for j := 0 to cCount -1 do
                        result.setValue(i, j, matriz[i][j] / value);
           end;

end;

function TMatriz.adjunta(fila: integer; columna : integer): TMatriz;
var
  I, J : Integer;
  filaDestino, colDestino : Integer;
begin
    //Si alguno de los valores esta fuera de la matriz, retorno una matriz que es copia de esta
    if (fila >= rCount) or (columna >= cCount) or  (fila < 0) or (columna < 0) then
       result := TMatriz.create(self)
    else
    begin
         //Si ambos estan dentro, obtengo la matriz resultante de eliminar dicha fila y columna
         result := TMatriz.create(rCount-1, cCount-1);
         for I := 0 to rCount -1 do
             if (i <> fila) then
             begin
                 for j := 0 to cCount -1 do
                 begin
                     if (j <> columna) then
                     begin
                         if (i<fila) then filaDestino := i
                         else             filaDestino := i-1;
                         if (j<columna) then colDestino := j
                         else                colDestino := j-1;
                         result.setValue(filaDestino, colDestino, matriz[i][j]);
                     end;
                 end;
              end;
    end;
end;

function TMatriz.esInvertible(): boolean;
begin
    result := true;
    if (rCount <> cCount) or (rCount = 0) then result := false
    else
       if (determinante()=0) then result := false;
end;

function TMatriz.ToString(): String;
begin
     result := toString(2);
end;

function TMatriz.ToString(pos: integer): String;
begin
     result := '';
end;


function TMatriz.copia(): TMatriz;
var
  I, J : Integer;
begin
     result := TMatriz.create(rCount, cCount);
     for i := 0 to rCount -1 do
         for j := 0 to cCount -1 do
             result.setValue(i, j, matriz[i][j]);
end;

function TMatriz.subMatriz(fOrigen: integer; cOrigen: integer; fDestino : integer; cDestino : integer): TMatriz;
var
  I, J : Integer;
begin
     if (fOrigen >= fDestino) or (fOrigen >= cDestino) then
        result := TMatriz.create(0,0)
     else
         begin
             result := TMatriz.create(fDestino-fOrigen, cDestino-cOrigen);
              for i := fOrigen to fDestino -1 do
                  for j := cOrigen to cDestino -1 do
                      result.setValue(i-fOrigen, j-cOrigen, matriz[i][j]);

         end;
end;

function TMatriz.escalonada(): TMatriz;
begin
     {
             Matriz salida = reordenarParaReducir();

             for (int j=0;j<salida.rCount;j++){
                 //Primero hago 1 en la diagonal
                 if (salida.getValue(j, j) != 0) salida.setRow(j, salida.getRow(j).division(salida.getValue(j, j)));
                 for (int j2=j+1;j2<salida.rCount;j2++){

                     //Ahora recorro en J2 todas las filas inferiores a J y les resto Nveces la fila actual
                     //donde N es el valor de la columna J para la fila J2
                     Matriz N = salida.getRow(j).producto(salida.getValue(j2, j));

                     //Si tienen el mismo signo las resto, si son diferentes las sumo
                     if ((N.getValue(0, j) > 0 && salida.getValue(j2, j) > 0)
                             ||
                         (N.getValue(0, j) < 0 && salida.getValue(j2, j) < 0 ))
                     {
                         salida.setRow(j2,  salida.getRow(j2).resta(N) );
                     }
                     else
                     {
                         salida.setRow(j2,  salida.getRow(j2).suma(N) );
                     }
                 }
             }
             salida.eliminarImprecision();
             return salida;
         }
end;

function TMatriz.reducir(): TMatriz;
begin
     {
             Matriz m = new Matriz(this);
             m = m.escalonada();
             //Teniendo una matriz escalonada, voy de abajo a arriba eliminando terminos
             for (int i=m.rCount-1;i>0;i--){
                 //usando el 1 del eelemento i,i, elimino los terminos de las casillas i-1,
                 for (int i2= i-1;i2>=0;i2--){
                     //Obtengo la linea con el termino en 1
                     //y la multiplico por el valor que deseo eliminar
                     Matriz linea  = m.getRow(i).producto(m.getValue(i2, i));

                     //si el signo es igual, las resto
                     if ((linea.getValue(0, i) >0 && m.getValue(i2, i) > 0) || (linea.getValue(0, i) <0 && m.getValue(i2, i) < 0))
                         m.setRow(i2, m.getRow(i2).resta(linea));
                     else
                         m.setRow(i2, m.getRow(i2).suma(linea));
                     //si es distinto, las sumo
                 }
             }
             return m;
         }
end;

procedure TMatriz.eliminarImprecision();
begin
     {
             for (int i=0;i<rCount;i++)
                for (int j = 0;j<cCount;j++){
                    if (Math.abs(round(matriz[i][j]) - matriz[i][j]) < 0.0000001)
                        matriz[i][j] = round(matriz[i][j]);
                    else
                        if (matriz[i][j] == 0) matriz[i][j] = 0;
                }
         }
end;

function TMatriz.reordenarParaReducir(): TMatriz;
begin
     {
             Matriz m = new Matriz(this);
             for (int i=0;i<rCount;i++){
                 if (m.getValue(i, i) == 0 && i< rCount-1)
                     m = m.intercambioFilas(i, i+1);
             }
             return m;
         }
end;

procedure TMatriz.setRow(fila: integer; m : TMatriz);
begin
     {
             if (fila >= 0 && fila < rCount){    //La fila elegida debe estar dentro de la matriz
                 if (m.cCount >= cCount){        //La Matriz de valores debe contener suficientes valores
                     if (m.rCount > 0){
                         for (int j=0;j<cCount;j++)
                             matriz[fila][j] = m.getValue(0, j);
                     }
                 }
             }
         }
end;

procedure TMatriz.setRow(fila : integer; m : tnumbers);
begin
     {
             if (fila >= 0 && fila < rCount){    //La fila elegida debe estar dentro de la matriz
                 for (int j=0;j<cCount;j++)
                     if (j<m.length)
                         matriz[fila][j] = m[j];
                     else
                         matriz[fila][j] = 0;
             }
         }
end;

procedure TMatriz.intercambioFilas(fila1 : integer; fila2 : integer);
begin
         {if (fila1 >= rCount || fila2 >= rCount || fila1 <0 || fila2 <0)
             return new Matriz(this);
         else
         {
             Matriz salida = new Matriz(this);
             Matriz m1 = salida.getRow(fila1);
             Matriz m2 = salida.getRow(fila2);
             salida.setRow(fila1, m2);
             salida.setRow(fila2, m1);
             return salida;
         }
     }
end;

procedure TMatriz.random(min : double; max : double);
begin
     {
             //Si el minimo es mayor que el maximo, se invierten
             if (min > max){
                 double temp = max;
                 max = min;
                 min = temp;
             }
             for (int i=0;i<rCount;i++)
                 for (int j=0;j<cCount;j++){
                     matriz[i][j] = Math.random() * (max-min) + min;
                 }
         }
end;
function TMatriz.abs() : TMatriz;
begin
     {
             Matriz salida = new Matriz(this);
             for (int i=0;i<rCount;i++)
                 for (int j=0;j<cCount;j++)
                     salida.setValue(i, j, Math.abs(matriz[i][j]));
             return salida;


     }
end;


end.

