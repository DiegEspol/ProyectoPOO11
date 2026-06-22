package gestioninmobiliaria.catalogos;

public class Casa extends Propiedad {

    public Casa(String id, String ubicacion, double precio) {
        super(id, ubicacion, precio);
    }

    @Override
    public String getTipo() {
        return "Casa";
    }

    @Override
    public String toString() {
        return "Casa - ID: " + getId() + 
               " | Ubicacion: " + getUbicacion() + 
               " | Precio: $" + getPrecio() + 
               " | Estado: " + getEstado() + 
               " | Agente: " + (getAgenteAsignado() != null ? getAgenteAsignado().getNombre() : "Ninguno");
    }
}