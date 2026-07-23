package musicgo.controllers;

import musicgo.exceptions.AliasDuplicadoException;
import musicgo.models.entities.Audio;
import musicgo.models.entities.Usuario;
import musicgo.models.persistence.UsuarioRepository;

import java.util.List;

/**
 * Controlador de la fase 2 (Pre Configuración): registro de usuarios y
 * carga/visualización del catálogo. Coordina UsuarioRepository y
 * ControladorCatalogo sin contener lógica de vista.
 */
public class PreConfiguracionController {

    private final UsuarioRepository usuarioRepository;
    private final ControladorCatalogo controladorCatalogo;

    public PreConfiguracionController() {
        this.usuarioRepository = new UsuarioRepository();
        this.controladorCatalogo = new ControladorCatalogo();
    }

    /**
     * Cubre el Caso 1: no permitir alias repetidos.
     */
    public void registrarUsuario(String alias, String nombre) throws AliasDuplicadoException {
        if (usuarioRepository.existeAlias(alias)) {
            throw new AliasDuplicadoException(alias);
        }
        List<Usuario> usuarios = usuarioRepository.cargarUsuarios();
        usuarios.add(new Usuario(alias, nombre));
        usuarioRepository.guardarUsuarios(usuarios);
    }

    public void cargarAudio(Audio audio) {
        controladorCatalogo.agregarAudio(audio);
    }

    public List<Audio> obtenerCatalogo() {
        return controladorCatalogo.getCatalogo().getListaAudios();
    }

    /**
     * Regla de habilitación del botón Continuar: al menos 1 usuario
     * registrado Y al menos 1 audio en el catálogo.
     */
    public boolean puedeContinuar() {
        boolean hayUsuarios = !usuarioRepository.cargarUsuarios().isEmpty();
        boolean hayAudios = controladorCatalogo.tieneAlMenosUnAudio();
        return hayUsuarios && hayAudios;
    }
}
