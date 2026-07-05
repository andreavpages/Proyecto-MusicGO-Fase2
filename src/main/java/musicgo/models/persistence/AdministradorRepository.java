package musicgo.models.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import musicgo.models.entities.Administrador;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio encargado exclusivamente de la persistencia de Administradores
 * en el archivo administradores.json. Este archivo se mantiene aislado del
 * registro público de usuarios, tal como exige el enunciado.
 */
public class AdministradorRepository {

    private static final String RUTA_ARCHIVO = "src/main/resources/data/administradores.json";
    private final Gson gson;

    public AdministradorRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<Administrador> cargarAdministradores() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Type tipoLista = new TypeToken<List<Administrador>>() {}.getType();
            List<Administrador> administradores = gson.fromJson(reader, tipoLista);
            return administradores != null ? administradores : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void guardarAdministradores(List<Administrador> administradores) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(administradores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}