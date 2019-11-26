package Elementy;

import javafx.scene.paint.Color;
import mainPackage.Main;

import java.util.Random;

public class Gory implements Element{

    int detale;
    long seed;

    double y;
    double x;

    double r=0,g=0,b=0;
    public Gory(double x, double y,double r,double g, double b,int detale, long seed){
        this.detale = detale;
        this.seed =seed;

        this.x=x;
        this.y=y;

        this.r=r;
        this.g=g;
        this.b=b;

    }

    @Override
    public void rysuj() {
//        Main.graphicsContext.setFill(new Color(r,g,b,1));
//        Main.graphicsContext.fillOval(x,y,50,50);
        Main.graphicsContext.setFill(new Color(r,g,b,1));

        double w = 0;
        double wk = Main.obraz.getWidth();
        double dh = Main.obraz.getHeight()-y;
        long s = seed;
        int detale0 = (int)Main.obraz.getWidth()/4;
        while (w<wk) {
            s = new Random(s).nextLong();



            double dh1 = dh + new Random(s).nextInt(detale0) - (double)(detale0+1)*w/wk;
            double dw = new Random(s).nextInt(detale0);
            double ch = Main.obraz.getHeight();
           rysujOdcinek(w,ch-dh,w+dw,ch-dh1,s,detale);
//
//            double x[] = {w,w,w+dw,w+dw};
//            double ch = Main.obraz.getHeight();
//            double y[] = {ch,ch-dh,ch-dh,ch};
//
//            Main.graphicsContext.fillPolygon(x, y, x.length);
            dh = dh1;
            w = w + dw;
        }
    }

    public void rysujOdcinek(double xp, double yp, double xk, double yk, long s, int detale){
        Main.graphicsContext.setFill(new Color(r,g,b,1));

        double w = xp;
        double dh = Main.obraz.getHeight() - yp;
        while (w<xk) {
            s = new Random(s).nextLong();

            double dw = new Random(s).nextInt(detale);
            double dh1 = dh + new Random(s).nextInt(detale) - (double)(detale-1)/2 - dw*(yk-yp)/(xk-xp);
            //double dh1 = dh - dw*(yk-yp)/(xk-xp);


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
