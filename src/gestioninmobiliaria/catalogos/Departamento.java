package gestioninmobiliaria.catalogos;

public class Departamento extends Propiedad {

    public Departamento(String id, String ubicacion, double precio) {
        super(id, ubicacion, precio);
    }

    @Override
    public String getTipo() {
        return "Departamento";
    }

    @Override
    public String toString() {
        return "Departamento - ID: " + getId() + 
               " | Ubicacion: " + getUbicacion() + 
               " | Precio: $" + getPrecio() + 
               " | Estado: " + getEstado() + 
               " | Agente: " + (getAgenteAsignado() != null ? getAgenteAsignado().getNombre() : "Ninguno");
    }
}