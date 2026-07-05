package musicgo.models.entities;

/**
 * Representa a un usuario final de la plataforma MusicGO.
 * Contiene su información de cuenta, saldo virtual y restricciones de edad/contenido.
 */
public class Usuario {

    private String alias;
    private String correo;
    private String password;
    private double saldoVirtual;
    private boolean restriccionEdad;

    public Usuario(String alias, String correo, String password,
                   double saldoVirtual, boolean restriccionEdad) {
        this.alias = alias;
        this.correo = correo;
        this.password = password;
        this.saldoVirtual = saldoVirtual;
        this.restriccionEdad = restriccionEdad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSaldoVirtual() {
        return saldoVirtual;
    }

    public void setSaldoVirtual(double saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    public boolean isRestriccionEdad() {
        return restriccionEdad;
    }

    public void setRestriccionEdad(boolean restriccionEdad) {
        this.restriccionEdad = restriccionEdad;
    }

    /**
     * Verifica si el usuario tiene saldo suficiente para cubrir un monto dado.
     * Esta es lógica propia de la entidad Usuario (le pertenece a sus propios datos),
     * por eso vive aquí y no en el controlador.
     */
    public boolean verificarSaldo(double monto) {
        return this.saldoVirtual >= monto;
    }
}