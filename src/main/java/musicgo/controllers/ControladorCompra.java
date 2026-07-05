package musicgo.controllers;

import musicgo.models.entities.ProductoEspecial;
import musicgo.models.entities.Transaccion;
import musicgo.pago.MetodoPago;

import java.time.LocalDate;

/**
 * Controlador encargado de orquestar el flujo de compra de un producto especial.
 * Coordina la verificación del usuario, el procesamiento del pago (a través de
 * la interfaz MetodoPago) y el registro de la transacción resultante.
 */
public class ControladorCompra {

    /**
     * Procesa la compra de un producto especial usando la modalidad de pago indicada.
     *
     * @param producto   el producto especial a comprar
     * @param metodoPago la implementación de MetodoPago elegida por el usuario
     *                   (SaldoVirtualPago, TransferenciaBancariaPago o TarjetaCreditoPago)
     * @return la Transaccion generada, con su estado ("Completada" o "Fallida")
     */
    public Transaccion procesarCompra(ProductoEspecial producto, MetodoPago metodoPago) {
        double monto = producto.getPrecio();
        boolean exito = metodoPago.procesarPago(monto);

        String estado = exito ? "Completada" : "Fallida";

        return new Transaccion(LocalDate.now(), monto, metodoPago.getClass().getSimpleName(), estado);
    }
}