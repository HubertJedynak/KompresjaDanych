package mainPackage;

import Elementy.*;
import javafx.application.Application;
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
import panele.Tlo;
import panele.Usuwanie;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    //WAZNE:
    // PAMIETAJ O ITEROWANIU INDEKSOW OBIEKTOW, JAK DODAJESZ NOWY OBIEKT DO OBRAZU
    //DETALICZNOSC DO TESTOWANIA

    public static List<Element> listaElementow = new LinkedList<>();

    public static int appWidth = 1300;
    public static int appHeight = 600;
    public static Canvas obraz;
    public static GraphicsContext graphicsContext;

    public static Button bOdswiez, bDodaj, bUsun, bTlo;

    public static Dodawanie pDodawanie;
    public static Usuwanie pUsuwanie;
    public static Tlo pTlo;


    public static Label lTrybRysowania;
    public static boolean trybRysowania = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        obraz = new Canvas(700,500);
        obraz.setLayoutX(25);
        obraz.setLayoutY(25);
        pane.getChildren().add(obraz);
        graphicsContext = obraz.getGraphicsContext2D();

        obraz.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double xClicked = event.getX();
                double yClicked = event.getY();

                if (trybRysowania == true) {
                    trybRysowania = false;
                    lTrybRysowania.setText("tryb rysowania OFF");

                    double r = Dodawanie.sR.getValue();
                    double g = Dodawanie.sG.getValue();
                    double b = Dodawanie.sB.getValue();
                    double detale = Dodawanie.sDetalicznosc.getValue();

                    long seed = Long.parseLong(Dodawanie.tfSeed.getText());
                    Element element;
                    if(Dodawanie.cbWzorce.getValue().equals("Trawa")){
                        element = new Trawa(xClicked,yClicked,r,g,b,(int)detale,seed);
                    }else if(Dodawanie.cbWzorce.getValue().equals("Gory")){
                        element = new Gory(xClicked,yClicked,r,g,b,(int)detale,seed);
                    }else if(Dodawanie.cbWzorce.getValue().equals("Chmury")){
                        element = new Chmury(xClicked,yClicked,r,g,b,(int)detale,seed);
                    }else if(Dodawanie.cbWzorce.getValue().equals("Drzewa")){
                        element = new Drzewa(xClicked,yClicked,r,g,b,(int)detale,seed);
                    }
                    else {
                        element = new Trawa(xClicked,yClicked,r,g,b,(int)detale,seed);
                    }
                    listaElementow.add(Dodawanie.warstwa,element);
                    rysujKrajobraz(graphicsContext,obraz);
                    Usuwanie.cbElementy.getItems().add(listaElementow.size()-1);
                    Main.pDodawanie.setVisible(true);
                    Main.bDodaj.setVisible(true);
                    Main.bUsun.setVisible(true);
                    Main.bTlo.setVisible(true);
                    Main.bOdswiez.setVisible(true);

//                    new Thread(() -> {
//                        // dodac rysowanie (tryb rysowania?)
//                            new Trawa().rysuj(graphicsContext,xClicked,yClicked);
//                    }).start();
                }
            }
        });

        bDodaj = new Button();
        bDodaj.setText("dodaj");
        bDodaj.setLayoutX(900);
        bDodaj.setLayoutY(25);
        bDodaj.setPrefSize(100,30);
        bDodaj.setOnAction(event -> {
            pDodawanie.setVisible(true);
            pUsuwanie.setVisible(false);
            pTlo.setVisible(false);
        });
        pane.getChildren().add(bDodaj);



        bUsun = new Button();
        bUsun.setText("usun");
        bUsun.setLayoutX(1050);
        bUsun.setLayoutY(25);
        bUsun.setPrefSize(100,30);
        bUsun.setOnAction(event -> {
            pDodawanie.setVisible(false);
            pUsuwanie.setVisible(true);
            pTlo.setVisible(false);
        });
        pane.getChildren().add(bUsun);

        bTlo = new Button();
        bTlo.setText("tlo");
        bTlo.setLayoutX(1200);
        bTlo.setLayoutY(25);
        bTlo.setPrefSize(100,30);
        bTlo.setOnAction(event -> {
            pDodawanie.setVisible(false);
            pUsuwanie.setVisible(false);
            pTlo.setVisible(true);
        });
        pane.getChildren().add(bTlo);


        lTrybRysowania = new Label();
        lTrybRysowania.setLayoutX(200);
        lTrybRysowania.setLayoutY(550);
        lTrybRysowania.setPrefSize(200,30);
        lTrybRysowania.setText("tryb rysowania OFF");
        pane.getChildren().add(lTrybRysowania);

        bOdswiez = new Button();
        bOdswiez.setText("odswiez");
        bOdswiez.setLayoutX(750);
        bOdswiez.setLayoutY(25);
        bOdswiez.setPrefSize(100,30);
        bOdswiez.setOnAction(event -> {
            rysujKrajobraz(graphicsContext,obraz);

        });
        pane.getChildren().add(bOdswiez);



        pDodawanie = new Dodawanie();
        pane.getChildren().add(pDodawanie);

        pUsuwanie = new Usuwanie();
        pUsuwanie.setVisible(false);
        pane.getChildren().add(pUsuwanie);

        pTlo = new Tlo();
        pTlo.setVisible(false);
        pane.getChildren().add(pTlo);


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
                //graphicsContext.setFill(Color.STEELBLUE);
                graphicsContext.fillRect(0,0,obraz.getWidth(),obraz.getHeight());
//                rysuj(obraz,graphicsContext);
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

    public static void rysujKrajobraz(GraphicsContext graphicsContext, Canvas obraz){

        new Thread(() -> {
            graphicsContext.setFill(new Color(Tlo.sR.getValue(),Tlo.sG.getValue(),Tlo.sB.getValue(),1));
            graphicsContext.fillRect(0,0,obraz.getWidth(),obraz.getHeight());
            // RYSOWANIE TLA ZROBIC TU

            for (int i = listaElementow.size() - 1; i >= 0;i--){
                //graphicsContext.setFill(Color.SPRINGGREEN);
                listaElementow.get(i).rysuj();
            }
        }).start();
        Dodawanie.lWarstwaWartosc.setText(Dodawanie.warstwa + "/" + listaElementow.size());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
