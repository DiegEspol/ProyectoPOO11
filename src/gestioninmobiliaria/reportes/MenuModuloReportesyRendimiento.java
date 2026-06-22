package gestioninmobiliaria.reportes;



import java.util.Scanner;

public class MenuModuloReportesyRendimiento {
    private final VisualizadorReportes visualizador;
    private final Scanner scanner;

    public MenuModuloReportesyRendimiento(VisualizadorReportes visualizador) {
        this.visualizador = visualizador;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n=========================================");
            System.out.println("    MÓDULO DE REPORTES Y RENDIMIENTO     ");
            System.out.println("=========================================");
            System.out.println("1. Mostrar Resumen Financiero (Ingresos)");
            System.out.println("2. Mostrar Ocupación de Inventario");
            System.out.println("3. Mostrar Ranking de Agentes");
            System.out.println("4. Mostrar Auditoría Mensual de Contratos");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            // Validación de entrada para evitar excepciones y caídas del sistema
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } else {
                opcion = 0; // Fuerza la entrada al bloque default
                scanner.nextLine(); // Limpiar la entrada incorrecta
            }

            switch (opcion) {
                case 1:
                    auxiliarMostrarResumenFinanciero();
                    break;
                case 2:
                    auxiliarMostrarOcupacionInventario();
                    break;
                case 3:
                    auxiliarMostrarRankingAgentes();
                    break;
                case 4:
                    auxiliarMostrarAuditoriaMensual();
                    break;
                case 5:
                    System.out.println("Saliendo del módulo de Reportes... Regresando al menú principal.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void auxiliarMostrarResumenFinanciero() {
        // La lógica de extracción y formato ya está encapsulada en el visualizador
        visualizador.mostrarResumenFinanciero();
    }

    private void auxiliarMostrarOcupacionInventario() {
        visualizador.mostrarOcupacionInventario();
    }

    private void auxiliarMostrarRankingAgentes() {
        visualizador.mostrarRankingAgentes();
    }

    private void auxiliarMostrarAuditoriaMensual() {
        System.out.println("\n--- FILTRO PARA AUDITORÍA MENSUAL ---");
        
        System.out.print("Ingrese el mes a consultar (1-12): ");
        int mes = 0;
        if (scanner.hasNextInt()) {
            mes = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
        } else {
            System.out.println("ERROR: El mes ingresado debe ser un número entero válido.");
            scanner.nextLine();
            return;
        }

        System.out.print("Ingrese el año a consultar (Ej. 2024): ");
        int anio = 0;
        if (scanner.hasNextInt()) {
            anio = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
        } else {
            System.out.println("ERROR: El año ingresado debe ser un número entero válido.");
            scanner.nextLine();
            return;
        }

        // Llamada a la capa de presentación enviando los parámetros solicitados por teclado
        visualizador.mostrarAuditoriaMensual(mes, anio);
    }
}