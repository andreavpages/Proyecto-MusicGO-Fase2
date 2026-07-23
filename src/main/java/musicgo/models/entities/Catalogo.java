package musicgo.models.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el catálogo global de la plataforma MusicGO.
 * Contiene la lista de audios (canciones/podcasts) disponibles.
 */
public class Catalogo {

    private List<Audio> listaAudios;

    public Catalogo() {
        this.listaAudios = new ArrayList<>();
    }

    public List<Audio> getListaAudios() {
        return listaAudios;
    }

    public void setListaAudios(List<Audio> listaAudios) {
        this.listaAudios = listaAudios;
    }

    public void agregarAudio(Audio audio) {
        listaAudios.add(audio);
    }

    public boolean eliminarAudio(String id) {
        return listaAudios.removeIf(a -> a.getId().equals(id));
    }

    public Audio buscarAudioPorId(String id) {
        for (Audio audio : listaAudios) {
            if (audio.getId().equals(id)) {
                return audio;
            }
        }
        return null;
    }
}