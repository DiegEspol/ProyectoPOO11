package gestioninmobiliaria.reportes;

import gestioninmobiliaria.contratos.GestorContratos;
import gestioninmobiliaria.contratos.Contrato;
import gestioninmobiliaria.catalogos.*;
import gestioninmobiliaria.catalogos.Agente;
import gestioninmobiliaria.catalogos.Propiedad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GestorReportes {
    private final CatalogoInmobiliario catalogo;
    private final GestorContratos gestorContratos;

    // Constructor con inyección de las dependencias maestras
    public GestorReportes(CatalogoInmobiliario catalogo, GestorContratos gestorContratos) {
        this.catalogo = catalogo;
        this.gestorContratos = gestorContratos;
    }

    public double obtenerIngresosTotalesAgencia() {
        double ingresosTotales = 0.0;
        
        // Itera sobre el historial inmutable aplicando polimorfismo dinámico
        for (Contrato contrato : gestorContratos.getListaContratos()) {
            ingresosTotales += contrato.calcularComision();
        }
        
        return ingresosTotales;
    }

    public Map<EstadoPropiedad, Integer> obtenerPropiedadesPorEstado() {
        Map<EstadoPropiedad, Integer> conteo = new EnumMap<>(EstadoPropiedad.class);
        
        // Inicializar el mapa con todos los estados posibles en 0
        for (EstadoPropiedad estado : EstadoPropiedad.values()) {
            conteo.put(estado, 0);
        }

        // Recorrer el inventario maestro y realizar el conteo
        for (Propiedad p : catalogo.getListaPropiedades()) {
            EstadoPropiedad estadoActual = p.getEstado();
            conteo.put(estadoActual, conteo.get(estadoActual) + 1);
        }

        // Retornar la estructura blindada y de solo lectura
        return Collections.unmodifiableMap(conteo);
    }

    public List<Agente> obtenerTopAgentesPorComision() {
        // 1. Extraer los agentes y clonarlos en una lista local e independiente
        List<Agente> topAgentes = new ArrayList<>(catalogo.getListaAgentes());
        
        // 2. Ordenar la lista descendentemente por las comisiones acumuladas
        topAgentes.sort((a1, a2) -> Double.compare(a2.getComisionesAcumuladas(), a1.getComisionesAcumuladas()));
        
        // 3. Retornar vista inmutable de la copia ordenada
        return Collections.unmodifiableList(topAgentes);
    }

    public List<Contrato> obtenerContratosPorMesAnio(int mes, int anio) {
        List<Contrato> transaccionesFiltradas = new ArrayList<>();
        
        // Filtrar histórico global
        for (Contrato c : gestorContratos.getListaContratos()) {
            if (c.getFechaRegistro().getMonthValue() == mes && c.getFechaRegistro().getYear() == anio) {
                transaccionesFiltradas.add(c);
            }
        }
        
        // Retornar sublista inmutable
        return Collections.unmodifiableList(transaccionesFiltradas);
    }
}