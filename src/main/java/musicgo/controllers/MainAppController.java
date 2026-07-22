package com.musicgo.controller;

import com.musicgo.persistence.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainAppController {

    @FXML private TextField txtLoginAlias;
    @FXML private ListView<String> listPlaylists;
    @FXML private ListView<String> listCatalogo;
    @FXML private Button btnGuardarYSalir;

    private DataManager dataManager = DataManager.getInstance();
    private String usuarioActivoAlias = null;

    @FXML
    private void handleLogin() {
        String aliasInput = txtLoginAlias.getText().trim();

        // CASO DE PRUEBA 2: Usuario Inexistente
        boolean existe = dataManager.getUsuarios().stream()
                .anyMatch(u -> u.getAlias().equalsIgnoreCase(aliasInput));

        if (!existe) {
            mostrarAlerta(Alert.AlertType.ERROR, "Acceso Denegado", "El alias ingresado no existe en el sistema.");
            return;
        }

        usuarioActivoAlias = aliasInput;
        mostrarAlerta(Alert.AlertType.INFORMATION, "Sesión Iniciada", "Bienvenido " + usuarioActivoAlias);
        cargarDatosUsuario();
    }

    @FXML
    private void handleAgregarAudioAPlaylist() {
        String audioSeleccionado = listCatalogo.getSelectionModel().getSelectedItem();
        String playlistSeleccionada = listPlaylists.getSelectionModel().getSelectedItem();

        if (audioSeleccionado == null || playlistSeleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Seleccione una playlist y un audio.");
            return;
        }

        // CASO DE PRUEBA 3: Playlist sin Duplicados
        boolean yaExisteEnPlaylist = dataManager.existeAudioEnPlaylist(usuarioActivoAlias, playlistSeleccionada, audioSeleccionado);

        if (yaExisteEnPlaylist) {
            mostrarAlerta(Alert.AlertType.ERROR, "Audio Duplicado", "Este audio ya forma parte de la playlist seleccionada.");
            return;
        }

        dataManager.agregarAudioAPlaylist(usuarioActivoAlias, playlistSeleccionada, audioSeleccionado);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Audio añadido a la lista.");
    }

    // FASE IV: Cierre Seguro con Guardado de Persistencia
    @FXML
    private void handleCierreSeguro() {
        try {
            // Guarda todo en JSON/TXT antes de cerrar
            dataManager.guardarTodoEnArchivo();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Guardado Exitoso", "Todos los datos se han guardado en disco.");

            // Cerrar la ventana actual
            Stage stage = (Stage) btnGuardarYSalir.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al Guardar", "No se pudieron guardar los cambios: " + e.getMessage());
        }
    }

    private void cargarDatosUsuario() {
        // Cargar listas del usuario activo en listPlaylists...
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}