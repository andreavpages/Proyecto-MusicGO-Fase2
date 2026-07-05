package musicgo.models.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import musicgo.models.entities.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio encargado exclusivamente de la persistencia de Usuarios
 * en el archivo usuarios.json. Su única responsabilidad (SRP) es leer
 * y escribir esta información; no contiene lógica de negocio.
 */
public class UsuarioRepository {

    private static final String RUTA_ARCHIVO = "src/main/resources/data/usuarios.json";
    private final Gson gson;

    public UsuarioRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Carga la lista completa de usuarios desde el archivo JSON.
     * Si el archivo no existe o está vacío, retorna una lista vacía.
     */
    public List<Usuario> cargarUsuarios() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, tipoLista);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Guarda (sobrescribe) la lista completa de usuarios en el archivo JSON.
     * Se llama cada vez que hay un cambio, para cumplir con la persistencia
     * inmediata y bidireccional que exige el enunciado.
     */
    public void guardarUsuarios(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}