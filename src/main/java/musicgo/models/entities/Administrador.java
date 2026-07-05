package musicgo.models.entities;

/**
 * Representa a un administrador de la plataforma MusicGO.
 * Su validación se hace contra un archivo JSON exclusivo (administradores.json),
 * separado del registro público de usuarios.
 */
public class Administrador {

    private String usuario;
    private String password;

    public Administrador(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}