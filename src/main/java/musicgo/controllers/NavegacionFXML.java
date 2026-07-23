package musicgo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utilidad compartida por los view-controllers para cambiar de pantalla
 * dentro del mismo Stage. Evita repetir el mismo bloque de carga de FXML
 * en cada controlador que dispara una transición de fase.
 */
public class NavegacionFXML {

    private NavegacionFXML() {
    }

    public static void cambiarEscena(ActionEvent event, String rutaFxml) throws IOException {
        Parent root = FXMLLoader.load(NavegacionFXML.class.getResource(rutaFxml));
        Scene escena = new Scene(root);
        escena.getStylesheets().add(NavegacionFXML.class.getResource("/styles/musicgo.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(escena);
        stage.show();
    }
}
