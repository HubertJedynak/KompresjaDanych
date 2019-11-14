package mainPackage;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        Canvas canvas = new Canvas(400,400);
        canvas.setLayoutX(50);
        canvas.setLayoutY(50);
        pane.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double yClicked = event.getY();
                new Thread(() -> {
                    //try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
                    graphicsContext.setFill(Color.SPRINGGREEN);
                    rysuj(canvas,graphicsContext,yClicked);
                }).start();

            }
        });

        Button bTrawa = new Button();
        bTrawa.setText("trawa");
        bTrawa.setLayoutX(500);
        bTrawa.setLayoutY(100);
        bTrawa.setPrefSize(100,50);
        pane.getChildren().add(bTrawa);



        Scene scene = new Scene(pane,700,500);
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
                graphicsContext.setFill(Color.STEELBLUE);
                graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
                rysuj(canvas,graphicsContext);
        }).start();

    }
    public void rysuj(Canvas canvas,GraphicsContext graphicsContext,double yClicked){
        long seed = 2345;
        double w = 0;
        double dh = 500 - yClicked;

        while (w<500) {
            seed = new Random(seed).nextLong();

            double dh1 = dh + new Random(seed).nextInt(4) - (double)(4-1)/2;
            double dw = new Random(seed).nextInt(2);


            double x[] = {w,w,w+dw,w+dw};
            double y[] = {500,500-dh,500-dh1,500};
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
        double dh = 500/2;

        while (w<500) {
            seed = new Random(seed).nextLong();

            double dh1 = dh + new Random(seed).nextInt(4) - (double)(4-1)/2;
            double dw = new Random(seed).nextInt(2);


            double x[] = {w,w,w+dw,w+dw};
            double y[] = {500,500-dh,500-dh1,500};
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
