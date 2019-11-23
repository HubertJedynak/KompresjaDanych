package mainPackage;

import Elementy.Element;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import panele.Dodawanie;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    //WAZNE:
    // PAMIETAJ O ITEROWANIU INDEKSOW OBIEKTOW, JAK DODAJESZ NOWY OBIEKT DO OBRAZU
    //DETALICZNOSC DO TESTOWANIA

    public static List<Element> listaElementow = new LinkedList<>();

    int appWidth = 1300;
    int appHeight = 600;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        Canvas obraz = new Canvas(700,500);
        obraz.setLayoutX(25);
        obraz.setLayoutY(25);
        pane.getChildren().add(obraz);
        GraphicsContext graphicsContext = obraz.getGraphicsContext2D();

        obraz.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double yClicked = event.getY();
                new Thread(() -> {
                    //try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
                    graphicsContext.setFill(Color.SPRINGGREEN);
                    rysuj(obraz,graphicsContext,yClicked);
                }).start();

            }
        });

        Button bDodaj = new Button();
        bDodaj.setText("dodaj");
        bDodaj.setLayoutX(900);
        bDodaj.setLayoutY(25);
        bDodaj.setPrefSize(100,30);
        pane.getChildren().add(bDodaj);

        Button bOdswiez = new Button();
        bOdswiez.setText("odswiez");
        bOdswiez.setLayoutX(750);
        bOdswiez.setLayoutY(25);
        bOdswiez.setPrefSize(100,30);
        pane.getChildren().add(bOdswiez);



        Dodawanie pDodawanie = new Dodawanie();
        pane.getChildren().add(pDodawanie);




//        ChoiceBox choiceBox = new ChoiceBox();
//        choiceBox.getItems().add("lala");
//        choiceBox.setLayoutX(500);
//        choiceBox.setLayoutY(200);
//        choiceBox.setPrefSize(100,50);
//        pane.getChildren().add(choiceBox);



        Scene scene = new Scene(pane,appWidth,appHeight);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        new Thread(() -> {
            try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
            graphicsContext.setFill(Color.STEELBLUE);
            graphicsContext.fillRect(0,0,obraz.getWidth(),obraz.getHeight());
            rysuj(obraz,graphicsContext);
        }).start();

    }
    public void rysuj(Canvas canvas,GraphicsContext graphicsContext,double yClicked){
        long seed = 2345;
        double w = 0;
        double dh = canvas.getHeight() - yClicked;

        while (w<canvas.getWidth()) {
            seed = new Random(seed).nextLong();

            double dh1 = dh + new Random(seed).nextInt(4) - (double)(4-1)/2;
            double dw = new Random(seed).nextInt(2);


            double x[] = {w,w,w+dw,w+dw};
            double ch = canvas.getHeight();
            double y[] = {ch,ch-dh,ch-dh1,ch};
            //System.out.println(w+" "+dh);
            graphicsContext.fillPolygon(x, y, x.length);
            dh = dh1;
            w = w + dw;
        }
    }



    public void rysuj(Canvas canvas,GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.GREEN);
        long seed = 1234;
        double w = 0;
        double dh = canvas.getHeight()/2;

        while (w<canvas.getWidth()) {
            seed = new Random(seed).nextLong();

            double dh1 = dh + new Random(seed).nextInt(4) - (double)(4-1)/2;
            double dw = new Random(seed).nextInt(2);


            double x[] = {w,w,w+dw,w+dw};
            double ch = canvas.getHeight();
            double y[] = {ch,ch-dh,ch-dh1,ch};
            //System.out.println(w+" "+dh);
            graphicsContext.fillPolygon(x, y, x.length);
            dh = dh1;
            w = w + dw;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}