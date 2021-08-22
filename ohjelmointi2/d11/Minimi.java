package d11;

/**
 * @author vilij
 * @version 1.4.2018
 *luokka funktion minimin selvittämiseksi
 */
public class Minimi{
    
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
     * luokka funktion säilyttämiseksi
     */
    static class Funktioni implements FunktioRR {
        @Override
        public double f(double x) { return 2*x-6; }
    }

    /**
     * @param funktio funktio jota tarkastellaa
     * @param a x:n alaraja
     * @param b x:n yläraja
     * @return funktion minimin
     * @example
     * <pre name="test">
     * FunktioRR f = new Funktioni();
     * funMin(f,-2,5) === -10.0;
     * funMin(f,0,5) === -6.0;
     * funMin(f,4,5) === 2.0;
     * funMin(f,7,5) === 4.0;
     * </pre>
     */
    public static double funMin(FunktioRR funktio, double a, double b) {
        int vali = 1000;
        double minimi = funktio.f(b);
        
        double dx = (b-a)/vali;
        for(double x=a; x<=b; x+=dx){
        if (funktio.f(x) < minimi){
            minimi = funktio.f(x);
            }
        }
        return minimi;
    }
  
  
  
  /**
   * pääohjelma luokan testaukseen
 * @param args ei käytössä
 */
public static void main(String[] args)  {
    FunktioRR f = new Funktioni();
    double y1 = funMin(f,0,5);
    System.out.println("funktion minimi on:" + y1);
    


  }
}