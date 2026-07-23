package musicgo.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import musicgo.exceptions.AudioDuplicadoException;
import musicgo.exceptions.UsuarioNoEncontradoException;
import musicgo.models.entities.Audio;
import musicgo.models.entities.Playlist;

import java.util.List;

/**
 * View-controller de la fase 3 (Sesión + Playlists). Traduce eventos de
 * la vista a llamadas de SesionPlaylistController; toda validación y
 * persistencia real vive en el backend, no aquí.
 */
public class SesionPlaylistViewController {

    private final SesionPlaylistController controller = new SesionPlaylistController();

    private List<Playlist> playlistsCache = List.of();
    private List<Audio> catalogoCache = List.of();

    @FXML
    private TextField aliasLoginField;
    @FXML
    private Label usuarioActivoLabel;
    @FXML
    private ListView<String> playlistsListView;
    @FXML
    private TextField nombrePlaylistField;
    @FXML
    private ListView<String> catalogoListView;

    @FXML
    private void onIniciarSesion() {
        String alias = aliasLoginField.getText();
        if (alias == null || alias.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ingresa un alias.");
            return;
        }
        try {
            controller.iniciarSesion(alias);
            usuarioActivoLabel.setText("Sesión activa: " + controller.getUsuarioActivo().getAlias());
            refrescarPlaylists();
            refrescarCatalogo();
        } catch (UsuarioNoEncontradoException e) {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void onCrearPlaylist() {
        String nombre = nombrePlaylistField.getText();
        if (controller.getUsuarioActivo() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Inicia sesión primero.");
            return;
        }
        if (nombre == null || nombre.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ingresa un nombre para la playlist.");
            return;
        }
        controller.crearPlaylist(nombre);
        nombrePlaylistField.clear();
        refrescarPlaylists();
    }

    @FXML
    private void onAgregarAudio() {
        int indicePlaylist = playlistsListView.getSelectionModel().getSelectedIndex();
        int indiceAudio = catalogoListView.getSelectionModel().getSelectedIndex();

        if (indicePlaylist < 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona una playlist.");
            return;
        }
        if (indiceAudio < 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona un audio del catálogo.");
            return;
        }

        Playlist playlist = playlistsCache.get(indicePlaylist);
        Audio audio = catalogoCache.get(indiceAudio);
        try {
            controller.agregarAudioAPlaylist(playlist, audio.getId());
            refrescarPlaylists();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Audio agregado a '" + playlist.getNombre() + "'.");
        } catch (AudioDuplicadoException e) {
            mostrarAlerta(Alert.AlertType.WARNING, e.getMessage());
        }
    }

    @FXML
    private void onSalir() {
        controller.cerrarSesionSegura();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Datos guardados. Cerrando la aplicación.");
        Platform.exit();
    }

    private void refrescarPlaylists() {
        playlistsCache = controller.obtenerPlaylistsUsuarioActivo();
        playlistsListView.getItems().clear();
        for (Playlist playlist : playlistsCache) {
            playlistsListView.getItems().add(playlist.getNombre() + " (" + playlist.getIdsAudios().size() + " audios)");
        }
    }

    private void refrescarCatalogo() {
        catalogoCache = controller.obtenerCatalogo();
        catalogoListView.getItems().clear();
        for (Audio audio : catalogoCache) {
            catalogoListView.getItems().add(audio.mostrarInfo());
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.showAndWait();
    }
}
