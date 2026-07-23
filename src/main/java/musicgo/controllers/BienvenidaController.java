package musicgo.controllers;

/**
 * Controlador de la fase 1 (Bienvenida). No tiene lógica de negocio ni
 * persistencia propia: solo existe para completar el patrón de un
 * controlador por fase de GUI.
 */
public class BienvenidaController {

    public void continuar() {
        // La vista decide qué pantalla mostrar a continuación; este método
        // existe para que el listener de la vista no contenga lógica propia.
    }

    public void cerrarAplicacion() {
        System.exit(0);
    }
}
