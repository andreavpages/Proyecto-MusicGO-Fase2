package musicgo.models.entities;

import java.time.LocalDate;

/**
 * Representa una transacción de compra realizada por un usuario.
 * Según el diagrama de análisis, no tiene métodos propios de negocio,
 * solo encapsula el registro del movimiento.
 */
public class Transaccion {

    private LocalDate fecha;
    private double monto;
    private String modalidadPago; // Ej: "TarjetaCredito", "TransferenciaBancaria", "SaldoVirtual"
    private String estado;        // Ej: "Completada", "Fallida", "Pendiente"

    public Transaccion(LocalDate fecha, double monto, String modalidadPago, String estado) {
        this.fecha = fecha;
        this.monto = monto;
        this.modalidadPago = modalidadPago;
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getModalidadPago() {
        return modalidadPago;
    }

    public void setModalidadPago(String modalidadPago) {
        this.modalidadPago = modalidadPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}