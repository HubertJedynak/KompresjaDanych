package Elementy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mainPackage.Main;

import java.util.Random;

public class Trawa implements Element{

    long seed;
    int detale;
    double y;
    double x;

    double r=0,g=0,b=0;
    public Trawa(double x, double y,double r,double g, double b, int detale, long seed){
        this.seed = seed;
        this.detale = detale;
        this.x=x;
        this.y=y;

        this.r=r;
        this.g=g;
        this.b=b;

    }

    @Override
    public void rysuj() {
//        Main.graphicsContext.setFill(new Color(r,g,b,1));
//        Main.graphicsContext.fillRect(x,y,50,50);
        Main.graphicsContext.setFill(new Color(r,g,b,1));

        double w = 0;
        double dh = Main.obraz.getHeight()-y;
        long s = seed;
        while (w<Main.obraz.getWidth()) {
            s = new Random(s).nextLong();

            double dh1 = dh + new Random(s).nextInt(detale) - (double)(detale-1)/2;
            double dw = new Random(s).nextInt(detale);


            double x[] = {w,w,w+dw,w+dw};
            double ch = Main.obraz.getHeight();
            double y[] = {ch,ch-dh,ch-dh1,ch};
            //System.out.println(w+" "+dh);
            Main.graphicsContext.fillPolygon(x, y, x.length);
            dh = dh1;
            w = w + dw;
        }
    }

}
