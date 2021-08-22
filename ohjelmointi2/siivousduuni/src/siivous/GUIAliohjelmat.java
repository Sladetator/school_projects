package siivous;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author valtteri järvinen, viljami järvinen
 * @version 13.2.2018
 *
 * Luokka aliohjelmille koodin vähentämiseksi ja käytön helpottamiseksi
 */
public class GUIAliohjelmat {

    
    /**
     * Aliohjelma ikkunoiden avaamiselle
     * @param ikkuna avattavan ikkunan nimi
     */
    public void avaaIkkuna(String ikkuna) {
        try {
            Stage stage = new Stage();
            String missa = "/fxSiivous/" + ikkuna + ".fxml";
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(missa));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Aliohjelma dialogin avaamiselle. Dialogi näyttää "Ei toimi"
     */
    public void naytaEiToimi() {
        Dialogs.showMessageDialog("Ei toimi");
        
    }
    
    /**
     * Sulkee ikkunan
     */
    public void sulje() {
        Platform.exit();
        System.exit(0);        
    }
    
    /**
     * Sulkee ikkunan hakemalla scenen jostain ruudulla olevasta painikkeesta, koska ei osattu muuten tehdä :)
     * @param btn button joka löytyy ruudusta
     */
    public void suljeIkkuna(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

}
