package Elementy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mainPackage.Main;

import java.util.Random;

public class Drzewa implements Element{

    long seed;
    int detale;
    double y;
    double x;

    double r=0,g=0,b=0;
    public Drzewa(double x, double y,double r,double g, double b, int detale, long seed){
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
        long s = seed;

        double hp = y;
        double hk = hp - ((int)Main.obraz.getHeight()/10);
        double dw = (hp - hk)/3;
        double dh = new Random(s).nextInt(detale/2);
        double w = x;

        while (hp - dh > hk) {
            s = new Random(s).nextLong();

            dw = new Random(s).nextInt((int)(y-(hp - hk)/3));
            hp = hp - new Random(s).nextInt(detale);


            double x[] = {w,w-dw,w,w+dw};
            double y[] = {hp,hk,hk,hk};
            //System.out.println(w+" "+dh);
            Main.graphicsContext.fillPolygon(x, y, x.length);
        }
    }

}
