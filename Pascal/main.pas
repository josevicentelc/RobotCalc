unit main;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, ExtCtrls,
  StdCtrls, utrobot, utnodo, uTmatriz;

type

  { TForm1 }

  TForm1 = class(TForm)
    Button1: TButton;
    Button2: TButton;
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
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure ComboBox1Change(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure rotXExit(Sender: TObject);
    procedure transXExit(Sender: TObject);
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
begin

  Image1.Canvas.Pen.color := clWhite;
  Image1.Canvas.Brush.color := clWhite;
  Image1.Canvas.FillRect(0, 0, Image1.Width, Image1.Height);

  Image1.Canvas.Pen.color := clRed;
  for I := 0 to 6 do
  begin
       nodo := robot.getNodo(I);
       matriz := nodo.getCoordenadas();

       //image1.Canvas.Rectangle(0, 0, 0, 0);
       image1.Canvas.Rectangle(round(matriz.getx()-2), round(matriz.gety()-2), Round(matriz.getx()+2), Round(matriz.gety()+2));
  end;


end;

function TForm1.getNodo(): TNodo;
begin
  result := robot.getNodo(strToInt(ComboBox1.Items[ComboBox1.ItemIndex])-1);
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
  robot := TRobot.create(6);
end;

procedure TForm1.rotXExit(Sender: TObject);
begin
   try
      if (trim(rotX.text) <> '') and (trim(rotY.text) <> '') and (trim(rotZ.text) <> '') then
         getNodo().rotar(strToFloat(rotX.text), strToFloat(rotY.text), strToFloat(rotZ.text));
  finally
  end;
end;

procedure TForm1.transXExit(Sender: TObject);
begin
  try
     if (trim(transX.text) <> '') and (trim(transY.text) <> '') and (trim(transZ.text) <> '') then
     getNodo().setTCP(strToFloat(transX.text), strToFloat(transY.text), strToFloat(transZ.text));
  finally
  end;
end;

procedure TForm1.ComboBox1Change(Sender: TObject);
var
  nodo : TNodo;
begin
  nodo := getNodo();
  transX.Text := formatFloat('##0.00', nodo.getTranslacionX());
  transY.Text := formatFloat('##0.00', nodo.getTranslacionY());
  transZ.Text := formatFloat('##0.00', nodo.getTranslacionZ());
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
  memo1.Lines.text := testMatriz();
end;

end.

