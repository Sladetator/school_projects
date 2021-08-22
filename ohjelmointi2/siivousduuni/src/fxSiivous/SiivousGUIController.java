package fxSiivous;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import siivous.GUIAliohjelmat;

/**
 * @author valtteri järvinen, viljami järvinen
 * @version 13.2.2018
 *
 * Kontrolleri aloitusnäkymälle
 */
public class SiivousGUIController {

    @FXML private Button btnTyokohteet;

    @FXML private Button btnTyontekija;

    private GUIAliohjelmat ali = new GUIAliohjelmat();
    
    
    /**
     * Avaa työkohteet-ikkunan ja sulkee aiemman ikkunan
     */
    @FXML
    private void avaaTyokohteet() {
        ali.avaaIkkuna("Tyokohteet");
        ali.suljeIkkuna(btnTyokohteet);
    }

    
    /**
     * Avaa työntekijä-ikkunan ja sulkee aiemman ikkunan
     */
    @FXML
    private void avaaTyontekija() {
        ali.avaaIkkuna("Tyontekijat");
        ali.suljeIkkuna(btnTyokohteet);
    }
}
