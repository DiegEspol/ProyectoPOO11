package gestioninmobiliaria.operaciones;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuOperacionesEIntermediacion {
    private final Scanner scanner;
    // CORRECCIÓN: Apuntar al gestor del propio módulo de operaciones
    private final GestorOperaciones gestor;
    
    public MenuOperacionesEIntermediacion(GestorOperaciones gestor) {
        this.gestor = gestor;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n=========================================");
            System.out.println(" MODULO DE OPERACIONES E INTERMEDIACION  ");
            System.out.println("=========================================");
            System.out.println("1. Asignar agente a una propiedad");
            System.out.println("2. Registrar nueva visita");
            System.out.println("3. Cambiar propiedad a 'En Negociacion'");
            System.out.println("4. Consultar visitas por cliente");
            System.out.println("5. Listar historial completo de visitas");
            System.out.println("6. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                opcion = 0; 
                scanner.nextLine(); 
            }

            switch (opcion) {
                case 1:
                    auxiliarAsignarAgente();
                    break;
                case 2:
                    auxiliarRegistrarVisita();
                    break;
                case 3:
                    auxiliarCambiarANegociacion();
                    break;
                case 4:
                    auxiliarObtenerVisitasCliente();
                    break;
                case 5:
                    auxiliarListarHistorialCompleto();
                    break;
                case 6:
                    System.out.println("Cerrando menu de Operaciones... Regresando al menu principal.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void auxiliarAsignarAgente() {
        System.out.println("\n--- ASIGNAR AGENTE A PROPIEDAD ---");
        System.out.print("Ingrese el ID de la propiedad: ");
        String idPropiedad = scanner.nextLine();
        
        System.out.print("Ingrese el ID del agente: ");
        String idAgente = scanner.nextLine();

        // Línea 75: Ahora compila perfectamente con GestorOperaciones
        boolean exito = gestor.asignarAgenteAPropiedad(idPropiedad, idAgente);

        if (exito) {
            System.out.println("EXITO: Agente asignado correctamente a la propiedad.");
        } else {
            System.out.println("ERROR: No se pudo asignar. Verifique que los IDs existan y que la propiedad este DISPONIBLE.");
        }
    }

    private void auxiliarRegistrarVisita() {
        System.out.println("\n--- REGISTRAR VISITA ---");
        System.out.print("Ingrese el ID del cliente: ");
        String idCliente = scanner.nextLine();
        
        System.out.print("Ingrese el ID de la propiedad a visitar: ");
        String idPropiedad = scanner.nextLine();
        
        System.out.print("Ingrese la fecha de la visita (Formato YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        
        LocalDate fechaVisita;
        try {
            fechaVisita = LocalDate.parse(fechaStr);
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: Formato de fecha incorrecto. Debe utilizar exactamente el formato YYYY-MM-DD.");
            return;
        }

        // Línea 103: Ahora compila perfectamente
        boolean exito = gestor.registrarVisita(idCliente, idPropiedad, fechaVisita);

        if (exito) {
            System.out.println("EXITO: Visita registrada correctamente.");
        } else {
            System.out.println("ERROR: Fallo en el registro. Verifique que los IDs existan y que la visita no este duplicada.");
        }
    }

    private void auxiliarCambiarANegociacion() {
        System.out.println("\n--- CAMBIAR PROPIEDAD A EN NEGOCIACION ---");
        System.out.print("Ingrese el ID de la propiedad interesada: ");
        String idPropiedad = scanner.nextLine();

        // Línea 115: Ahora compila perfectamente
        boolean exito = gestor.cambiarANegociacion(idPropiedad);

        if (exito) {
            System.out.println("EXITO: La propiedad ha sido marcada como 'EN_NEGOCIACION'.");
        } else {
            System.out.println("ERROR: No se pudo cambiar el estado. Verifique que el ID de la propiedad exista.");
        }
    }

    private void auxiliarObtenerVisitasCliente() {
        System.out.println("\n--- CONSULTAR VISITAS POR CLIENTE ---");
        System.out.print("Ingrese el ID del cliente a consultar: ");
        String idCliente = scanner.nextLine();

        // Línea 131: Ahora compila perfectamente
        List<Visita> visitasCliente = gestor.obtenerVisitasPorCliente(idCliente);

        if (visitasCliente.isEmpty()) {
            System.out.println("El cliente no tiene visitas registradas en el sistema.");
        } else {
            System.out.println("Visitas encontradas para el cliente:");
            for (Visita v : visitasCliente) {
                System.out.println(v.toString());
            }
        }
    }

    private void auxiliarListarHistorialCompleto() {
        System.out.println("\n--- HISTORIAL COMPLETO DE VISITAS ---");
        // Línea 145: Ahora compila perfectamente
        List<Visita> todasLasVisitas = gestor.getListaVisitas();
        
        if (todasLasVisitas.isEmpty()) {
            System.out.println("No hay visitas registradas en el sistema en este momento.");
            return;
        }

        for (Visita v : todasLasVisitas) {
            System.out.println(v.toString());
        }
    }
}