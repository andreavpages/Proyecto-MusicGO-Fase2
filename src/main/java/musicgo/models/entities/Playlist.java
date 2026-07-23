package musicgo.models.entities;

import musicgo.exceptions.AudioDuplicadoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una lista de reproducción de un usuario final.
 * Guarda solo los id de los audios (referencia al catálogo global),
 * no copias de los objetos Audio, para no duplicar datos.
 */
public class Playlist {

    private String nombre;
    private String aliasPropietario;
    private List<String> idsAudios;

    public Playlist(String nombre, String aliasPropietario) {
        this.nombre = nombre;
        this.aliasPropietario = aliasPropietario;
        this.idsAudios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAliasPropietario() {
        return aliasPropietario;
    }

    public void setAliasPropietario(String aliasPropietario) {
        this.aliasPropietario = aliasPropietario;
    }

    public List<String> getIdsAudios() {
        return idsAudios;
    }

    public void setIdsAudios(List<String> idsAudios) {
        this.idsAudios = idsAudios;
    }

    public boolean contieneAudio(String idAudio) {
        return idsAudios.contains(idAudio);
    }

    /**
     * Cubre el Caso 3 de la reformulación: no permitir audios repetidos en una playlist.
     */
    public void agregarAudio(String idAudio) throws AudioDuplicadoException {
        if (contieneAudio(idAudio)) {
            throw new AudioDuplicadoException(idAudio);
        }
        idsAudios.add(idAudio);
    }
}
