package musicgo.models.entities;

/**
 * Clase abstracta que representa un contenido de audio dentro de la plataforma MusicGO.
 * Es la superclase de Cancion y Podcast, y encapsula únicamente los atributos
 * y comportamientos comunes a todo contenido reproducible.
 *
 * Principio SRP: esta clase tiene una única responsabilidad -> representar
 * los datos esenciales de un audio y su forma de mostrarse. No conoce nada
 * de persistencia, de JavaFX ni de reglas de negocio de compra o reproducción.
 */
public abstract class Audio {

    protected String id;
    protected String titulo;
    protected String clasificacionContenido; // Ej: "Explícito", "Apto para todo público"
    protected int duracionSegundos;

    public Audio(String id, String titulo, String clasificacionContenido, int duracionSegundos) {
        this.id = id;
        this.titulo = titulo;
        this.clasificacionContenido = clasificacionContenido;
        this.duracionSegundos = duracionSegundos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClasificacionContenido() {
        return clasificacionContenido;
    }

    public void setClasificacionContenido(String clasificacionContenido) {
        this.clasificacionContenido = clasificacionContenido;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    /**
     * Formatea la duración como mm:ss para mostrarla en la interfaz.
     */
    public String getDuracionFormateada() {
        return String.format("%d:%02d", duracionSegundos / 60, duracionSegundos % 60);
    }

    /**
     * Cada subclase debe definir cómo se presenta su información específica.
     * Esto es polimorfismo puro: Cancion y Podcast implementarán este método
     * de forma distinta, sin que el resto del sistema necesite saber cuál es cuál.
     */
    public abstract String mostrarInfo();
}