package gestioninmobiliaria.operaciones;


import gestioninmobiliaria.catalogos.*;
import gestioninmobiliaria.catalogos.Agente;
import gestioninmobiliaria.catalogos.Cliente;
import gestioninmobiliaria.catalogos.Propiedad;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorOperaciones {
    private final List<Visita> listaVisitas;
    private final CatalogoInmobiliario catalogo;

    // Constructor que inyecta la dependencia obligatoria del catálogo maestro
    public GestorOperaciones(CatalogoInmobiliario catalogo) {
        this.catalogo = catalogo;
        this.listaVisitas = new ArrayList<>();
    }

    public boolean asignarAgenteAPropiedad(String idPropiedad, String idAgente) {
        Propiedad propiedad = catalogo.buscarPropiedadPorId(idPropiedad);
        Agente agente = catalogo.buscarAgentePorId(idAgente);

        // Validación de existencia en el catálogo y validación de regla de negocio (estado)
        if (propiedad != null && agente != null) {
            if (propiedad.getEstado() == EstadoPropiedad.DISPONIBLE) {
                propiedad.setAgenteAsignado(agente);
                return true;
            }
        }
        return false;
    }

    public boolean registrarVisita(String idCliente, String idPropiedad, LocalDate fecha) {
        Cliente cliente = catalogo.buscarClientePorId(idCliente);
        Propiedad propiedad = catalogo.buscarPropiedadPorId(idPropiedad);

        // Validación de existencias maestras
        if (cliente != null && propiedad != null) {
            
            // Validación para prevenir visitas duplicadas exactas (mismo cliente, propiedad y fecha)
            for (Visita v : listaVisitas) {
                if (v.getCliente().getId().equalsIgnoreCase(idCliente) && 
                    v.getPropiedad().getId().equalsIgnoreCase(idPropiedad) && 
                    v.getFecha().equals(fecha)) {
                    return false; // Falla la validación, la visita ya existe
                }
            }

            // Si pasa las validaciones, se instancia e inserta la visita inmutable
            Visita nuevaVisita = new Visita(cliente, propiedad, fecha);
            listaVisitas.add(nuevaVisita);
            return true;
        }
        return false;
    }

    public boolean cambiarANegociacion(String idPropiedad) {
        Propiedad propiedad = catalogo.buscarPropiedadPorId(idPropiedad);

        if (propiedad != null) {
            propiedad.setEstado(EstadoPropiedad.EN_NEGOCIACION);
            return true;
        }
        return false;
    }

    public List<Visita> obtenerVisitasPorCliente(String idCliente) {
        List<Visita> visitasFiltradas = new ArrayList<>();
        
        for (Visita v : listaVisitas) {
            if (v.getCliente().getId().equalsIgnoreCase(idCliente)) {
                visitasFiltradas.add(v);
            }
        }
        
        // Retorna sublista blindada
        return Collections.unmodifiableList(visitasFiltradas);
    }

    // Retorna una vista inmutable del historial completo para proteger el encapsulamiento
    public List<Visita> getListaVisitas() {
        return Collections.unmodifiableList(listaVisitas);
    }
}
