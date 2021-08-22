package siivous;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author valtteri & viljami j�rvinen
 * @version 10.3.2018
 *
 */
@SuppressWarnings("all")
public class Tyokohteet {
    private final Collection<Tyokohde> tyokohteet;
    

    /**
     * Alustaa Tyokohteet-olion
     */
    public Tyokohteet() {
        this.tyokohteet = new ArrayList<Tyokohde>();
    }
    
 

    /**
     * Lis�� uuden ty�kohteen listaa
     * @param tyokohde lis�tt�v� tyokohde
     * #import java.util.ArrayList;
     * #import java.util.*;
     * #import org.junit.*;
     * <pre name="test">
     * #THROWS SailoException
     * Tyokohteet tyokohteet = new Tyokohteet();
     * Tyokohde t = new Tyokohde();
     * Tyokohde t2 = new Tyokohde();
     * Tyokohde t3 = new Tyokohde();
     * tyokohteet.lisaa(t);
     * tyokohteet.lisaa(t2);
     * tyokohteet.getKoko2() === 2;
     * tyokohteet.lisaa(t3);
     * tyokohteet.getKoko2() === 3;
     * </pre>
     */
    public void lisaa(Tyokohde tyokohde){
        tyokohteet.add(tyokohde);
        
    }
    
    
    

    
    /**Vaihtoehtoinen tapaus getKoko():sta
     * @return ty�kohteiden koko
     */
    public int getKoko2() {
    int koko2 = tyokohteet.size();
    return koko2;
    }
    
    
    
 
    /**
     * 
     * @param tunnusnro A
     * @return l�ydetyt ty�kohteet
     * #import java.util.ArrayList;
     * #import java.util.*;
     * #import org.junit.*;
     * <pre name="test">
     * #THROWS SailoException
     * Tyokohteet tyokohteet = new Tyokohteet();
     * Tyokohde t = new Tyokohde();
     * Tyokohde t2 = new Tyokohde();
     * Tyokohde t3 = new Tyokohde();
     * tyokohteet.lisaa(t);
     * tyokohteet.lisaa(t2);
     * tyokohteet.lisaa(t3);
     * tyokohteet.getKoko2() === 3;
     * </pre> 
     */
    public List<Tyokohde> annaTyokohteet(int tunnusnro) {
        List<Tyokohde> loydetyt = new ArrayList<Tyokohde>();
        for(Tyokohde tyokohde : tyokohteet) {
            if(tyokohde.getTyontekijaNro() == tunnusnro) loydetyt.add(tyokohde);
        }
        return loydetyt;
    }

    
    /**
     * Testip��ohjelma Ty�kohteille
     * @param args ei k�yt�ss�
     * @throws SailoException jos rivi ei ole oikein
     */
    public static void main(String[] args) throws SailoException {
        Tyokohteet tyokohteet = new Tyokohteet();
        Tyokohde t = new Tyokohde();
        Tyokohde t2 = new Tyokohde();
        Tyokohde t3 = new Tyokohde();
        Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sep�nkatu 2 |40100|Jyv�skyl�      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
        t.vastaa();
        t2.vastaa();
        t3.vastaa();
        tyokohteet.lisaa(kohdedatasta);
        tyokohteet.lisaa(t);
        tyokohteet.lisaa(t2);
        tyokohteet.lisaa(t3);
        System.out.println(tyokohteet.getKoko2());
        
        

        List<Tyokohde> tyokohteet2 = tyokohteet.annaTyokohteet(2); //Kauppa Kallis ty�ntekij�n nro 2

        for (Tyokohde tyokohde : tyokohteet2) {
            System.out.print(tyokohde.getTyontekijaNro() + " ");
            tyokohde.tulosta(System.out);
        }
        
    }


    public Tyokohde annaTyokohde(int mones) {
        int i = 0;
        for(Tyokohde tyokohde : tyokohteet) {
            if(i == mones) return tyokohde;
            i++;
        }
        return null;
    }
    

}
