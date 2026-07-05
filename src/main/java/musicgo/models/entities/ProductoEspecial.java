package musicgo.models.entities;

/**
 * Representa un producto especial dentro del catálogo de MusicGO
 * (ej. suscripción premium, merchandising digital, etc).
 * Según el diagrama de análisis, esta clase no tiene métodos propios de negocio:
 * solo encapsula sus datos.
 */
public class ProductoEspecial {

    private String nombre;
    private double precio;

    public ProductoEspecial(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}