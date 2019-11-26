package panele;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Dodawanie extends Pane {
    Label lDodawanie;

    Label lR,lG,lB;
    Slider sR,sG,sB;

    Canvas cKolor;
    GraphicsContext gcKolor;

    Label lWarstwa, lWarstwaWartosc;
    Button bLewo, bPrawo;

    Label lDetalicznosc;
    Slider sDetalicznosc;

    Label lSeed;
    TextField tfSeed;

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
        bLewo.setText("<-");
        bLewo.setPrefSize(50,30);
        getChildren().add(bLewo);

        bPrawo = new Button();
        bPrawo.setLayoutX(170);
        bPrawo.setLayoutY(180);
        bPrawo.setText("->");
        bPrawo.setPrefSize(50,30);
        getChildren().add(bPrawo);

        lWarstwaWartosc = new Label();
        lWarstwaWartosc.setLayoutX(280);
        lWarstwaWartosc.setLayoutY(180);
        lWarstwaWartosc.setPrefSize(120,30);
        lWarstwaWartosc.setText("0");
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

    }

}