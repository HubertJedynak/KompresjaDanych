package mainPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Canvas canvas = new Canvas(500,500);
        pane.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(pane,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
                rysuj(canvas,graphicsContext);
        }).start();


    }

    public void rysuj(Canvas canvas,GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.GREEN);
        double w = 0;
        double dh = 500/2;

        while (w<500) {
            double dh1 = dh + new Random().nextInt(50) - 25;
            double dw = new Random().nextInt(50);


            double x[] = {w,w,w+dw,w+dw};
            double y[] = {500,500-dh,500-dh1,500};
            System.out.println(w+" "+dh);
            graphicsContext.fillPolygon(x, y, x.length);
            dh = dh1;
            w = w + dw;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
