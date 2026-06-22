package gestioninmobiliaria.contratos;


import gestioninmobiliaria.catalogos.*;
import gestioninmobiliaria.catalogos.Agente;
import gestioninmobiliaria.catalogos.Cliente;
import gestioninmobiliaria.catalogos.Propiedad;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorContratos {
    private final List<Contrato> listaContratos;
    private final CatalogoInmobiliario catalogo;

    // Constructor con inyección obligatoria del repositorio maestro de la memoria RAM
    public GestorContratos(CatalogoInmobiliario catalogo) {
        this.catalogo = catalogo;
        this.listaContratos = new ArrayList<>();
    }

    public boolean registrarContratoVenta(String idContrato, LocalDate fechaRegistro, String idCliente, String idAgente, String idPropiedad) {
        Cliente cliente = catalogo.buscarClientePorId(idCliente);
        Agente agente = catalogo.buscarAgentePorId(idAgente);
        Propiedad propiedad = catalogo.buscarPropiedadPorId(idPropiedad);

        // Validación de existencia rigurosa de las entidades involucradas
        if (cliente == null || agente == null || propiedad == null) {
            return false;
        }

        // Validación de Regla de Negocio: la propiedad debe estar DISPONIBLE o EN_NEGOCIACION
        if (propiedad.getEstado() != EstadoPropiedad.DISPONIBLE && propiedad.getEstado() != EstadoPropiedad.EN_NEGOCIACION) {
            return false;
        }

        // Validación de unicidad transaccional: evita ID de contratos duplicados en memoria
        for (Contrato c : listaContratos) {
            if (c.getIdContrato().equalsIgnoreCase(idContrato)) {
                return false;
            }
        }

        // Instanciación, ejecución polimórfica del cierre financiero e inserción histórica
        ContratoVenta nuevoContrato = new ContratoVenta(idContrato, fechaRegistro, cliente, agente, propiedad);
        nuevoContrato.procesarCierre();
        listaContratos.add(nuevoContrato);
        return true;
    }

    public boolean registrarContratoAlquiler(String idContrato, LocalDate fechaRegistro, String idCliente, String idAgente, String idPropiedad, LocalDate fechaInicio, int mesesDuracion) {
        Cliente cliente = catalogo.buscarClientePorId(idCliente);
        Agente agente = catalogo.buscarAgentePorId(idAgente);
        Propiedad propiedad = catalogo.buscarPropiedadPorId(idPropiedad);

        // Validación de existencias y parámetros cuantitativos lógicos
        if (cliente == null || agente == null || propiedad == null || mesesDuracion <= 0) {
            return false;
        }

        // Validación de Regla de Negocio: la propiedad debe estar DISPONIBLE o EN_NEGOCIACION
        if (propiedad.getEstado() != EstadoPropiedad.DISPONIBLE && propiedad.getEstado() != EstadoPropiedad.EN_NEGOCIACION) {
            return false;
        }

        // Validación de unicidad transaccional
        for (Contrato c : listaContratos) {
            if (c.getIdContrato().equalsIgnoreCase(idContrato)) {
                return false;
            }
        }

        // Instanciación, ejecución polimórfica del cierre financiero e inserción histórica
        ContratoAlquiler nuevoContrato = new ContratoAlquiler(idContrato, fechaRegistro, cliente, agente, propiedad, fechaInicio, mesesDuracion);
        nuevoContrato.procesarCierre();
        listaContratos.add(nuevoContrato);
        return true;
    }

    public void evaluarCierreContratosAlquiler(LocalDate fechaActual) {
        // Iteración de la colección polimórfica aislando dinámicamente los alquileres con 'instanceof'
        for (Contrato contrato : listaContratos) {
            if (contrato instanceof ContratoAlquiler) {
                ContratoAlquiler alquiler = (ContratoAlquiler) contrato;
                
                // Si está vigente y la fecha actual de evaluación superó la fecha de término, se libera
                if (alquiler.getEstado() == EstadoContrato.ACTIVO && fechaActual.isAfter(alquiler.getFechaFin())) {
                    alquiler.finalizarContrato();
                }
            }
        }
    }

    // Retorna una colección estrictamente inmutable para blindar el historial contra manipulaciones externas
    public List<Contrato> getListaContratos() {
        return Collections.unmodifiableList(listaContratos);
    }
}