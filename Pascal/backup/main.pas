unit main;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, ExtCtrls,
  StdCtrls, ComCtrls, utrobot, utnodo, uTmatriz, math, Types;

type

  { TForm1 }

  TForm1 = class(TForm)
    ComboBox1: TComboBox;
    Label9: TLabel;
    Memo1: TMemo;
    transX: TEdit;
    transY: TEdit;
    transZ: TEdit;
    rotX: TEdit;
    rotZ: TEdit;
    rotY: TEdit;
    Image1: TImage;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Panel1: TPanel;
    UpDown1: TUpDown;
    UpDown2: TUpDown;
    UpDown3: TUpDown;
    UpDown4: TUpDown;
    UpDown5: TUpDown;
    UpDown6: TUpDown;
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure ComboBox1Change(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure Image1MouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure Image1MouseLeave(Sender: TObject);
    procedure Image1MouseMove(Sender: TObject; Shift: TShiftState; X, Y: Integer
      );
    procedure Image1MouseUp(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure Image1MouseWheelDown(Sender: TObject; Shift: TShiftState;
      MousePos: TPoint; var Handled: Boolean);
    procedure Image1MouseWheelUp(Sender: TObject; Shift: TShiftState;
      MousePos: TPoint; var Handled: Boolean);
    procedure rotXExit(Sender: TObject);
    procedure transXExit(Sender: TObject);
    procedure UpDown1Click(Sender: TObject; Button: TUDBtnType);
    procedure UpDown4Click(Sender: TObject; Button: TUDBtnType);
  private
         lastMovex, lastMovey : integer;
         rotating : boolean;
         nodoProy : TNodo;
         robot : TRobot;
         function getNodo(): TNodo;
         procedure paintRobot();
  public

  end;

var
  Form1: TForm1;

implementation

{$R *.lfm}

{ TForm1 }

procedure TForm1.paintRobot();
var
  I : Integer;
  nodo : TNodo;
  matriz : TMatriz;
  a : TStringList;
  lastx: integer;
  lasty : integer;
  x, y : integer;
begin

  Image1.Canvas.Pen.color := clWhite;
  Image1.Canvas.Brush.color := clWhite;
  Image1.Canvas.FillRect(0, 0, Image1.Width, Image1.Height);
  robot.draw(Image1.canvas, nodoProy.getMatrizTransf());

  memo1.lines.Clear;
  memo1.lines.add('Mat. Trans. nodo '+ComboBox1.Text) ;
  memo1.Lines.add(getNodo().getMatrizTransfGlobal().ToString());
  memo1.lines.add('');
  memo1.lines.add('Posición global del TCP '+ComboBox1.text);
  matriz := getNodo().getGlobalCoordinates();
  memo1.lines.add('X= '+formatFloat('#0.00', matriz.getValue(0,0)) + ' Y= '+formatFloat('#0.00', matriz.getValue(1,0)) + ' Z='+formatFloat('#0.00', matriz.getValue(2,0)));
  matriz.free;

end;

function TForm1.getNodo(): TNodo;
begin
  result := robot.getNodo(strToInt(ComboBox1.Items[ComboBox1.ItemIndex])-1);
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
  rotating:=false;
  robot := TRobot.create(6);
  robot.trasladarY(0, 300);
  robot.rotarX(1, 90);
  robot.trasladarX(1, 50);
  robot.trasladarX(2, 150);
  robot.trasladarX(3, 50);
  robot.trasladarX(4, 100);
  robot.trasladarX(5, 50);
  nodoProy := TNodo.create;
end;

procedure TForm1.FormShow(Sender: TObject);
begin
  paintRobot();
end;

procedure TForm1.Image1MouseDown(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
begin
  rotating := true;
  lastMovex:=x;
  lastMovey:=y;
end;

procedure TForm1.Image1MouseLeave(Sender: TObject);
begin
 rotating:=false;
end;

procedure TForm1.Image1MouseMove(Sender: TObject; Shift: TShiftState; X,
  Y: Integer);
begin
 if (rotating) then
  begin

     if (ssCtrl in Shift) then
      begin
         //if (y > lastMovey) then nodoProy.sety(nodoProy.gety()+1);
         //if (y < lastMovey) then nodoProy.sety(nodoProy.gety()-1);

         //if (x > lastMovex) then nodoProy.setx(nodoProy.getx()-1);
         //if (x < lastMovex) then nodoProy.setx(nodoProy.getx()+1);

         nodoProy.setX(nodoProy.getX() + (lastMovex-x));
         nodoProy.setY(nodoProy.getY() + (y-lastMovey));
      end
     else
     begin
       if (y > lastMovey) then nodoProy.rotarX(nodoProy.getRotacionX() + 1);
       if (y < lastMovey) then nodoProy.rotarX(nodoProy.getRotacionX() - 1);
       if (x > lastMovex) then nodoProy.rotarY(nodoProy.getRotacionY() + 1);
       if (x < lastMovex) then nodoProy.rotarY(nodoProy.getRotacionY() - 1);
     end;

    lastMovex:=x;
    lastMovey:=y;
    paintRobot();
  end;
end;

procedure TForm1.Image1MouseUp(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
begin
  rotating:=false;
end;

procedure TForm1.Image1MouseWheelDown(Sender: TObject; Shift: TShiftState;
  MousePos: TPoint; var Handled: Boolean);
begin
  nodoProy.setz(nodoProy.getz()+1);
  paintRobot();
end;

procedure TForm1.Image1MouseWheelUp(Sender: TObject; Shift: TShiftState;
  MousePos: TPoint; var Handled: Boolean);
begin
  nodoProy.setz(nodoProy.getz()-1);
  paintRobot();

end;

procedure TForm1.rotXExit(Sender: TObject);
begin
   try
      if (trim(rotX.text) <> '') and (trim(rotY.text) <> '') and (trim(rotZ.text) <> '') then
         getNodo().rotar(strToFloat(rotX.text), strToFloat(rotY.text), strToFloat(rotZ.text));
  finally
  end;
  paintRobot();
end;

procedure TForm1.transXExit(Sender: TObject);
begin
  try
     if (trim(transX.text) <> '') and (trim(transY.text) <> '') and (trim(transZ.text) <> '') then
     getNodo().setTCP(strToFloat(transX.text), strToFloat(transY.text), strToFloat(transZ.text));
  finally
  end;
  paintRobot();
end;

procedure TForm1.UpDown1Click(Sender: TObject; Button: TUDBtnType);
begin
  try
     if (trim(transX.text) <> '') and (trim(transY.text) <> '') and (trim(transZ.text) <> '') then
     getNodo().setTCP(strToFloat(transX.text), strToFloat(transY.text), strToFloat(transZ.text));
  finally
  end;
  paintRobot();
end;

procedure TForm1.UpDown4Click(Sender: TObject; Button: TUDBtnType);
begin
  try
     if (trim(rotX.text) <> '') and (trim(rotY.text) <> '') and (trim(rotZ.text) <> '') then
        getNodo().rotar(strToFloat(rotX.text), strToFloat(rotY.text), strToFloat(rotZ.text));
 finally
 end;
 paintRobot();
end;

procedure TForm1.ComboBox1Change(Sender: TObject);
var
  nodo : TNodo;
begin
  nodo := getNodo();
  UpDown1.Position:=Round(nodo.getX());
  UpDown2.Position:=Round(nodo.getY());
  UpDown3.Position:=Round(nodo.getZ());
  UpDown4.Position:=Round(nodo.getRotacionX());
  UpDown5.Position:=Round(nodo.getRotacionY());
  UpDown6.Position:=Round(nodo.getRotacionZ());
  paintRobot();
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
  paintRobot();
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
  //memo1.Lines.text := testMatriz();
  //memo1.Lines.add(robot.getNodo(1).getGlobalCoordinates().ToString());
  paintRobot();
end;

procedure TForm1.Button3Click(Sender: TObject);
begin
  showMessage(formatFloat('#0.00', sin(degtorad(90))));
end;

end.

