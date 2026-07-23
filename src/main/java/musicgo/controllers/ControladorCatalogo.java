package musicgo.controllers;

import musicgo.models.entities.Audio;
import musicgo.models.entities.Catalogo;
import musicgo.models.persistence.CatalogoRepository;

/**
 * Controlador encargado de la lógica CRUD del catálogo global.
 * Coordina las acciones entre el modelo (Catalogo) y la persistencia
 * (CatalogoRepository), sin contener lógica de vista.
 */
public class ControladorCatalogo {

    private Catalogo catalogo;
    private final CatalogoRepository catalogoRepository;

    public ControladorCatalogo() {
        this.catalogoRepository = new CatalogoRepository();
        this.catalogo = catalogoRepository.cargarCatalogo();
    }

    // ---------- AUDIOS ----------

    public void agregarAudio(Audio audio) {
        catalogo.agregarAudio(audio);
        catalogoRepository.guardarCatalogo(catalogo);
    }

    public boolean modificarAudio(String id, String nuevoTitulo, String nuevaClasificacion) {
        Audio audio = catalogo.buscarAudioPorId(id);
        if (audio == null) {
            return false;
        }
        audio.setTitulo(nuevoTitulo);
        audio.setClasificacionContenido(nuevaClasificacion);
        catalogoRepository.guardarCatalogo(catalogo);
        return true;
    }

    public boolean eliminarAudio(String id) {
        boolean eliminado = catalogo.eliminarAudio(id);
        if (eliminado) {
            catalogoRepository.guardarCatalogo(catalogo);
        }
        return eliminado;
    }

    // ---------- ACCESO GENERAL ----------

    public Catalogo getCatalogo() {
        return catalogo;
    }

    /**
     * Regla de validación crítica de la Fase 2 (preconfiguración):
     * el botón Continuar solo se habilita si hay al menos un audio cargado.
     */
    public boolean tieneAlMenosUnAudio() {
        return !catalogo.getListaAudios().isEmpty();
    }
}