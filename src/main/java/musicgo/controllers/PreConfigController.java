package com.musicgo.controller;

import com.musicgo.persistence.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class PreConfigController {

    @FXML private TextField txtAlias;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtArtista;
    @FXML private ListView<String> listCatalogo;
    @FXML private Button btnContinuarApp;

    private DataManager dataManager = DataManager.getInstance();

    @FXML
    public void initialize() {
        actualizarCatalogoVisual();
        validarEstadoContinuar(); // Comprueba al iniciar si ya hay datos guardados
    }

    @FXML
    private void handleRegistrarUsuario(ActionEvent event) {
        try {
            String alias = txtAlias.getText().trim();
            String nombre = txtNombre.getText().trim();

            if (alias.isEmpty() || nombre.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor complete todos los campos.");
                return;
            }

            // CASO DE PRUEBA 1: Registro Duplicado
            boolean existe = dataManager.getUsuarios().stream()
                    .anyMatch(u -> u.getAlias().equalsIgnoreCase(alias));

            if (existe) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de Registro", "El alias ya existe en el sistema.");
                return;
            }

            dataManager.agregarUsuario(alias, nombre);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario registrado correctamente.");
            txtAlias.clear();
            txtNombre.clear();

            validarEstadoContinuar();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error Inesperado", e.getMessage());
        }
    }

    @FXML
    private void handleAgregarAudio(ActionEvent event) {
        try {
            String titulo = txtTitulo.getText().trim();
            String artista = txtArtista.getText().trim();

            if (titulo.isEmpty() || artista.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Complete los datos del audio.");
                return;
            }

            dataManager.agregarAudioAlCatalogo(titulo, artista);
            actualizarCatalogoVisual();
            validarEstadoContinuar();

            txtTitulo.clear();
            txtArtista.clear();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al guardar audio", e.getMessage());
        }
    }

    // REGULA LA HABILITACIÓN DEL BOTÓN DE CONTINUAR
    private void validarEstadoContinuar() {
        boolean hayUsuarios = !dataManager.getUsuarios().isEmpty();
        boolean hayAudios = !dataManager.getCatalogo().isEmpty();

        // Se activa el botón SOLO cuando ambas condiciones se cumplen
        btnContinuarApp.setDisable(!(hayUsuarios && hayAudios));
    }

    private void actualizarCatalogoVisual() {
        listCatalogo.getItems().clear();
        dataManager.getCatalogo().forEach(a -> listCatalogo.getItems().add(a.toString()));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}