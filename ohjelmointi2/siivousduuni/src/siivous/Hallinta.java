package siivous;

import java.util.List;

/**
 * Hallinta-luokka huolehtii luokkien v�lisest� yhteisty�st�.
 * Metodit ovat p��osin vain "v�litt�j�metodeja"
 * @author valtteri & viljami j�rvinen
 * @version 1.3.2018
 *
 */
public class Hallinta {
    private final Tyontekijat tyontekijat = new Tyontekijat();
    private final Tyokohteet tyokohteet = new Tyokohteet();
    
    
    /**
     * Aliohjelma joka lis�� ty�ntekij�it�
     * @param tyontekija lis�tt�v� ty�ntekij�
     * @throws SailoException jos tietorakenne jo t�ynn�
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  #import fi.jyu.mit.ohj2.Suuntaaja;
     *  Hallinta hallinta = new Hallinta();
     *  Tyontekija datasta2 = new Tyontekija("2 |Susi Sepe  |020347-123T|Kauppatori 2  |40100  |Jyv�skyl�      |12-03030|siivooja        |osa-aikainen    |15.03.2017|0045321      |Opiskelija          |");
     *  hallinta.lisaa(datasta2);
     *  Tyontekija valle = new Tyontekija();
     *  hallinta.getTyontekijoita() === 1;
     *  hallinta.lisaa(valle);
     *  hallinta.getTyontekijoita() === 2;
     * </pre>
     */
    public void lisaa(Tyontekija tyontekija) throws SailoException {
        tyontekijat.lisaa(tyontekija);
    }
    
    
    /**
     * Aliohjelma joka lis�� ty�kohteita
     * @param tyokohde lis�tt�v� ty�kohde
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyv�skyl�      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
     *  Tyokohde t = new Tyokohde();
     *  hallinta.lisaa(kohdedatasta2);
     *  hallinta.lisaa(t);
     *  hallinta.getTyokohteita() === 2;
     * </pre>
     */
    public void lisaa(Tyokohde tyokohde) {
        tyokohteet.lisaa(tyokohde);
    }


    /**
     * Palauttaa i:nnen j�senen
     * @param i paikka josta haetaan ty�ntekij�
     * @return tyontekij� paikassa i
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija(), valle3 = new Tyontekija();
     *  hallinta.lisaa(valle);
     *  hallinta.lisaa(valle2);
     *  hallinta.lisaa(valle3);
     *  hallinta.annaTyontekija(2) === valle3; 
     *  hallinta.annaTyontekija(1) === valle2;
     * </pre>
     */
    public Tyontekija annaTyontekija(int i) {
        return tyontekijat.anna(i);
    }


    /**
     * Palauttaa ty�ntekij�iden m��r�n
     * @return ty�ntekij�iden m��r�
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija(), valle3 = new Tyontekija();
     *  hallinta.lisaa(valle);
     *  hallinta.lisaa(valle2);
     *  hallinta.lisaa(valle3);
     *  hallinta.getTyontekijoita() === 3;
     * </pre>
     */
    public int getTyontekijoita() {
        //voiko olla public?
        return tyontekijat.getMaara();
    }
    
    /**
     * hakee ty�kohteiden m��r�n
     * @return tyokohteiden m��r�
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyv�skyl�      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
     *  Tyokohde t = new Tyokohde();
     *  hallinta.lisaa(kohdedatasta2);
     *  hallinta.lisaa(t);
     *  hallinta.getTyokohteita() === 2;
     * </pre>
     */
    public int getTyokohteita(){
        return tyokohteet.getKoko2();
    }

    /**
     * hakee listaan tyontekij�n tunnusnumeron
     * @param tyontekija ty�ntekij� jonka tunnusnumero halutaan
     * @return ty�ntekij�n tunnusnumero
     */
    public List<Tyokohde> annaTyokohteet(Tyontekija tyontekija) {
        return tyokohteet.annaTyokohteet(tyontekija.getTunnusNro());
    }
    
