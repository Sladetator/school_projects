package fxSiivous;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import siivous.GUIAliohjelmat;

/**
 * @author valtteri j�rvinen, viljami j�rvinen
 * @version 13.2.2018
 *
 * Kontrolleri aloitusn�kym�lle
 */
public class SiivousGUIController {

    @FXML private Button btnTyokohteet;

    @FXML private Button btnTyontekija;

    private GUIAliohjelmat ali = new GUIAliohjelmat();
    
    
    /**
     * Avaa ty�kohteet-ikkunan ja sulkee aiemman ikkunan
     */
    @FXML
    private void avaaTyokohteet() {
        ali.avaaIkkuna("Tyokohteet");
        ali.suljeIkkuna(btnTyokohteet);
    }

    
    /**
     * Avaa ty�ntekij�-ikkunan ja sulkee aiemman ikkunan
     */
    @FXML
    private void avaaTyontekija() {
        ali.avaaIkkuna("Tyontekijat");
        ali.suljeIkkuna(btnTyokohteet);
    }
}
