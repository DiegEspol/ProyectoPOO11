package gestioninmobiliaria.operaciones;


import gestioninmobiliaria.catalogos.Cliente;
import gestioninmobiliaria.catalogos.Propiedad;
import java.time.LocalDate;

public class Visita {
    // Atributos definidos como final para garantizar la inmutabilidad exigida en el diseño
    private final Cliente cliente;
    private final Propiedad propiedad;
    private final LocalDate fecha;

    public Visita(Cliente cliente, Propiedad propiedad, LocalDate fecha) {
        this.cliente = cliente;
        this.propiedad = propiedad;
        this.fecha = fecha;
    }

    // Métodos de acceso exclusivos de lectura (Sin setters)
    public Cliente getCliente() {
        return cliente;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    // Sobreescritura del método toString aprovechando el polimorfismo de Propiedad
    @Override
    public String toString() {
        return "Fecha Visita: " + fecha + 
               " | Cliente: " + cliente.getNombre() + " (ID: " + cliente.getId() + ")" +
               " | Inmueble: [" + propiedad.toString() + "]"; 
    }
}
