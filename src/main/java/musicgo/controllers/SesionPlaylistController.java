package musicgo.controllers;

import musicgo.exceptions.AudioDuplicadoException;
import musicgo.exceptions.UsuarioNoEncontradoException;
import musicgo.models.entities.Audio;
import musicgo.models.entities.Playlist;
import musicgo.models.entities.Usuario;
import musicgo.models.persistence.PlaylistRepository;
import musicgo.models.persistence.UsuarioRepository;

import java.util.List;

/**
 * Controlador de la fase 3 (Sesión + Playlists): login por alias,
 * visualización de playlists del usuario activo, creación de playlists
 * y adición de audios del catálogo. Coordina UsuarioRepository,
 * PlaylistRepository y ControladorCatalogo sin contener lógica de vista.
 */
public class SesionPlaylistController {

    private final UsuarioRepository usuarioRepository;
    private final PlaylistRepository playlistRepository;
    private final ControladorCatalogo controladorCatalogo;
    private Usuario usuarioActivo;

    public SesionPlaylistController() {
        this.usuarioRepository = new UsuarioRepository();
        this.playlistRepository = new PlaylistRepository();
        this.controladorCatalogo = new ControladorCatalogo();
    }

    /**
     * Cubre el Caso 2: login solo por alias, sin password.
     */
    public Usuario iniciarSesion(String alias) throws UsuarioNoEncontradoException {
        Usuario usuario = usuarioRepository.buscarPorAlias(alias);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException(alias);
        }
        this.usuarioActivo = usuario;
        return usuario;
    }

    public List<Playlist> obtenerPlaylistsUsuarioActivo() {
        return playlistRepository.buscarPorPropietario(usuarioActivo.getAlias());
    }

    public Playlist crearPlaylist(String nombre) {
        List<Playlist> playlists = playlistRepository.cargarPlaylists();
        Playlist nueva = new Playlist(nombre, usuarioActivo.getAlias());
        playlists.add(nueva);
        playlistRepository.guardarPlaylists(playlists);
        return nueva;
    }

    /**
     * Cubre el Caso 3: evitar audios duplicados dentro de una misma playlist.
     */
    public void agregarAudioAPlaylist(Playlist playlist, String idAudio) throws AudioDuplicadoException {
        playlist.agregarAudio(idAudio);
        List<Playlist> playlists = playlistRepository.cargarPlaylists();
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getNombre().equals(playlist.getNombre())
                    && playlists.get(i).getAliasPropietario().equals(playlist.getAliasPropietario())) {
                playlists.set(i, playlist);
                break;
            }
        }
        playlistRepository.guardarPlaylists(playlists);
    }

    public List<Audio> obtenerCatalogo() {
        return controladorCatalogo.getCatalogo().getListaAudios();
    }

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    /**
     * Cubre el Caso 4: punto de cierre que la vista debe invocar al salir.
     * No hace falta volver a escribir nada aquí: usuarios, catálogo y
     * playlists ya se persisten de inmediato en cada operación mutadora
     * (mismo patrón en los tres repositorios), así que al llegar aquí el
     * disco ya está al día.
     */
    public void cerrarSesionSegura() {
        usuarioActivo = null;
    }
}
