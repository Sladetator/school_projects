package fxSiivous;

import java.util.List;

import fi.jyu.mit.fxgui.ListChooser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import siivous.GUIAliohjelmat;
import siivous.Hallinta;
import siivous.SailoException;
import siivous.Tyokohde;
import siivous.Tyontekija;
import javafx.scene.control.ChoiceBox;

/**
 * @author valtteri j�rvinen, viljami j�rvinen
 * @version 13.2.2018
 * 
 * Kontrolleriluokka Ty�ntekij�t-ikkunalle
 */
public class TyontekijatController {
    private GUIAliohjelmat ali = new GUIAliohjelmat();

    @FXML private Button btnLisaa;

    @FXML private Button btnPoista;

    @FXML private Button btnMuokkaa;

    @FXML private Button btnTallenna;

    @FXML private MenuItem menuVaihda;

    @FXML private MenuItem menuTallenna;

    @FXML private MenuItem menuSulje;

    @FXML private MenuItem menuLisaa;

    @FXML private MenuItem menuPoista;

    @FXML private MenuItem menuApua;

    @FXML private MenuItem menuTietoja;
    
    @FXML private ChoiceBox<String> chooseNimike;

    @FXML private ChoiceBox<String> chooseTyosuhde;
    @FXML
    private ListChooser<Tyontekija> lcHaku;

    @FXML
    private TextField txtEnimi;

    @FXML
    private TextField txtKatuosoite;

    @FXML
    private TextField txtPostinro;

    @FXML
    private TextField txtPuh;

    @FXML
    private TextField txtAlkamispvm;

    @FXML
    private TextField txtTyontekijanro;

    @FXML
    private TextField txtTyokohteet;

    @FXML
    private TextField txtPostios;

    @FXML
    private TextField txtLisatietoja;

    @FXML
    private TextField txtSnimi;

    @FXML
    private TextField txtSotu;

    @FXML
    private TextField txtTyonimike;

    @FXML
    private TextField txtTyosuhde;
    
    private Hallinta hallinta;
    
    /**
     * Tehd��n k�ynnist�ess� tarvittavat alustukset
     */
    @FXML
    public void initialize() {
        hallinta = new Hallinta();
        try {
            hallinta = hallinta.alustaHallinta();
        } catch (SailoException e) {
            e.printStackTrace();
        }
        alustaChooser();
    }
    
    /**
     * Alustetaan loput tarvittavat tiedot
     */
    private void alustaChooser() {
        lcHaku.clear();
        for(int i = 0; i < hallinta.getTyontekijoita(); i++) {
            Tyontekija tyontekija = hallinta.annaTyontekijaTaulukosta(i);
            lcHaku.add(tyontekija.getNimi(), tyontekija);
        }
        
        lcHaku.setSelectedIndex(0);
        lcHaku.addSelectionListener(e -> asetaTekstit(e));
        asetaTekstit(hallinta.annaTyontekijaTaulukosta(lcHaku.getSelectedIndex()));
    }
    
    /**
     * Asettaa tekstit Ty�ntekij�-ikkunaan
     * @param tyontekija tyontekija jota k�sitell��n
     */
    private void asetaTekstit(Tyontekija tyontekija) {
        String[] nimet = tyontekija.getNimi().split(" ");
        txtEnimi.setText(nimet[0]);
        txtSnimi.setText(nimet[1]);
        txtKatuosoite.setText(tyontekija.getKatuosoite());
        txtPostinro.setText(tyontekija.getPostinro());
        txtPuh.setText(tyontekija.getPuhnro());
        txtAlkamispvm.setText(tyontekija.getAlkamispvm());
        txtSotu.setText(tyontekija.getSotu());
        txtTyonimike.setText(tyontekija.getTyonimike());
        txtTyosuhde.setText(tyontekija.getTyosuhde());
        txtTyontekijanro.setText(tyontekija.getTyontekijanro());
        
        try {
            List<Tyokohde> tyokohteet= hallinta.annaTyokohteet(tyontekija);
            String s = "";
            for(Tyokohde tyokohde : tyokohteet) {
                s += tyokohde.getNimi() + ", ";
            }
            txtTyokohteet.setText(s.substring(0, s.length()-2));
        } catch (NullPointerException e) {
            txtTyokohteet.setText("Ei l�ytynyt ty�kohdetta");
        } catch (IndexOutOfBoundsException e) {
            txtTyokohteet.setText("Ei viel� asetettu ty�kohdetta" );
        }
        
        txtPostios.setText(tyontekija.getPostiosoite());
        txtLisatietoja.setText(tyontekija.getLisatietoja());
    }

    /**
     * Avaa Apua-ikkunan
     */
    @FXML
    private void avaaApua() {
        ali.naytaEiToimi();
    }


    /**
     * Avaa Lis�ysn�kym�n. Tyhjent�� n�kyvill� olevat tiedot ja avaa ne muokattaviksi
     */
    @FXML
    private void avaaLisaa() {
        ali.naytaEiToimi();

    }


    /**
     * Avaa Muokkausn�kym�n. Avaa n�kyvill� olevat tiedot muokattaviksi
     */
    @FXML
    private void avaaMuokkaus() {
        ali.naytaEiToimi();

    }


    /**
     * Avaa ikkunan, jossa kysyt��n halutaanko poistaa nyt avattu
     */
    @FXML
    private void avaaPoista() {
        ali.naytaEiToimi();

    }


    /**
     * Avaa tallennusikkunan, jossa kysyt��n halutaanko tallentaa
     */
    @FXML
    private void avaaTallenna() {
        ali.naytaEiToimi();
    }


    /**
     * Avaa Tietoja-ikkunan
     */
    @FXML
    private void avaaTietoja() {
        ali.avaaIkkuna("Tietoja");

    }


    /**
     * Avaa Ty�kohteet-n�kym�n ja sulkee t�m�n ikkunan alta pois
     */
    @FXML
    private void avaaTyokohteet() {
        ali.avaaIkkuna("Tyokohteet");
        ali.suljeIkkuna(btnMuokkaa);
    }


    /**
     * Sulkee ohjelman
     */
    @FXML
    private void sulje() {
        ali.sulje();
    }

}
