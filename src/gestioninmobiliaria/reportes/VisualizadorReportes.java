package gestioninmobiliaria.reportes;


import gestioninmobiliaria.contratos.Contrato;
import gestioninmobiliaria.catalogos.*;
import gestioninmobiliaria.catalogos.Agente;
import java.util.List;
import java.util.Map;

public class VisualizadorReportes {
    private final GestorReportes reportador;

    public VisualizadorReportes(GestorReportes reportador) {
        this.reportador = reportador;
    }

    public void mostrarResumenFinanciero() {
        System.out.println("\n=========================================");
        System.out.println("          RESUMEN FINANCIERO             ");
        System.out.println("=========================================");
        
        double total = reportador.obtenerIngresosTotalesAgencia();
        System.out.printf("Ingresos Totales (Comisiones Generadas): $%.2f%n", total);
        
        System.out.println("=========================================\n");
    }

    public void mostrarOcupacionInventario() {
        System.out.println("\n=========================================");
        System.out.println("         OCUPACION DE INVENTARIO         ");
        System.out.println("=========================================");
        
        Map<EstadoPropiedad, Integer> ocupacion = reportador.obtenerPropiedadesPorEstado();
        
        // Recorrer las llaves del enum en orden y mostrar las métricas cuantitativas
        for (EstadoPropiedad estado : EstadoPropiedad.values()) {
            System.out.printf("%-18s : %d unidades%n", estado.name(), ocupacion.get(estado));
        }
        
        System.out.println("=========================================\n");
    }

    public void mostrarRankingAgentes() {
        System.out.println("\n=========================================");
        System.out.println("          RANKING DE AGENTES             ");
        System.out.println("=========================================");
        
        List<Agente> agentesTop = reportador.obtenerTopAgentesPorComision();
        
        // Validación de escenario vacío
        if (agentesTop.isEmpty()) {
            System.out.println("No hay agentes registrados en el sistema.");
        } else {
            System.out.printf("%-5s | %-25s | %s%n", "Pos", "Nombre del Agente", "Comisiones Acumuladas");
            System.out.println("------------------------------------------------------------");
            
            int posicion = 1;
            for (Agente a : agentesTop) {
                System.out.printf("%-5d | %-25s | $%.2f%n", posicion, a.getNombre() + " (" + a.getId() + ")", a.getComisionesAcumuladas());
                posicion++;
            }
        }
        
        System.out.println("=========================================\n");
    }

    public void mostrarAuditoriaMensual(int mes, int anio) {
        System.out.println("\n=========================================");
        System.out.printf("       AUDITORIA MENSUAL (%02d/%d)       %n", mes, anio);
        System.out.println("=========================================");
        
        List<Contrato> transaccionesMensuales = reportador.obtenerContratosPorMesAnio(mes, anio);
        
        // Validación de escenario sin transacciones en el rango de fechas
        if (transaccionesMensuales.isEmpty()) {
            System.out.println("Aviso: No se encontraron transacciones registradas en el periodo indicado.");
        } else {
            for (Contrato contrato : transaccionesMensuales) {
                // Invocación polimórfica del método toString de ContratoVenta o ContratoAlquiler
                System.out.println(contrato.toString());
            }
        }
        
        System.out.println("=========================================\n");
    }
}