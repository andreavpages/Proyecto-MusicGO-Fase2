package musicgo.models.entities;

/**
 * Representa a un usuario final de la plataforma MusicGO.
 */
public class Usuario {

    private String alias;
    private String nombre;

    public Usuario(String alias, String nombre) {
        this.alias = alias;
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}