    /**
     * palauttaa ty�kohteen ty�ntekij�n rekister�intij�rjestyksen perusteella
     * @param mones monesko tyokohde annetaan
     * @return ty�kohteet string taulukkona
     */
    public Tyokohde annaTyokohde(int mones) {
        return tyokohteet.annaTyokohde(mones);
    }
    
    
    /**
     * P��ohjelma luokan testailuun
     * @param args ei k�yt�ss�
     * @throws SailoException jos tietorakenne jo t�ynn�
     */
    public static void main(String[] args) throws SailoException {
        Hallinta hallinta = new Hallinta();
        Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyv�skyl�      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
        Tyontekija datasta2 = new Tyontekija("2 |Susi Sepe  |020347-123T|Kauppatori 2  |40100  |Jyv�skyl�      |12-03030|siivooja        |osa-aikainen    |15.03.2017|0045321      |Opiskelija          |");
        Tyontekija datasta3 = new Tyontekija("4 |Ari Arka   |030455-3333|Koulutie 2-4  |43100|Saarij�rvi      |12-91919|siivooja        |m��r�aikainen    |03.05.2014|0032992      |-          |");
        Tyontekija datasta4 = new Tyontekija("5 |Kalle Kokko|121213-555X|Kentt�kuja 2  |41400|Lievestuore      |12-66666|palveluohjaaja        |toistaiseksi    |03.03.2012|0029999      |K�rsii migreenist�          |");
        
        Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija();
        

        hallinta.lisaa(datasta);
        hallinta.lisaa(datasta2);
        hallinta.lisaa(datasta3);
        hallinta.lisaa(datasta4);
        

        valle.rekisteroi();
        valle.vastaa();
        valle2.rekisteroi();
        valle2.vastaa();
        hallinta.lisaa(valle);
        hallinta.lisaa(valle2);
        
        int id1 = valle.getTunnusNro();
        int id2 = valle2.getTunnusNro();
        Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sep�nkatu 2 |40100|Jyv�skyl�      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
        Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyv�skyl�      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
        Tyokohde kohdedatasta3 = new Tyokohde(" 3 |Terveysasema |Tapionkatu 5|40100|Jyv�skyl�      |0031356 |Terve Tuomo  |12-21345 |666-7777|2123   |1.5        |4|-                        |");
        Tyokohde kohdedatasta4 = new Tyokohde(" 4 |Kauppa Halpa |Torikatu 3  |43100|Saarij�rvi      |0022313 |Reipas Repe  |12-54321 |787-8787|1111   |1          |5|-                        |");
        
        Tyokohde t = new Tyokohde(id1);
        t.vastaa();
        Tyokohde t2 = new Tyokohde(id2);
        t2.vastaa();

        hallinta.lisaa(kohdedatasta);
        hallinta.lisaa(kohdedatasta2);
        hallinta.lisaa(kohdedatasta3);
        hallinta.lisaa(kohdedatasta4);
        hallinta.lisaa(t);
        hallinta.lisaa(t2);

        for(int i = 0; i < hallinta.getTyontekijoita(); i++) {
            System.out.println(i);
            Tyontekija tyontekija = hallinta.annaTyontekijaTaulukosta(i);
            System.out.println(tyontekija.getNimi());
        }
        /*
        for (int i = 0; i < hallinta.getTyontekijoita(); i++) {
            Tyontekija tyontekija = hallinta.annaTyontekija(i);
            System.out.println("Ty�ntekij�n nro taulukossa: " + i);
            tyontekija.tulosta(System.out);
            List<Tyokohde> loytyneet = hallinta.annaTyokohteet(tyontekija);
            for (Tyokohde tyokohde : loytyneet)
                tyokohde.tulosta(System.out);
            System.out.println("--------------------------------------");
        }*/
    }
    
    /**
     * Alustaa Hallinnan ty�ntekij�ill�
     * @return hallinta alustettu hallinta-olio
     * @throws SailoException Jos virheellinen data
     */
    public Hallinta alustaHallinta() throws SailoException {
        Hallinta hallinta = new Hallinta();
        Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyv�skyl�      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
        Tyontekija datasta2 = new Tyontekija("2 |Susi Sepe  |020347-123T|Kauppatori 2  |40100  |Jyv�skyl�      |12-03030|siivooja        |osa-aikainen    |15.03.2017|0045321      |Opiskelija          |");
        Tyontekija datasta3 = new Tyontekija("4 |Ari Arka   |030455-3333|Koulutie 2-4  |43100|Saarij�rvi      |12-91919|siivooja        |m��r�aikainen    |03.05.2014|0032992      |-          |");
        Tyontekija datasta4 = new Tyontekija("5 |Kalle Kokko|121213-555X|Kentt�kuja 2  |41400|Lievestuore      |12-66666|palveluohjaaja        |toistaiseksi    |03.03.2012|0029999      |K�rsii migreenist�          |");
        
        Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija();
        

        hallinta.lisaa(datasta);
        hallinta.lisaa(datasta2);
        hallinta.lisaa(datasta3);
        hallinta.lisaa(datasta4);
        

        valle.rekisteroi();
        valle.vastaa();
        valle2.rekisteroi();
        valle2.vastaa();
        hallinta.lisaa(valle);
        hallinta.lisaa(valle2);
        
        int id1 = valle.getTunnusNro();
        int id2 = valle2.getTunnusNro();
        Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sep�nkatu 2 |40100|Jyv�skyl�      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
        Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyv�skyl�      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
        Tyokohde kohdedatasta3 = new Tyokohde(" 3 |Terveysasema |Tapionkatu 5|40100|Jyv�skyl�      |0031356 |Terve Tuomo  |12-21345 |666-7777|2123   |1.5        |4|-                        |");
        Tyokohde kohdedatasta4 = new Tyokohde(" 4 |Kauppa Halpa |Torikatu 3  |43100|Saarij�rvi      |0022313 |Reipas Repe  |12-54321 |787-8787|1111   |1          |5|-                        |");
        
        Tyokohde t = new Tyokohde(id1);
        t.vastaa();
        Tyokohde t2 = new Tyokohde(id2);
        t2.vastaa();

        hallinta.lisaa(kohdedatasta);
        hallinta.lisaa(kohdedatasta2);
        hallinta.lisaa(kohdedatasta3);
        hallinta.lisaa(kohdedatasta4);
        hallinta.lisaa(t);
        hallinta.lisaa(t2);
        
        return hallinta;
    }

    
    /**
     * Lis�� uuden Ty�kohteen
     */
    public void lisaaTyokohde() {
        Tyokohde uusi = new Tyokohde();
        uusi.rekisteroi();
        uusi.vastaa();
        this.lisaa(uusi);
    }

    /**
     * Etsii ty�ntekij�n taulukosta indeksin perusteella
     * @param i indeksi jonka ty�ntekij� halutaa
     * @return ty�ntekij� jota haetaan
     */
    public Tyontekija annaTyontekijaTaulukosta(int i) {
        return tyontekijat.annaTaulukosta(i);
    }
}
