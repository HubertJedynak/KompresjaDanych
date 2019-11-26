package panele;

import Elementy.Element;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mainPackage.Main;

public class Dodawanie extends Pane {
    static Label lDodawanie;

    static Label lR,lG,lB;
    public static Slider sR,sG,sB;

    static Canvas cKolor;
    static GraphicsContext gcKolor;

    public static Label lWarstwa, lWarstwaWartosc;
    static Button bLewo, bPrawo;

    static Label lDetalicznosc;
    public static Slider sDetalicznosc;

    static Label lSeed;
    public static TextField tfSeed;

    static Button bRysuj;

    public static ChoiceBox<String> cbWzorce;

    public static int warstwa=0;

    public Dodawanie(){
        super();
        setLayoutX(900);
        setLayoutY(80);
        setPrefSize(400,500);

        lDodawanie = new Label();
        lDodawanie.setLayoutX(150);
        lDodawanie.setLayoutY(0);
        lDodawanie.setPrefSize(200,50);
        lDodawanie.setText("dodawanie");
        getChildren().add(lDodawanie);

//        l = new Label();
//        l.setLayoutX(150);
//        l.setLayoutY(0);
//        l.setPrefSize(200,50);
//        l.setText("dodawanie");
//        getChildren().add(l);

        lR = new Label();
        lR.setLayoutX(0);
        lR.setLayoutY(30);
        lR.setPrefSize(100,30);
        lR.setText("czerwony:");
        getChildren().add(lR);

        sR = new Slider();
        sR.setLayoutX(100);
        sR.setLayoutY(30);
        sR.setPrefSize(200,30);
        sR.setMax(1);
        sR.valueProperty().addListener((observable, oldValue, newValue) -> {
            gcKolor.setFill(new Color(sR.getValue(),sG.getValue(),sB.getValue(),1));
            gcKolor.fillRect(0,0,50,50);
        });
        getChildren().add(sR);

        lG = new Label();
        lG.setLayoutX(0);
        lG.setLayoutY(60);
        lG.setPrefSize(100,30);
        lG.setText("zielony:");
        getChildren().add(lG);

        sG = new Slider();
        sG.setLayoutX(100);
        sG.setLayoutY(60);
        sG.setPrefSize(200,30);
        sG.setMax(1);
        sG.valueProperty().addListener((observable, oldValue, newValue) -> {
            gcKolor.setFill(new Color(sR.getValue(),sG.getValue(),sB.getValue(),1));
            gcKolor.fillRect(0,0,50,50);
        });
        getChildren().add(sG);

        lB = new Label();
        lB.setLayoutX(0);
        lB.setLayoutY(90);
        lB.setPrefSize(100,30);
        lB.setText("niebieski:");
        getChildren().add(lB);

        sB = new Slider();
        sB.setLayoutX(100);
        sB.setLayoutY(90);
        sB.setPrefSize(200,30);
        sB.setMax(1);
        sB.valueProperty().addListener((observable, oldValue, newValue) -> {
            gcKolor.setFill(new Color(sR.getValue(),sG.getValue(),sB.getValue(),1));
            gcKolor.fillRect(0,0,50,50);
        });
        getChildren().add(sB);

        cKolor = new Canvas(50,50);
        cKolor.setLayoutX(140);
        cKolor.setLayoutY(120);
        gcKolor = cKolor.getGraphicsContext2D();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            }catch(Exception e) {};
            gcKolor.setFill(new Color(0,0,0,1));
            gcKolor.fillRect(0,0,50,50);
        }).start();
        getChildren().add(cKolor);

////////////////////////
        lWarstwa = new Label();
        lWarstwa.setLayoutX(0);
        lWarstwa.setLayoutY(180);
        lWarstwa.setPrefSize(100,30);
        lWarstwa.setText("warstwa:");
        getChildren().add(lWarstwa);

        bLewo = new Button();
        bLewo.setLayoutX(110);
        bLewo.setLayoutY(180);
        bLewo.setText("<-for");
        bLewo.setPrefSize(50,30);
        bLewo.setOnAction(event -> {
            if(warstwa>0){
                warstwa--;
                lWarstwaWartosc.setText(warstwa + "/" + Main.listaElementow.size());
            }
        });
        getChildren().add(bLewo);

        bPrawo = new Button();
        bPrawo.setLayoutX(170);
        bPrawo.setLayoutY(180);
        bPrawo.setText("back->");
        bPrawo.setPrefSize(60,30);
        bPrawo.setOnAction(event -> {
            if(warstwa < Main.listaElementow.size()){
                warstwa++;
                lWarstwaWartosc.setText(warstwa + "/" + Main.listaElementow.size());
            }
        });
        getChildren().add(bPrawo);

        lWarstwaWartosc = new Label();
        lWarstwaWartosc.setLayoutX(280);
        lWarstwaWartosc.setLayoutY(180);
        lWarstwaWartosc.setPrefSize(150,30);
        lWarstwaWartosc.setText("0/0");
        getChildren().add(lWarstwaWartosc);


    ////////////////
        lDetalicznosc = new Label();
        lDetalicznosc.setLayoutX(0);
        lDetalicznosc.setLayoutY(220);
        lDetalicznosc.setPrefSize(100,30);
        lDetalicznosc.setText("detalicznosc:");
        getChildren().add(lDetalicznosc);

        sDetalicznosc = new Slider();
        sDetalicznosc.setLayoutX(120);
        sDetalicznosc.setLayoutY(220);
        sDetalicznosc.setPrefSize(150,30);
        sDetalicznosc.setMin(4);
        sDetalicznosc.setMax(20);
        sDetalicznosc.setShowTickLabels(true);
        sDetalicznosc.valueProperty().addListener((observable, oldValue, newValue) -> {
            //
        });
        getChildren().add(sDetalicznosc);

        lSeed = new Label();
        lSeed.setLayoutX(0);
        lSeed.setLayoutY(260);
        lSeed.setPrefSize(100,30);
        lSeed.setText("seed:");
        getChildren().add(lSeed);

        tfSeed = new TextField();
        tfSeed.setLayoutX(150);
        tfSeed.setLayoutY(260);
        tfSeed.setPrefSize(100,30);
        tfSeed.setText("1234");
        tfSeed.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                int wartosc = Integer.parseInt(newValue);
            }catch (Exception e){
                tfSeed.setText(oldValue);
            }
        });
        getChildren().add(tfSeed);

        bRysuj = new Button();
        bRysuj.setLayoutX(0);
        bRysuj.setLayoutY(350);
        bRysuj.setPrefSize(100,30);
        bRysuj.setText("rysuj");
        bRysuj.setOnAction(event -> {
            // zrobic niewidzialny panel i guzik rysuj
            Main.lTrybRysowania.setText("tryb rysowania ON");
            Main.trybRysowania = true;
            Main.pDodawanie.setVisible(false);
            Main.bDodaj.setVisible(false);
            Main.bUsun.setVisible(false);
            Main.bTlo.setVisible(false);
            Main.bOdswiez.setVisible(false);
        });
        getChildren().add(bRysuj);

        cbWzorce = new ChoiceBox<>();
        cbWzorce.setLayoutX(150);
        cbWzorce.setLayoutY(300);
        cbWzorce.setPrefSize(100,50);
        cbWzorce.getItems().add("Trawa");
        cbWzorce.getItems().add("Gory");
        cbWzorce.getItems().add("Chmury");
       // cbWzorce.getItems().add("Drzewa");
        cbWzorce.setValue("Trawa");
        getChildren().add(cbWzorce);




    }

}
