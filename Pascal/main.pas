unit main;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, ExtCtrls,
  StdCtrls, ComCtrls, utrobot, utnodo, uTmatriz, math;

type

  { TForm1 }

  TForm1 = class(TForm)
    Button1: TButton;
    Button2: TButton;
    Button3: TButton;
    ComboBox1: TComboBox;
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
    procedure rotXExit(Sender: TObject);
    procedure transXExit(Sender: TObject);
    procedure UpDown1Click(Sender: TObject; Button: TUDBtnType);
    procedure UpDown4Click(Sender: TObject; Button: TUDBtnType);
  private
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
  lastx, lasty : integer;
  x, y : integer;
begin

  Image1.Canvas.Pen.color := clWhite;
  Image1.Canvas.Brush.color := clWhite;
  Image1.Canvas.FillRect(0, 0, Image1.Width, Image1.Height);
  lastx := 0;
  lasty := 0;

  for I := 0 to 5 do
  begin
       nodo := robot.getNodo(I);
       matriz := nodo.getGlobalCoordinates();
       x := round(matriz.getValue(0,0));
       y := round(matriz.getValue(1,0));
       //image1.Canvas.Rectangle(0, 0, 0, 0);
       Image1.Canvas.Pen.color := clRed;
       image1.Canvas.Rectangle( x -5, y-5, x+5, y+5);

       Image1.Canvas.Pen.color := clBlack;
       image1.Canvas.Line(lastx, lasty, x, y);
       lastx := x;
       lasty:= y;

  end;


end;

function TForm1.getNodo(): TNodo;
begin
  result := robot.getNodo(strToInt(ComboBox1.Items[ComboBox1.ItemIndex])-1);
end;

procedure TForm1.FormCreate(Sender: TObject);
var
  I : Integer;
begin
  robot := TRobot.create(6);
  for I:=0 to 5 do
  begin
       robot.trasladarX(I,50);
       robot.trasladarY(I,50);
       robot.trasladarZ(I,50);
  end;
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
  transX.Text := formatFloat('##0.00', nodo.getX());
  transY.Text := formatFloat('##0.00', nodo.getY());
  transZ.Text := formatFloat('##0.00', nodo.getZ());
  rotX.Text := formatFloat('##0.00', nodo.getRotacionX());
  rotY.Text := formatFloat('##0.00', nodo.getRotacionY());
  rotZ.Text := formatFloat('##0.00', nodo.getRotacionZ());
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

