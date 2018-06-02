unit uTmatriz;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, math;

function testMatriz() : String;
function precision(d : double; n:integer) : double;
function equals(d1 : double; d2:double; decimalPrecision:integer) : boolean;

type
  tnumbers = array of double;
  tmatrix = array of tnumbers;

  TMatriz = class(TObject)
    protected
           matriz : TMatrix;
           rCount : integer;
           cCount : integer;
    public
           constructor create(rows: integer; cools : integer);
           constructor create(otra : TMatriz);
           procedure setCero();
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
           function absoluteMatrix() : TMatriz;
           procedure randomMatrix(min : double; max : double);
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
           function getX() : double;
           function getY() : double;
           function getZ() : double;
  end;



implementation



function TMatriz.getX() : double;
begin
     result := matriz[0][3];
end;

function TMatriz.getY() : double;
begin
     result := matriz[1][3];
end;

function TMatriz.getZ() : double;
begin
     result := matriz[2][3];
end;

function precision(d : double; n:integer) : double;
var
t : string;
mask : String;
I: integer;
begin
     mask := '##0';
     if n > 0 then mask := mask + '.';
     for I := 1 to n do
         mask := mask + '0';
     t := StringReplace(formatFloat(mask, d), '.', ',', [rfreplaceall]);
     result := strToFloat(t)
end;

function ABS(indata : double):double;
begin
     if indata < 0 then result := indata* -1
     else               result := indata;
end;

function equals(d1 : double; d2:double; decimalPrecision:integer) : boolean;
var
dif : double;
maxdif : double;
begin
     dif := abs(d1-d2);
     maxdif := 1 / ( power(10,decimalPrecision));
     result := (dif < maxdif);
end;

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
     if (rCount <> otra.RowCount()) or (cCount <> otra.ColCount()) then result := false
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
         result.setValue(I, 0, matriz[I][col]);
end;

function TMatriz.getRow(row : integer): TMatriz;
var
  I : Integer;
begin
  result := tMatriz.create(1, cCount);
  for I := 0 to cCount -1 do
      result.setValue(0, I, matriz[row][I]);
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
  n,m,p : integer;
  T : double;
begin

   //M(m,n) * M(n,p) = M(m,p)

    if (cCount = otra.rowCount()) then
       begin
          n := cCount;
          m := RowCount();
          p := otra.ColCount();
          result := TMatriz.create(m, p);
          for I := 0 to m-1 do
              for J := 0 to p-1 do
                  begin
                       t := 0;
                       for R := 0 to n-1 do
                           begin
                                t := t + getValue(I, R) * otra.getValue(R, J);
                           end;
                       result.setValue(I,J,T);
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
     if rCount <> cCount then    result := 0
     else
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
             if (sum)then
                begin
                     result := result + (adjunta(i,0).determinante() * matriz[i][0]);
                     sum := false;
                end
             else
             begin
               result :=  result - (adjunta(i,0).determinante() * matriz[i][0]);
               sum := true;
             end;
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
var
  mask : String;
  I, J: integer;
  line : String;
  tamline : integer;
  t : TStringList;
begin
     mask := '##0';
     if pos > 0 then mask := mask+'.';
     for I := 1 to pos do
         mask := mask + '0';

     t := TStringList.create();
     for I := 0 To RowCount()-1 do
     begin
         t.Add('| ');
         for J := 0 to cCount -1 do
         begin
              t[T.Count-1] := t[T.Count-1] + formatFloat(mask, matriz[i][j]) + ' | ';
         end;
     end;
     result := t.Text;
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
     if (fOrigen >= fDestino) or (cOrigen >= cDestino) then
        result := TMatriz.create(0,0)
     else
         begin
             result := TMatriz.create(fDestino-fOrigen+1, cDestino-cOrigen+1);
              for i := fOrigen to fDestino  do
                  for j := cOrigen to cDestino  do
                      result.setValue(i-fOrigen, j-cOrigen, matriz[i][j]);

         end;
end;

