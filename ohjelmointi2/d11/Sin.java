
package d11;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * Ohjelmalla "piirretään" konsoliin sinin kuvaaja annetuilla tiedoilla.
 * @author Viljami & Valtteri Järvinen
 * @version 1.0 2.4.2018
 */
public class Sin {

    /**
     *Rajapinta kaikille R->R
     */
    public interface FunktioRR {
        /**
        *luodaan funktio 
        * @param x funktio matemaattisessa muodossa
        * @return funktio x
        */
        public double f(double x);
    }

    /**
     * luokka itse tehdylle funktiolle
    */
    static class Funktioni implements FunktioRR {
        @Override
        public double f(double x) {
            return 2 * x - 6;
        }
    }
    
    /**
     * luokka sinifunktiolle
     * @author vilij
     * @version 2.4.2018
     * @example
     * <pre name="test">
     * </pre>
     */
    public static class SinFun implements FunktioRR {
        @Override
        public double f(double x) {
            return Math.sin(x);
        }
    }

    /**
     * Luokka 2-asteen polynomille
     */
    public static class P2 implements FunktioRR {
        private double a;
        private double b;
        private double c;

        /**
         * Alustetaan polynomiksi x^2 
         */
        public P2() {
            a = 1;
        }


        /**
         * Alustetaan polynomi kertoimilla
         * @param a x^2 kerroin
         * @param b x:n kerroin
         * @param c vakiotermi
         */
        public P2(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public double f(double x) {
            return a * x * x + b * x + c;
        }
        
        @Override
        public String toString() {
            return a + "x^2 + " + b + "x + " + c;
        }

    }
    

  
    /**
     * @param f piirrettävä funktio-olio
     * @param x1 x:n alkuarvo
     * @param x2 x:n loppuarvo
     * @param y1 y:n alkuarvo
     * @param y2 y:n loppuarvo
     * @param dx -
     */
    public static void piirra(FunktioRR f, double x1, double x2, double y1, double y2, double dx) {
        int leveys = 50; //tähän en keksinyt mitään järkevää muuta kuin asettamalla
        double suhde;
        if(Math.abs(y1) >= Math.abs(y2))
            suhde = Math.abs(y1);
        
        else 
            suhde = Math.abs(y2);
    
    for(double x = x1; x <= x2; x += dx) {
        int n = (int) ( Math.round((f.f(x) /suhde) * (leveys/2) + (leveys/2)));
        System.out.println(Mjonot.tyhja(n)+ "*");
        }
    }
    
    /**
     *@example
     * <pre name="test">
     * FunktioRR f = new SinFun();
     *funMax(f, -5, 5, 0.5) ~~~ -1.0;
     * </pre>
     */
    private static double funMax(FunktioRR f, double x1, double x2, double dx) {
      double minimi = f.f(x2);  
      for(double x=x1 + dx/2; x<x2; x+=dx) {
          double tulos =f.f(x);
          if(tulos<minimi) minimi = tulos;
      }
      return minimi;
    }
    
    /**
     * @param f
     * @param x1
     * @param x2
     * @param dx
     * @return funktion maksimi
     *@example
     * <pre name="test">
     * FunktioRR f = new SinFun();
     *funMin(f, -5, 5, 0.5) ~~~ 1.0;
     * </pre>
     */
    private static double funMin(FunktioRR f, double x1, double x2, double dx) {
        double maximi = f.f(x2);  
        for(double x=x1 + dx/2; x<x2; x+=dx) {
            double tulos =f.f(x);
            if(tulos>maximi) maximi = tulos;
        }
        return maximi;
      }
    
    /**
     * Pääohjelma luokan testaamiseen
    * @param args ei käytössä
    */
    public static void main(String[] args) {
        double x1 = -5, x2 = 5, dx = 0.5, y1, y2;
        String s;
        FunktioRR f = new SinFun();

        System.out.println("Piirrän funktion sin(x)");
        s = Syotto.kysy("Anna väli ja tiheys jolla piirretään",
                Mjonot.fmt(x1, 4, 2) + " " + Mjonot.fmt(x2, 4, 2) + " "
                        + Mjonot.fmt(dx, 4, 2));
        StringBuffer sb = new StringBuffer(s);
        x1 = Mjonot.erota(sb, ' ', x1);
        x2 = Mjonot.erota(sb, ' ', x2);
        dx = Mjonot.erota(sb, ' ', dx);

        y1 = funMin(f, x1, x2, dx);
        y2 = funMax(f, x1, x2, dx);

        piirra(f, x1, x2, y1, y2, dx);
        System.out.println(y1);
        System.out.println(y2);
        

    }
}