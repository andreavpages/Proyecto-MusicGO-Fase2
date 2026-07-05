package musicgo.pago;

public class TransferenciaBancariaPago implements MetodoPago {

    private String banco;
    private String numeroCuenta;

    public TransferenciaBancariaPago(String banco, String numeroCuenta) {
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    @Override
    public boolean procesarPago(double monto) {
        if (banco == null || banco.isEmpty() || numeroCuenta == null || numeroCuenta.isEmpty()) {
            return false;
        }
        return true;
    }
}