function TMatriz.escalonada(): TMatriz;
var
  I, J, j2: integer;
  N : TMatriz;
begin
             result := reordenarParaReducir();
             for j := 0 to result.RowCount() -1 do
             begin
                 //Primero hago 1 en la diagonal
                 if (result.getValue(j, j) <> 0) then result.setRow(j, result.getRow(j).division(result.getValue(j, j)));
                 for j2 := j+1 to result.RowCount() -1 do
                 begin
                     //Ahora recorro en J2 todas las filas inferiores a J y les resto Nveces la fila actual
                     //donde N es el valor de la columna J para la fila J2
                     N := result.getRow(j).producto(result.getValue(j2, j));
                     //Si tienen el mismo signo las resto, si son diferentes las sumo
                     if ((N.getValue(0, j) > 0) and (result.getValue(j2, j) > 0))
                             or
                         ((N.getValue(0, j) < 0) and (result.getValue(j2, j) < 0 )) then
                     begin
                         result.setRow(j2,  result.getRow(j2).resta(N) );
                     end
                     else
                     begin
                         result.setRow(j2,  result.getRow(j2).suma(N) );
                     end;
                 end;
             end;
             result.eliminarImprecision();
end;

function TMatriz.reducir(): TMatriz;
var
  I, J, I2 : integer;
  linea: TMatriz;
begin

             result := escalonada();
             //Teniendo una matriz escalonada, voy de abajo a arriba eliminando terminos
             for i := result.RowCount()-1 downto 0 do
                 //usando el 1 del eelemento i,i, elimino los terminos de las casillas i-1,
                 for i2 := i-1 downto 0 do
                 begin

                     //Obtengo la linea con el termino en 1
                     //y la multiplico por el valor que deseo eliminar
                     linea  := result.getRow(i).producto(result.getValue(i2, i));

                     //si el signo es igual, las resto
                     if ((linea.getValue(0, i) >0) and (result.getValue(i2, i) > 0))
                             or
                        ((linea.getValue(0, i) <0) and (result.getValue(i2, i) < 0)) then
                         result.setRow(i2, result.getRow(i2).resta(linea))
                     else
                         result.setRow(i2, result.getRow(i2).suma(linea));
                     //si es distinto, las sumo
                 end;
end;

procedure TMatriz.eliminarImprecision();
var
  I,J:integer;
begin
     for I := 0 to rCount-1 do
         for J := 0 to cCount-1 do
             matriz[i][j] := precision(matriz[i][j], 8);

end;

//Reordena las filas de forma que ninguna tenga un 0 en su diagonal
function TMatriz.reordenarParaReducir(): TMatriz;
var
  I : integer;
begin

     result := TMatriz.create(self);
     for i := 0 to rCount-1 do
         if (result.getValue(i, i) = 0) and  (i< rCount-1) then
            result.intercambioFilas(i, i+1);
end;

procedure TMatriz.setRow(fila: integer; m : TMatriz);
var
  J : integer;
begin
     if (fila >= 0) and (fila < rCount) then    //La fila elegida debe estar dentro de la matriz
        if m.cCount >= cCount then           //La Matriz de valores debe contener suficientes valores
           if m.rCount > 0 then
              for j :=0 to cCount -1 do
                  matriz[fila][j] := m.getValue(0, j);
end;

procedure TMatriz.setRow(fila : integer; m : tnumbers);
var
  J : integer;
begin
     if (fila >= 0) and (fila < rCount) then     //La fila elegida debe estar dentro de la matriz
        for j :=0 to cCount -1 do
            if (j<length(m)) then        matriz[fila][j] := m[j]
            else                        matriz[fila][j] := 0;
end;

procedure TMatriz.intercambioFilas(fila1 : integer; fila2 : integer);
var
  m1, m2: TMatriz;
begin
     if (fila1 < rCount) and (fila2 < rCount) and (fila1 >=0) and (fila2 >=0) then
         begin
             m1 := getRow(fila1);
             m2 := getRow(fila2);
             setRow(fila1, m2);
             setRow(fila2, m1);
         end;
