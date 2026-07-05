package musicgo.models.entities;

/**
 * Representa una canción dentro del catálogo de MusicGO.
 * Hereda de Audio los atributos comunes (id, titulo, clasificacionContenido)
 * y añade sus propios atributos específicos: artista y álbum.
 */
public class Cancion extends Audio {

    private String artista;
    private String album;

    public Cancion(String id, String titulo, String clasificacionContenido,
                   String artista, String album) {
        super(id, titulo, clasificacionContenido);
        this.artista = artista;
        this.album = album;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String mostrarInfo() {
        return "Canción: " + titulo + " | Artista: " + artista +
                " | Álbum: " + album + " | Clasificación: " + clasificacionContenido;
    }
}