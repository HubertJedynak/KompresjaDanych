package panele;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mainPackage.Main;

public class Tlo extends Pane {
    static Label lTlo;

    static Label lR,lG,lB;
    public static Slider sR,sG,sB;

    static Canvas cKolor;
    static GraphicsContext gcKolor;

    static Button bRysuj;

    public Tlo(){
        super();
        setLayoutX(900);
        setLayoutY(80);
        setPrefSize(400,500);

        lTlo = new Label();
        lTlo.setLayoutX(150);
        lTlo.setLayoutY(0);
        lTlo.setPrefSize(200,50);
        lTlo.setText("tlo");
        getChildren().add(lTlo);

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

        bRysuj = new Button();
        bRysuj.setLayoutX(0);
        bRysuj.setLayoutY(350);
        bRysuj.setPrefSize(100,30);
        bRysuj.setText("rysuj");
        bRysuj.setOnAction(event -> {
            // zrobic niewidzialny panel i guzik rysuj
//            Main.lTrybRysowania.setText("tryb rysowania ON");
//            Main.trybRysowania = true;
                Main.rysujKrajobraz(Main.graphicsContext,Main.obraz);
        });
        getChildren().add(bRysuj);


    }

}
