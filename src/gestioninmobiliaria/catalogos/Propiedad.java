package gestioninmobiliaria.catalogos;

/**
 * Clase abstracta que sirve de plantilla base para todos los inmuebles del sistema.
 */
public abstract class Propiedad {
    private final String id; // Código único no modificable
    private String ubicacion;
    private double precio;
    private EstadoPropiedad estado;
    private Agente agenteAsignado; // Puede ser null inicialmente

    public Propiedad(String id, String ubicacion, double precio) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.estado = EstadoPropiedad.DISPONIBLE; // Inicia por defecto disponible
        this.agenteAsignado = null;
    }

    // Método abstracto obligatorio para las subclases
    public abstract String getTipo();

    // Métodos de acceso (Getters y Setters)
    public String getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoPropiedad getEstado() {
        return estado;
    }

    public void setEstado(EstadoPropiedad estado) {
        this.estado = estado;
    }

    public Agente getAgenteAsignado() {
        return agenteAsignado;
    }

    public void setAgenteAsignado(Agente agenteAsignado) {
        this.agenteAsignado = agenteAsignado;
    }
}