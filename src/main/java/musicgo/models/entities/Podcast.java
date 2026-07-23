package musicgo.models.entities;

/**
 * Representa un episodio de podcast dentro del catálogo de MusicGO.
 * Hereda de Audio y añade sus propios atributos: anfitrión y descripción.
 */
public class Podcast extends Audio {

    private String anfitrion;
    private String descripcion;

    public Podcast(String id, String titulo, String clasificacionContenido, int duracionSegundos,
                   String anfitrion, String descripcion) {
        super(id, titulo, clasificacionContenido, duracionSegundos);
        this.anfitrion = anfitrion;
        this.descripcion = descripcion;
    }

    public String getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(String anfitrion) {
        this.anfitrion = anfitrion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String mostrarInfo() {
        return "Podcast: " + titulo + " | Anfitrión: " + anfitrion +
                " | Descripción: " + descripcion + " | Duración: " + getDuracionFormateada() +
                " | Clasificación: " + clasificacionContenido;
    }
}