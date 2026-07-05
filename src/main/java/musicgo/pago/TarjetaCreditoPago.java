package musicgo.pago;

public class TarjetaCreditoPago implements MetodoPago {

    private String numeroTarjeta;
    private String fechaExpiracion;
    private String cvv;

    public TarjetaCreditoPago(String numeroTarjeta, String fechaExpiracion, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    @Override
    public boolean procesarPago(double monto) {
        if (numeroTarjeta == null || numeroTarjeta.length() != 16) {
            return false;
        }
        if (cvv == null || cvv.length() != 3) {
            return false;
        }
        return true;
    }
}