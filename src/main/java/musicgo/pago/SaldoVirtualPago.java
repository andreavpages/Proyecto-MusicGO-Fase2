package musicgo.pago;

import musicgo.models.entities.Usuario;

public class SaldoVirtualPago implements MetodoPago {

    private Usuario usuario;

    public SaldoVirtualPago(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean procesarPago(double monto) {
        if (!usuario.verificarSaldo(monto)) {
            return false;
        }
        usuario.setSaldoVirtual(usuario.getSaldoVirtual() - monto);
        return true;
    }
}