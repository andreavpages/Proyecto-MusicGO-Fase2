package musicgo.models.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el catálogo global de la plataforma MusicGO.
 * Agrupa por composición la lista de audios (canciones/podcasts) y
 * la lista de productos especiales disponibles.
 *
 * Esta clase solo gestiona la colección en memoria; la persistencia real
 * a JSON es responsabilidad de las clases del paquete `persistence`.
 */
public class Catalogo {

    private List<Audio> listaAudios;
    private List<ProductoEspecial> listaProductos;

    public Catalogo() {
        this.listaAudios = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
    }

    public List<Audio> getListaAudios() {
        return listaAudios;
    }

    public void setListaAudios(List<Audio> listaAudios) {
        this.listaAudios = listaAudios;
    }

    public List<ProductoEspecial> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoEspecial> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void agregarAudio(Audio audio) {
        listaAudios.add(audio);
    }

    public void agregarProducto(ProductoEspecial producto) {
        listaProductos.add(producto);
    }

    public boolean eliminarAudio(String id) {
        return listaAudios.removeIf(a -> a.getId().equals(id));
    }

    /**
     * Busca un audio en el catálogo por su id.
     * Retorna null si no se encuentra (el controlador que use esto
     * será responsable de lanzar una excepción personalizada si aplica).
     */
    public Audio buscarAudioPorId(String id) {
        for (Audio audio : listaAudios) {
            if (audio.getId().equals(id)) {
                return audio;
            }
        }
        return null;
    }
}