package musicgo.models.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import musicgo.models.entities.Catalogo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Repositorio encargado exclusivamente de la persistencia del Catálogo global
 * (audios y productos especiales) en el archivo catalogo.json.
 */
public class CatalogoRepository {

    private static final String RUTA_ARCHIVO = "src/main/resources/data/catalogo.json";
    private final Gson gson;

    public CatalogoRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Carga el catálogo desde el archivo JSON.
     * Si el archivo no existe o está vacío, retorna un catálogo nuevo (vacío).
     */
    public Catalogo cargarCatalogo() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Catalogo catalogo = gson.fromJson(reader, Catalogo.class);
            return catalogo != null ? catalogo : new Catalogo();
        } catch (IOException e) {
            return new Catalogo();
        }
    }

    /**
     * Guarda (sobrescribe) el catálogo completo en el archivo JSON.
     */
    public void guardarCatalogo(Catalogo catalogo) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(catalogo, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}