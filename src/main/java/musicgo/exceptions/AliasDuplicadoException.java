package musicgo.exceptions;

/**
 * Se lanza al intentar registrar un usuario con un alias que ya existe.
 */
public class AliasDuplicadoException extends Exception {

    public AliasDuplicadoException(String alias) {
        super("El alias '" + alias + "' ya está registrado.");
    }
}
