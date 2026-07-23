package musicgo.exceptions;

/**
 * Se lanza al intentar iniciar sesión con un alias que no existe.
 */
public class UsuarioNoEncontradoException extends Exception {

    public UsuarioNoEncontradoException(String alias) {
        super("No existe ningún usuario registrado con el alias '" + alias + "'.");
    }
}