end;

procedure TMatriz.randomMatrix(min : double; max : double);
var
  temp : double;
  I, J: Integer;
  margin : integer;
begin
             //Si el minimo es mayor que el maximo, se invierten
             if (min > max) then
             begin
                temp := max;
                 max := min;
                 min := temp;
             end;
             margin :=Round(max-min);
             for i:=0 to rCount-1 do
                 for j :=0 to cCount-1 do
                     matriz[i][j] := random( margin) + min;
end;
function TMatriz.absoluteMatrix() : TMatriz;
var
  I, J : Integer;
begin
     result := TMatriz.create(self);
            for i :=0 to rCount-1 do
                 for j :=0 to cCount -1 do
                     result.setValue(i, j, abs(matriz[i][j]));
end;


//******************************************************************************
//******************************************************************************
//                              TEST MATRIZ
//******************************************************************************
//******************************************************************************

function testMatriz() : string;
var
t : TStringList;
A, B, C : TMatriz;
I,J : integer;
ok : boolean;
numbers : tnumbers;
n1,n2,n3 : double;
begin
     t := TStringList.Create();

     //Constuctor por defecto
     A := TMatriz.create(3, 5);
     if (A.RowCount() <> 3) or (A.ColCount()<> 5) then  t.Add('test 01: Forma de matriz incorrecta');
     ok := true;
     for I := 0 to A.RowCount()- 1do
         for J := 0 to A.colCount()-1 do
             if A.getValue(J,I) <> 0 then ok := false;
     if not ok then  t.Add('test 02: La matriz no esta inicializada todo a 0');
     A.free;

     //Contructor de copia
     A := TMatriz.create(2, 1);
     A.setValue(0, 0, 1);
     A.setValue(1, 0, 2);
     B := TMatriz.create(A);
     if (B.RowCount() <> 2) or (B.ColCount()<> 1) then  t.Add('test 03: Forma de matriz incorrecta');
     if (B.getValue(0, 0) <> 1) or (B.getValue(1, 0) <> 2) then t.Add('test 04: Los valores no se copiaron correctamente');
     A.free;
     B.free;

     //copia
     A := TMatriz.create(2, 1);
     A.setValue(0, 0, 1);
     A.setValue(1, 0, 2);
     B := A.copia();
     if (B.RowCount() <> 2) or (B.ColCount()<> 1) then  t.Add('test 03: Matriz.copia Forma de matriz incorrecta');
     if (B.getValue(0, 0) <> 1) or (B.getValue(1, 0) <> 2) then t.Add('test 04: Matriz.copia, Los valores no se copiaron correctamente');
     A.free;
     B.free;


     //setcero
     A := TMatriz.create(2, 1);
     A.setValue(0, 0, 1);
     A.setValue(1, 0, 2);
     A.setCero();
     if (A.getValue(0,0) <> 0) or (A.getValue(1,0) <> 0) then t.add('test 05: Los vlores no se han establecido a 0');
     A.free;

     //setidentidad
     A := TMatriz.create(2, 2);
     A.setIdentidad();
     if (A.getValue(0,0) <> 1) or (A.getValue(1,1) <> 1) then t.add('test 06: Matriz identidad incorrecta');
     if (A.getValue(0,1) <> 0) or (A.getValue(1,0) <> 0) then t.add('test 06: Matriz identidad incorrecta');
     A.free;

     //equals
     A := TMatriz.create(2, 1);
     B := TMatriz.create(2, 1);
     A.setValue(0,0,1); A.setValue(1,0,2);
     B.setValue(0,0,1); B.setValue(1,0,2);
     if not A.equals(B) then t.Add('test 07: Dos matrices iguales dan Equals = false');
     B.setValue(0,0,3); B.setValue(1,0,4);
     if A.equals(B) then t.Add('test 08: Dos matrices diferentes dan Equals = true');
     A.free;
     B.free;

     //getRow
     A := TMatriz.create(3, 3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,6);
     A.setValue(2,0,7);A.setValue(2,1,8);A.setValue(2,2,9);
     B := A.getRow(1);
     if B = nil then t.Add('test 09: GetRow ha retornado una matriz nula')
     else
        begin
            if (B.RowCount() <> 1) or (B.ColCount() <> 3) then t.Add('test 10: GetRow ha retornado una matriz con forma inesperada')
            else
            begin
                 if (b.getValue(0,0) <> 4) or (b.getValue(0, 1) <> 5) or (b.getValue(0,2) <> 6) then
                 begin
                  t.Add('test 11: GetRow ha retornado una matriz con con valores ineperados');
                  t.add('Esperaba (4,5,6) y obtengo ('+formatfloat('0', b.getValue(0,0))+','+formatfloat('0', b.getValue(0,1))+','+formatfloat('0', b.getValue(0,2))+')'   );
                 end;
            end;
        end;
     A.free;
     B.free;


     //getColum
     A := TMatriz.create(3, 3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,6);
     A.setValue(2,0,7);A.setValue(2,1,8);A.setValue(2,2,9);
     B := A.getCol(1);
     if B = nil then t.Add('test 12: GetCol ha retornado una matriz nula')
     else
        begin
            if (B.RowCount() <> 3) or (B.ColCount() <> 1) then t.Add('test 13: GetCol ha retornado una matriz con forma inesperada')
            else
            begin
                 if (b.getValue(0,0) <> 2) or (b.getValue(1, 0) <> 5) or (b.getValue(2,0) <> 8) then
                 begin
                  t.Add('test 14: GetRow ha retornado una matriz con con valores ineperados');
                  t.add('Esperaba (2,5,8) y obtengo ('+formatfloat('0', b.getValue(0,0))+','+formatfloat('0', b.getValue(1,0))+','+formatfloat('0', b.getValue(2,0))+')'   );
                 end;
            end;
        end;
     A.free;
     B.free;


     //suma
     A := TMatriz.create(2,3);
     B := TMatriz.create(2,3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,7);

     B.setValue(0,0,2);B.setValue(0,1,5);B.setValue(0,2,8);
     B.setValue(1,0,3);B.setValue(1,1,7);B.setValue(1,2,9);

     C := A.suma(B);
     if (C.RowCount() <> 2) or (C.ColCount()<>3) then t.Add('test 15: suma de matrices ha retornado una matriz de forma inesperada')
     else
     begin
      if (C.getValue(0, 0) <> 3) or (C.getValue(0, 1) <> 7) or (C.getValue(0, 2) <> 11)
      or (C.getValue(1, 0) <> 7) or (C.getValue(1, 1) <> 12) or (C.getValue(1, 2) <> 16) then
      t.Add('test 16: suma de matrices ha retornado valores inesperados');
     end;
     A.free;
     B.free;
     C.free;

     //resta
     A := TMatriz.create(2,3);
     B := TMatriz.create(2,3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,7);

     B.setValue(0,0,2);B.setValue(0,1,5);B.setValue(0,2,8);
     B.setValue(1,0,3);B.setValue(1,1,7);B.setValue(1,2,9);

     C := A.resta(B);
     if (C.RowCount() <> 2) or (C.ColCount()<>3) then t.Add('test 17: resta de matrices ha retornado una matriz de forma inesperada')
     else
     begin
      if (C.getValue(0, 0) <> -1) or (C.getValue(0, 1) <> -3) or (C.getValue(0, 2) <> -5)
      or (C.getValue(1, 0) <> 1) or (C.getValue(1, 1) <> -2) or (C.getValue(1, 2) <> -2) then
      t.Add('test 18: reta de matrices ha retornado valores inesperados');
     end;
     A.free;
     B.free;
     C.free;

     //producto
     A := TMatriz.create(2,3);
     B := TMatriz.create(3,2);

     A.setValue(0,0,1);A.setValue(0,1,0);A.setValue(0,2,0);
     A.setValue(1,0,3);A.setValue(1,1,4);A.setValue(1,2,2);

     B.setValue(0,0,2);B.setValue(0,1,1);
     B.setValue(1,0,0);B.setValue(1,1,3);
     B.setValue(2,0,1);B.setValue(2,1,0);

     C := A.producto(B);
     if (C.RowCount() <> 2) or (C.ColCount()<>2) then t.Add('test 18: Producto A*B ha retornado una matriz de forma incorrecta')
     else
     begin
          if (C.getValue(0,0) <> 2) or (C.getValue(0,1) <> 1)
          or (C.getValue(1,0) <> 8) or (C.getValue(1,1) <> 15) then
          t.Add('test 19: Producto A*B ha retornado valores incorrectos');
     end;
     C.free;
     C := B.producto(A);
     if (C.RowCount() <> 3) or (C.ColCount()<>3) then t.Add('test 20: Producto B*A ha retornado una matriz de forma incorrecta')
     else
     begin
          if (C.getValue(0,0) <> 5) or (C.getValue(0,1) <> 4) or (C.getValue(0,2) <> 2)
          or (C.getValue(1,0) <> 9) or (C.getValue(1,1) <> 12) or (C.getValue(1,2) <> 6)
          or (C.getValue(2,0) <> 1) or (C.getValue(2,1) <> 0) or (C.getValue(2,2) <> 0) then
          t.Add('test 21: Producto B*A ha retornado valores incorrectos');
     end;
     C.free;
     A.free;
     b.free;

     //traspuesta
     A := TMatriz.create(2,3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,6);
     B := A.traspuesta();
     if (B.RowCount() <> 3) or (B.ColCount()<>2) then
        t.add('text 22: La forma de la matriz transpuesta no es correcta')
     else
     begin
          if (B.getValue(0,0) <> 1) or (B.getValue(0,1) <> 4)
          or (B.getValue(1,0) <> 2) or (B.getValue(1,1) <> 5)
          or (B.getValue(2,0) <> 3) or (B.getValue(2,1) <> 6) then
          t.add('test 23: La matriz traspuesta tiene valores inesperados');
     end;
     B.Free;
     A.free;

     //absolutematrix
     A := TMatriz.create(2,2);
     A.setValue(0,0,1);A.setValue(0,1,-2);
     A.setValue(1,0,0);A.setValue(1,1, 2.5);
     B := A.absoluteMatrix();
     if (B.RowCount() <> 2) or (B.ColCount()<>2) then
        t.add('text 24: La matriz absoluta tiene una forma incorrecta')
     else
     begin
          if (B.getValue(0,0) <> 1) or (B.getValue(0,1) <> 2)
          or (B.getValue(1,0) <> 0) or (B.getValue(1,1) <> 2.5) then
          t.add('test 25: La matriz absoluta tiene valores inesperados');
     end;
     B.Free;
     A.free;

     //setRow
     A:=TMatriz.create(1,3);
     A.setValue(0,0,2);A.setValue(0,1,3);A.setValue(0,2,4);
     B:=TMatriz.create(3,3);
     B.setRow(1, A);
       if (B.getValue(0,0) <> 0) or (B.getValue(0,1) <> 0) or (B.getValue(0,2) <> 0)
       or (B.getValue(1,0) <> 2) or (B.getValue(1,1) <> 3) or (B.getValue(1,2) <> 4)
       or (B.getValue(2,0) <> 0) or (B.getValue(2,1) <> 0) or (B.getValue(2,2) <> 0) then
       t.add('test 26: SetRow(Matriz) produjo valores insperados');
     A.free;
     B.free;

     //setRow
     SetLength(numbers, 3);
     numbers[0] := 6;     numbers[1] := 7;     numbers[2] := 8;
     B:=TMatriz.create(3,3);
     B.setRow(1, numbers);
       if (B.getValue(0,0) <> 0) or (B.getValue(0,1) <> 0) or (B.getValue(0,2) <> 0)
       or (B.getValue(1,0) <> 6) or (B.getValue(1,1) <> 7) or (B.getValue(1,2) <> 8)
       or (B.getValue(2,0) <> 0) or (B.getValue(2,1) <> 0) or (B.getValue(2,2) <> 0) then
       t.add('test 27: SetRow(Tnumbers) produjo valores insperados');
     B.free;

     //submatriz
     A:=TMatriz.create(4, 4);
     A.setValue(0,0,0);A.setValue(0,1,0);A.setValue(0,2,0);A.setValue(0,3,0);
     A.setValue(1,0,1);A.setValue(1,1,2);A.setValue(1,2,3);A.setValue(1,3,0);
     A.setValue(2,0,4);A.setValue(2,1,5);A.setValue(2,2,6);A.setValue(2,3,0);
     A.setValue(3,0,0);A.setValue(3,1,0);A.setValue(3,2,0);A.setValue(3,3,0);
     B:=A.subMatriz(1,0,2,2);
     if (B.RowCount()<>2) or (B.ColCount()<>3) then
     begin
        t.add('test 28: Submatriz produjo una matriz con forma inesperada');
        t.add(B.ToString()) ;
     end
     else
     begin
      if (B.getValue(0,0)<>1) or(B.getValue(0,1)<>2) or(B.getValue(0,2)<>3)
      or (B.getValue(1,0)<>4) or(B.getValue(1,1)<>5) or(B.getValue(1,2)<>6) then
      begin
         t.add('test 29: Submatriz produjo una matriz con valores ineperados');
         t.add(B.ToString()) ;
      end;
     end;
     A.free;
     B.free;


     //reordenar para reducir
     A:=TMatriz.create(3,4);
     A.setValue(0,0,3);A.setValue(0,1,-1);A.setValue(0,2,8);A.setValue(0,3,0);
     A.setValue(1,0,2);A.setValue(1,1,0);A.setValue(1,2,5);A.setValue(1,3,9);
     A.setValue(2,0,-4);A.setValue(2,1,3);A.setValue(2,2,1);A.setValue(2,3,0);
     B := A.reordenarParaReducir();
     A.free;
     b.free;

     //escalonar
     A:=TMatriz.create(3,4);
     A.setValue(0,0,3);A.setValue(0,1,-1);A.setValue(0,2,8);A.setValue(0,3,0);
     A.setValue(1,0,2);A.setValue(1,1,0);A.setValue(1,2,5);A.setValue(1,3,9);
     A.setValue(2,0,-4);A.setValue(2,1,3);A.setValue(2,2,1);A.setValue(2,3,0);
     B:=A.escalonada();
     A.free;
     b.free;

     //reducir
     A:=TMatriz.create(3,4);
     A.setValue(0,0,3);A.setValue(0,1,-1);A.setValue(0,2,8);A.setValue(0,3,0);
     A.setValue(1,0,2);A.setValue(1,1,0);A.setValue(1,2,5);A.setValue(1,3,5);
     A.setValue(2,0,-4);A.setValue(2,1,3);A.setValue(2,2,1);A.setValue(2,3,0);
     B:=A.reducir();
     n1 := B.getValue(0,3);
     n2 := B.getValue(1,3);
     n3 := B.getValue(2,3);
     if (not equals(n1,5,3)) or (not equals(n2,7,3)) or (not equals(n3,-1,3)) then
     begin
        t.Add('test 30: Reducir matriz produjo valores inesperados');
        t.add('Se esperaba A=5,B=7,C=-1');
        t.add('Se obtuvo A='+ formatFloat('0.00', n1) + ',B='+formatFloat('0.00', n2) + ',C='+formatFloat('0.00', n3));
        t.add(b.ToString());
     end;
     a.free;
     b.free;

     //esinvertible
     A:=TMatriz.create(3,4);
     if (A.esInvertible()) then t.add('test 31: Una matriz no cuadrada no puede ser invertible');
     a.free;
     A:=TMatriz.create(3,3);
     if (A.esInvertible()) then t.add('test 32: Una matriz ccuadra con determinante 0 no es invertible');
     a.free;
     A:=TMatriz.create(3,3);
     A.setValue(0,0,2);A.setValue(0,1,5);A.setValue(0,2,6);
     A.setValue(1,0,1);A.setValue(1,1,2);A.setValue(1,2,3);
     A.setValue(2,0,4);A.setValue(2,1,4);A.setValue(2,2,8);
     if (not A.esInvertible()) then t.add('test 33: Una matriz ccuadra con determinante <>0 es invertible');
     a.free;

     //inversa
     A:=TMatriz.create(3,3);
     A.setValue(0,0,2);A.setValue(0,1,5);A.setValue(0,2,6);
     A.setValue(1,0,1);A.setValue(1,1,2);A.setValue(1,2,3);
     A.setValue(2,0,4);A.setValue(2,1,4);A.setValue(2,2,8);
     b:=A.inversa();
     if (b.RowCount()<>3) or (b.ColCount()<>3) then
        t.Add('test 34: Inversa produjo una matriz de forma inesperada')
     else
     begin
          if (not equals(B.getValue(0, 0), 1, 3)) or (not equals(B.getValue(0, 1), -4, 3)) or (not equals(B.getValue(0, 2), 0.75, 3))
          or (not equals(B.getValue(1, 0), 1, 1)) or (not equals(B.getValue(1, 1), -2, 3)) or (not equals(B.getValue(1, 2), 0, 3))
          or (not equals(B.getValue(2, 0), 1, -1)) or (not equals(B.getValue(2, 1), 3, 3)) or (not equals(B.getValue(2, 2), -0.25, 3))
          then
          begin
             t.add('test 35: Inversa produjo una matriz con valores inesperados');
             t.add(B.ToString());
          end;
     end;
     a.free;
     b.free;

     //Adjunta(f,c)
     A:=TMatriz.create(3,4);
     A.setValue(0, 0, 1);A.setValue(0, 1, 2);A.setValue(0, 2, 3);A.setValue(0, 3, 4);
     A.setValue(1, 0, 5);A.setValue(1, 1, 6);A.setValue(1, 2, 7);A.setValue(1, 3, 8);
     A.setValue(2, 0, 9);A.setValue(2, 1, 10);A.setValue(2, 2, 11);A.setValue(2, 3, 12);
     B:=A.adjunta(0, 0);
     if (B.RowCount()<>2) or (B.ColCount()<>3) then
        t.Add('test 36: Matriz adjunta(f, c) ha retornado una matriz de tamaño inesperado')
     else
     begin
          if (B.getValue(0, 0) <> 6) or (B.getValue(0, 1) <> 7) or(B.getValue(0, 2) <> 8) or
             (B.getValue(1, 0) <> 10) or (B.getValue(1, 1) <> 11) or(B.getValue(1, 2) <> 12) then
             t.Add('test 37: Matriz adjunta(f, c) ha retornado una matriz con valores inesperados');
     end;
     A.free;
     B.free;

     //Adjunta(f,c)
     A:=TMatriz.create(3,4);
     A.setValue(0, 0, 1);A.setValue(0, 1, 2);A.setValue(0, 2, 3);A.setValue(0, 3, 4);
     A.setValue(1, 0, 5);A.setValue(1, 1, 6);A.setValue(1, 2, 7);A.setValue(1, 3, 8);
     A.setValue(2, 0, 9);A.setValue(2, 1, 10);A.setValue(2, 2, 11);A.setValue(2, 3, 12);
     B:=A.adjunta(1, 1);
     if (B.RowCount()<>2) or (B.ColCount()<>3) then
        t.Add('test 38: Matriz adjunta(f, c) ha retornado una matriz de tamaño inesperado')
     else
     begin
          if (B.getValue(0, 0) <> 1) or (B.getValue(0, 1) <> 3) or(B.getValue(0, 2) <> 4) or
             (B.getValue(1, 0) <> 9) or (B.getValue(1, 1) <> 11) or(B.getValue(1, 2) <> 12) then
             t.Add('test 39: Matriz adjunta(f, c) ha retornado una matriz con valores inesperados');
     end;
     A.free;
     B.free;


     //Adjunta
     A:=TMatriz.create(3,3);
     A.setValue(0, 0, 0);A.setValue(0, 1, 1);A.setValue(0, 2, 0);
     A.setValue(1, 0, 1);A.setValue(1, 1, 0);A.setValue(1, 2, 0);
     A.setValue(2, 0, 1);A.setValue(2, 1, 0);A.setValue(2, 2, 1);
     B:=A.adjunta();
     if (B.RowCount()<>3) or (B.ColCount()<>3) then
        t.Add('test 40: Matriz adjunta ha retornado una matriz de tamaño inesperado')
     else
     begin
          if (B.getValue(0, 0) <> 0) or (B.getValue(0, 1) <> -1) or(B.getValue(0, 2) <> 0) or
             (B.getValue(1, 0) <> -1) or (B.getValue(1, 1) <> 0) or(B.getValue(1, 2) <> 1) or
             (B.getValue(2, 0) <> 0) or (B.getValue(2, 1) <> 0) or(B.getValue(2, 2) <>  -1) then
             t.Add('test 41: Matriz adjunta ha retornado una matriz con valores inesperados');
     end;
     A.free;
     B.free;



     //determinante
        //No cuadrada            //error
        A := TMatriz.create(2,3);
        A.setIdentidad();
        if not equals(A.determinante(),0,3) then t.Add('test 42: El determinante de una matriz no cuadrada es 0, se obtiene '+formatFloat('0.0', A.determinante()));
        A.free;

        //1x1
        A := TMatriz.create(1,1);
        A.setIdentidad();
        if A.determinante() <> 1 then t.Add('test 43: El determinante de una matriz 1x1 es el valor de V(0,0) '+formatFloat('0.0', A.determinante()));
        A.free;

        //2x2
        A := TMatriz.create(2,2);
        A.setValue(0,0, 1); A.setValue(0,1,2);
        A.setValue(1,0, 3); A.setValue(1,1,4);
        if not equals(A.determinante(),-2,3) then t.Add('test 44: El determinante de una matriz 2x2 arrojo un resultado inesperado '+formatFloat('0.0', A.determinante()));
        A.free;
        //3x3

        A := TMatriz.create(3,3);       //error
        A.setValue(0,0, 1); A.setValue(0,1,2); A.setValue(0,2,3);
        A.setValue(1,0, 4); A.setValue(1,1,5); A.setValue(1,2,4);
        A.setValue(2,0, 7); A.setValue(2,1,8); A.setValue(2,2,1);
        if not equals(A.determinante(),12, 3) then t.Add('test 45: El determinante de una matriz 3x3 arrojo un resultado inesperado '+formatFloat('0.0', A.determinante()));
        A.free;

        //NxN
        A := TMatriz.create(4,4);       //error
        A.setValue(0,0, 2); A.setValue(0,1,3); A.setValue(0,2,-2);A.setValue(0,3,4);
        A.setValue(1,0, 3); A.setValue(1,1,-2); A.setValue(1,2,1);A.setValue(1,3,2);
        A.setValue(2,0, 3); A.setValue(2,1, 2); A.setValue(2,2,3);A.setValue(2,3,4);
        A.setValue(3,0,-2); A.setValue(3,1, 4); A.setValue(3,2,0);A.setValue(3,3,5);
        if not equals(A.determinante(),-286, 3) then t.Add('test 46: El determinante de una matriz 4x4 arrojo un resultado inesperado '+formatFloat('0.0', A.determinante()));
        A.free;

     A:= TMatriz.create(4,4);
     A.setValue(0, 3, 1);
     A.setValue(1, 3, 2);
     A.setValue(2, 3, 3);

     //get X,Y,Z
     if A.getX() <> 1 then t.Add('test 47: getX retorno un valor inesperado');
     if A.getY() <> 2 then t.Add('test 48: getY retorno un valor inesperado');
     if A.getZ() <> 3 then t.Add('test 49: getZ retorno un valor inesperado');

     A.free;

     t.add('Fin de test TMatriz');

     result := t.Text;
end;


end.

