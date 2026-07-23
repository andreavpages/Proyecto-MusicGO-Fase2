package musicgo.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import musicgo.exceptions.AliasDuplicadoException;
import musicgo.models.entities.Audio;
import musicgo.models.entities.Cancion;
import musicgo.models.entities.Podcast;

import java.io.IOException;
import java.util.UUID;

/**
 * View-controller de la fase 2 (Pre Configuración). Traduce los eventos
 * de la vista a llamadas de PreConfiguracionController; toda validación
 * y persistencia real vive en el backend, no aquí.
 */
public class PreConfiguracionViewController {

    private static final String TIPO_CANCION = "Canción";
    private static final String TIPO_PODCAST = "Podcast";

    private final PreConfiguracionController controller = new PreConfiguracionController();

    @FXML
    private TextField aliasField;
    @FXML
    private TextField nombreField;
    @FXML
    private ListView<String> catalogoListView;
    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private TextField tituloField;
    @FXML
    private TextField clasificacionField;
    @FXML
    private TextField duracionField;
    @FXML
    private TextField campo3Field;
    @FXML
    private TextField campo4Field;
    @FXML
    private Button continuarButton;

    @FXML
    private void initialize() {
        tipoComboBox.getItems().addAll(TIPO_CANCION, TIPO_PODCAST);
        refrescarCatalogo();
        actualizarBotonContinuar();
    }

    @FXML
    private void onRegistrarUsuario() {
        String alias = aliasField.getText();
        String nombre = nombreField.getText();
        if (alias == null || alias.isBlank() || nombre == null || nombre.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Alias y nombre son obligatorios.");
            return;
        }
        try {
            controller.registrarUsuario(alias, nombre);
            aliasField.clear();
            nombreField.clear();
            actualizarBotonContinuar();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Usuario '" + alias + "' registrado.");
        } catch (AliasDuplicadoException e) {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void onAgregarAudio() {
        String tipo = tipoComboBox.getValue();
        String titulo = tituloField.getText();
        String clasificacion = clasificacionField.getText();
        String duracionTexto = duracionField.getText();
        String campo3 = campo3Field.getText();
        String campo4 = campo4Field.getText();

        if (tipo == null || titulo == null || titulo.isBlank()
                || clasificacion == null || clasificacion.isBlank()
                || duracionTexto == null || duracionTexto.isBlank()
                || campo3 == null || campo3.isBlank() || campo4 == null || campo4.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Completa todos los campos del audio.");
            return;
        }

        int duracionSegundos;
        try {
            duracionSegundos = Integer.parseInt(duracionTexto.trim());
            if (duracionSegundos <= 0) {
                mostrarAlerta(Alert.AlertType.WARNING, "La duración debe ser un número mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "La duración debe ser un número entero de segundos.");
            return;
        }

        String id = UUID.randomUUID().toString();
        Audio audio = TIPO_CANCION.equals(tipo)
                ? new Cancion(id, titulo, clasificacion, duracionSegundos, campo3, campo4)
                : new Podcast(id, titulo, clasificacion, duracionSegundos, campo3, campo4);

        controller.cargarAudio(audio);
        refrescarCatalogo();
        actualizarBotonContinuar();

        tituloField.clear();
        clasificacionField.clear();
        duracionField.clear();
        campo3Field.clear();
        campo4Field.clear();
    }

    @FXML
    private void onContinuar(ActionEvent event) throws IOException {
        NavegacionFXML.cambiarEscena(event, "/views/sesion-playlist-view.fxml");
    }

    @FXML
    private void onSalir() {
        Platform.exit();
    }

    private void refrescarCatalogo() {
        catalogoListView.getItems().clear();
        for (Audio audio : controller.obtenerCatalogo()) {
            catalogoListView.getItems().add(audio.mostrarInfo());
        }
    }

    private void actualizarBotonContinuar() {
        continuarButton.setDisable(!controller.puedeContinuar());
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.showAndWait();
    }
}
