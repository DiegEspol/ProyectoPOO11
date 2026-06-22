package gestioninmobiliaria.catalogos;

public class Terreno extends Propiedad {

    public Terreno(String id, String ubicacion, double precio) {
        super(id, ubicacion, precio);
    }

    @Override
    public String getTipo() {
        return "Terreno";
    }

    @Override
    public String toString() {
        return "Terreno - ID: " + getId() + 
               " | Ubicacion: " + getUbicacion() + 
               " | Precio: $" + getPrecio() + 
               " | Estado: " + getEstado() + 
               " | Agente: " + (getAgenteAsignado() != null ? getAgenteAsignado().getNombre() : "Ninguno");
    }
}