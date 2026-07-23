package musicgo.models.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import musicgo.models.entities.Playlist;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio encargado exclusivamente de la persistencia de Playlists
 * en el archivo playlists.json. Su única responsabilidad (SRP) es leer
 * y escribir esta información; no contiene lógica de negocio.
 */
public class PlaylistRepository {

    private static final String RUTA_ARCHIVO = "src/main/resources/data/playlists.json";
    private final Gson gson;

    public PlaylistRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<Playlist> cargarPlaylists() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Type tipoLista = new TypeToken<List<Playlist>>() {}.getType();
            List<Playlist> playlists = gson.fromJson(reader, tipoLista);
            return playlists != null ? playlists : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void guardarPlaylists(List<Playlist> playlists) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(playlists, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Playlist> buscarPorPropietario(String aliasPropietario) {
        List<Playlist> resultado = new ArrayList<>();
        for (Playlist playlist : cargarPlaylists()) {
            if (playlist.getAliasPropietario().equals(aliasPropietario)) {
                resultado.add(playlist);
            }
        }
        return resultado;
    }
}
