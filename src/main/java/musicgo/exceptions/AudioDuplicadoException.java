package musicgo.exceptions;

/**
 * Se lanza al intentar añadir a una playlist un audio que ya contiene.
 */
public class AudioDuplicadoException extends Exception {

    public AudioDuplicadoException(String idAudio) {
        super("El audio '" + idAudio + "' ya está en la playlist.");
    }
}
