package gestioninmobiliaria.catalogos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controlador central del módulo encargado de almacenar y administrar los catálogos en memoria RAM.
 */
public class CatalogoInmobiliario {
    private final List<Propiedad> listaPropiedades;
    private final List<Cliente> listaClientes;
    private final List<Agente> listaAgentes;

    public CatalogoInmobiliario() {
        this.listaPropiedades = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaAgentes = new ArrayList<>();
    }

    public void precargarDatosEjemplo() {
        // Personas iniciales
        registrarAgente(new Agente("A01", "Carlos Mendoza"));
        registrarCliente(new Cliente("C01", "Ana Gomez"));
        
        // Propiedades iniciales de ejemplo
        registrarPropiedad(new Casa("P01", "Norte, Calle Alborada", 125000.0));
        registrarPropiedad(new Departamento("P02", "Sur, Samborondon", 850.0));
        registrarPropiedad(new Terreno("P03", "Via a la Costa KM 20", 45000.0));
    }

    public void registrarPropiedad(Propiedad p) {
        if (p != null && buscarPropiedadPorId(p.getId()) == null) {
            listaPropiedades.add(p);
        }
    }

    public void registrarCliente(Cliente c) {
        if (c != null) {
            listaClientes.add(c);
        }
    }

    public void registrarAgente(Agente a) {
        if (a != null) {
            listaAgentes.add(a);
        }
    }

    public Propiedad buscarPropiedadPorId(String id) {
        for (Propiedad p : listaPropiedades) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null; // Retorna null si no se encuentra coincidencia
    }
     public Cliente buscarClientePorId(String id) {
        for (Cliente c : listaClientes) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public Agente buscarAgentePorId(String id) {
        for (Agente a : listaAgentes) {
            if (a.getId().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    // Sobrecarga de Métodos de Filtrado (Polimorfismo Estático)

    /**
     * Filtra las propiedades evaluando la clase concreta mediante reflexión/tipo.
     */
    public List<Propiedad> filtrarPropiedades(Class<? extends Propiedad> tipoInmueble) {
        List<Propiedad> resultado = new ArrayList<>();
        for (Propiedad p : listaPropiedades) {
            if (tipoInmueble.isInstance(p)) {
                resultado.add(p);
            }
        }
        return Collections.unmodifiableList(resultado);
    }

    /**
     * Filtra las propiedades cuyo precio base sea menor o igual al precio máximo indicado.
     */
    public List<Propiedad> filtrarPropiedades(double precioMaximo) {
        List<Propiedad> resultado = new ArrayList<>();
        for (Propiedad p : listaPropiedades) {
            if (p.getPrecio() <= precioMaximo) {
                resultado.add(p);
            }
        }
        return Collections.unmodifiableList(resultado);
    }

    /**
     * Filtra las propiedades cuya ubicación contenga la cadena buscada (case-insensitive).
     */
    public List<Propiedad> filtrarPropiedades(String ubicacion) {
        List<Propiedad> resultado = new ArrayList<>();
        for (Propiedad p : listaPropiedades) {
            if (p.getUbicacion().toLowerCase().contains(ubicacion.toLowerCase())) {
                resultado.add(p);
            }
        }
        return Collections.unmodifiableList(resultado);
    }

    // Métodos de Listado Blindados (Seguridad de Encapsulamiento)

    public List<Propiedad> getListaPropiedades() {
        return Collections.unmodifiableList(listaPropiedades);
    }

    public List<Cliente> getListaClientes() {
        return Collections.unmodifiableList(listaClientes);
    }

    public List<Agente> getListaAgentes() {
        return Collections.unmodifiableList(listaAgentes);
    }
}