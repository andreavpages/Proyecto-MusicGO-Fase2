package musicgo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * View-controller de la fase 1 (Bienvenida). Solo traduce clics de la
 * vista a llamadas del controlador de backend y a la navegación de
 * pantalla; no contiene lógica de negocio propia.
 */
public class BienvenidaViewController {

    private final BienvenidaController bienvenidaController = new BienvenidaController();

    @FXML
    private void onContinuar(ActionEvent event) throws IOException {
        bienvenidaController.continuar();
        NavegacionFXML.cambiarEscena(event, "/views/preconfiguracion-view.fxml");
    }

    @FXML
    private void onCerrar() {
        bienvenidaController.cerrarAplicacion();
    }
}
