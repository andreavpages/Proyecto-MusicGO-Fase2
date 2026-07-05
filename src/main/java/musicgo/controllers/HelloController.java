package musicgo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        // Puedes personalizar este mensaje a tu gusto:
        welcomeText.setText("¡Sistema MusicGo Fase 2 - Conectado con éxito!");
    }
}