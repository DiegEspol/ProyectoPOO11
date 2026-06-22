package gestioninmobiliaria.contratos;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuContratoyCierreComercial {
    private final GestorContratos gestor;
    private final Scanner scanner;

    public MenuContratoyCierreComercial(GestorContratos gestor) {
        this.gestor = gestor;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n=========================================");
            System.out.println(" MODULO DE CONTRATOS Y CIERRE COMERCIAL  ");
            System.out.println("=========================================");
            System.out.println("1. Registrar Contrato de Venta");
            System.out.println("2. Registrar Contrato de Alquiler");
            System.out.println("3. Evaluar Vencimiento de Alquileres");
            System.out.println("4. Listar Historial Financiero de Contratos");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            // Validación de entrada para evitar excepciones del sistema
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } else {
                opcion = 0; // Fuerza la entrada al bloque default
                scanner.nextLine(); // Limpiar la entrada incorrecta
            }

            switch (opcion) {
                case 1:
                    auxiliarRegistrarVenta();
                    break;
                case 2:
                    auxiliarRegistrarAlquiler();
                    break;
                case 3:
                    auxiliarEvaluarAlquileres();
                    break;
                case 4:
                    auxiliarListarContratos();
                    break;
                case 5:
                    System.out.println("Saliendo del modulo de Contratos... Regresando al menu principal.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void auxiliarRegistrarVenta() {
        System.out.println("\n--- REGISTRAR CONTRATO DE VENTA ---");
        System.out.print("Ingrese un ID unico para el contrato: ");
        String idContrato = scanner.nextLine();
        
        System.out.print("Ingrese la fecha de registro (YYYY-MM-DD): ");
        LocalDate fechaRegistro = leerFechaSegura();
        if (fechaRegistro == null) return;

        System.out.print("Ingrese el ID del cliente comprador: ");
        String idCliente = scanner.nextLine();
        
        System.out.print("Ingrese el ID del agente asesor: ");
        String idAgente = scanner.nextLine();
        
        System.out.print("Ingrese el ID de la propiedad a vender: ");
        String idPropiedad = scanner.nextLine();

        // El gestor se encarga de las validaciones de negocio e integridad
        boolean exito = gestor.registrarContratoVenta(idContrato, fechaRegistro, idCliente, idAgente, idPropiedad);

        if (exito) {
            System.out.println("EXITO: Contrato de venta registrado. El inmueble ahora esta VENDIDO y la comision fue asignada.");
        } else {
            System.out.println("ERROR: No se pudo registrar la venta. Verifique que los IDs existan, que la propiedad este DISPONIBLE o EN NEGOCIACION, y que el ID del contrato no este repetido.");
        }
    }

    private void auxiliarRegistrarAlquiler() {
        System.out.println("\n--- REGISTRAR CONTRATO DE ALQUILER ---");
        System.out.print("Ingrese un ID unico para el contrato: ");
        String idContrato = scanner.nextLine();
        
        System.out.print("Ingrese la fecha de registro/firma (YYYY-MM-DD): ");
        LocalDate fechaRegistro = leerFechaSegura();
        if (fechaRegistro == null) return;

        System.out.print("Ingrese el ID del cliente arrendatario: ");
        String idCliente = scanner.nextLine();
        
        System.out.print("Ingrese el ID del agente asesor: ");
        String idAgente = scanner.nextLine();
        
        System.out.print("Ingrese el ID de la propiedad a alquilar: ");
        String idPropiedad = scanner.nextLine();

        System.out.print("Ingrese la fecha de inicio de vigencia (YYYY-MM-DD): ");
        LocalDate fechaInicio = leerFechaSegura();
        if (fechaInicio == null) return;

        System.out.print("Ingrese la duracion del contrato en meses: ");
        int mesesDuracion = 0;
        if (scanner.hasNextInt()) {
            mesesDuracion = scanner.nextInt();
            scanner.nextLine(); 
        } else {
            System.out.println("ERROR: La duracion debe ser un numero entero.");
            scanner.nextLine();
            return;
        }

        // El gestor procesa y valida la lógica de negocio
        boolean exito = gestor.registrarContratoAlquiler(idContrato, fechaRegistro, idCliente, idAgente, idPropiedad, fechaInicio, mesesDuracion);

        if (exito) {
            System.out.println("EXITO: Contrato de alquiler registrado. El inmueble ahora esta ALQUILADO y la comision fue asignada.");
        } else {
            System.out.println("ERROR: No se pudo registrar el alquiler. Verifique que los datos sean correctos, que la propiedad este DISPONIBLE y el contrato no este duplicado.");
        }
    }

    private void auxiliarEvaluarAlquileres() {
        System.out.println("\n--- EVALUAR VENCIMIENTO DE CONTRATOS DE ALQUILER ---");
        System.out.println("NOTA: Este proceso revisara todos los alquileres activos y liberara las propiedades cuyo contrato haya expirado.");
        System.out.print("Ingrese la fecha de evaluacion actual (YYYY-MM-DD): ");
        
        LocalDate fechaActual = leerFechaSegura();
        if (fechaActual == null) return;

        // Llamada a la lógica de negocio en el controlador
        gestor.evaluarCierreContratosAlquiler(fechaActual);
        
        System.out.println("EXITO: La evaluacion de arrendamientos ha concluido.");
    }

    private void auxiliarListarContratos() {
        System.out.println("\n--- HISTORIAL DE CONTRATOS Y TRANSACCIONES ---");
        List<Contrato> transacciones = gestor.getListaContratos();
        
        if (transacciones.isEmpty()) {
            System.out.println("No existen contratos registrados en el historial financiero.");
            return;
        }

        for (Contrato c : transacciones) {
            // El polimorfismo dinámico invoca el toString() de Venta o Alquiler automáticamente
            System.out.println(c.toString());
        }
    }

    // Método de soporte interno para evitar duplicar código de parseo de fechas y proteger el menú
    private LocalDate leerFechaSegura() {
        String fechaStr = scanner.nextLine();
        try {
            return LocalDate.parse(fechaStr);
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: Formato de fecha incorrecto. Debe utilizar exactamente el formato YYYY-MM-DD.");
            return null;
        }
    }
}