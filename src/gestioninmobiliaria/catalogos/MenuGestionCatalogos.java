package gestioninmobiliaria.catalogos;

import java.util.List;
import java.util.Scanner;

/**
 * Encargada exclusiva de la interacción con el usuario por consola para el módulo de catálogos.
 */
public class MenuGestionCatalogos {
    private final CatalogoInmobiliario catalogo;
    private final Scanner teclado;

    public MenuGestionCatalogos(CatalogoInmobiliario catalogo) {
        this.catalogo = catalogo;
        this.teclado = new Scanner(System.in);
    }

    /**
     * Inicia el ciclo principal del menú del módulo.
     */
    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("  SISTEMA NOVA CASA: GESTION DE CATALOGOS");
            System.out.println("========================================");
            System.out.println("1. Registrar una Casa");
            System.out.println("2. Registrar un Departamento");
            System.out.println("3. Registrar un Terreno");
            System.out.println("4. Registrar un Agente Inmobiliario");
            System.out.println("5. Registrar un Cliente");
            System.out.println("6. Listar todas las Propiedades");
            System.out.println("7. Filtrar Propiedades por Tipo");
            System.out.println("8. Filtrar Propiedades por Precio Maximo");
            System.out.println("9. Filtrar Propiedades por Ubicacion");
            System.out.println("10. Listar Personal y Clientes");
            System.out.println("11. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            
            try {
                opcion = Integer.parseInt(teclado.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un numero valido.");
            }
        } while (opcion != 11);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                ejecutarRegistroCasa();
                break;
            case 2:
                ejecutarRegistroDepartamento();
                break;
            case 3:
                ejecutarRegistroTerreno();
                break;
            case 4:
                ejecutarRegistroAgente();
                break;
            case 5:
                ejecutarRegistroCliente();
                break;
            case 6:
                ejecutarListadoPropiedades();
                break;
            case 7:
                ejecutarFiltradoPorTipo();
                break;
            case 8:
                ejecutarFiltradoPorPrecio();
                break;
            case 9:
                ejecutarFiltradoPorUbicacion();
                break;
            case 10:
                ejecutarListadoPersonas();
                break;
            case 11:
                System.out.println("Retornando al flujo del menu superior...");
                break;
            default:
                System.out.println("Opción no valida. Intente nuevamente.");
        }
    }

    // Métodos Auxiliares del Menú

    private void ejecutarRegistroCasa() {
        System.out.println("\n--- REGISTRAR NUEVA CASA ---");
        System.out.print("Ingrese ID unico: ");
        String id = teclado.nextLine();
        System.out.print("Ingrese Ubicacion: ");
        String ubicacion = teclado.nextLine();
        System.out.print("Ingrese Precio base: ");
        double precio = Double.parseDouble(teclado.nextLine());

        Casa nuevaCasa = new Casa(id, ubicacion, precio);
        catalogo.registrarPropiedad(nuevaCasa);
        System.out.println("Operacion procesada en el controlador.");
    }

    private void ejecutarRegistroDepartamento() {
        System.out.println("\n--- REGISTRAR NUEVO DEPARTAMENTO ---");
        System.out.print("Ingrese ID unico: ");
        String id = teclado.nextLine();
        System.out.print("Ingrese Ubicacion: ");
        String ubicacion = teclado.nextLine();
        System.out.print("Ingrese Precio base: ");
        double precio = Double.parseDouble(teclado.nextLine());

        Departamento nuevoDepto = new Departamento(id, ubicacion, precio);
        catalogo.registrarPropiedad(nuevoDepto);
        System.out.println("Operacion procesada en el controlador.");
    }

    private void ejecutarRegistroTerreno() {
        System.out.println("\n--- REGISTRAR NUEVO TERRENO ---");
        System.out.print("Ingrese ID unico: ");
        String id = teclado.nextLine();
        System.out.print("Ingrese Ubicacion: ");
        String ubicacion = teclado.nextLine();
        System.out.print("Ingrese Precio base: ");
        double precio = Double.parseDouble(teclado.nextLine());

        Terreno nuevoTerreno = new Terreno(id, ubicacion, precio);
        catalogo.registrarPropiedad(nuevoTerreno);
        System.out.println("Operacion procesada en el controlador.");
    }

    private void ejecutarRegistroAgente() {
        System.out.println("\n--- REGISTRAR ASESOR INMOBILIARIO ---");
        System.out.print("Ingrese ID unico del Agente: ");
        String id = teclado.nextLine();
        System.out.print("Ingrese Nombre completo: ");
        String nombre = teclado.nextLine();

        Agente nuevoAgente = new Agente(id, nombre);
        catalogo.registrarAgente(nuevoAgente);
        System.out.println("Agente registrado.");
    }

    private void ejecutarRegistroCliente() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        System.out.print("Ingrese Identificacion (ID): ");
        String id = teclado.nextLine();
        System.out.print("Ingrese Nombre completo: ");
        String nombre = teclado.nextLine();

        Cliente nuevoCliente = new Cliente(id, nombre);
        catalogo.registrarCliente(nuevoCliente);
        System.out.println("Cliente registrado.");
    }

    private void ejecutarListadoPropiedades() {
        System.out.println("\n--- INVENTARIO GENERAL DE PROPIEDADES ---");
        List<Propiedad> propiedades = catalogo.getListaPropiedades();
        if (propiedades.isEmpty()) {
            System.out.println("No hay propiedades registradas en el catalogo actual.");
        } else {
            for (Propiedad p : propiedades) {
                System.out.println(p.toString());
            }
        }
    }

    private void ejecutarFiltradoPorTipo() {
        System.out.println("\n--- FILTRAR POR TIPO DE INMUEBLE ---");
        System.out.println("1. Casas");
        System.out.println("2. Departamentos");
        System.out.println("3. Terrenos");
        System.out.print("Seleccione el tipo a filtrar: ");
        int tipo = Integer.parseInt(teclado.nextLine());

        List<Propiedad> filtradas;
        if (tipo == 1) {
            filtradas = catalogo.filtrarPropiedades(Casa.class);
        } else if (tipo == 2) {
            filtradas = catalogo.filtrarPropiedades(Departamento.class);
        } else if (tipo == 3) {
            filtradas = catalogo.filtrarPropiedades(Terreno.class);
        } else {
            System.out.println("Seleccion invalida.");
            return;
        }

        mostrarResultadosFiltrados(filtradas);
    }

    private void ejecutarFiltradoPorPrecio() {
        System.out.println("\n--- FILTRAR POR PRECIO MAXIMO ---");
        System.out.print("Ingrese el presupuesto limite maximo: ");
        double maximo = Double.parseDouble(teclado.nextLine());

        List<Propiedad> filtradas = catalogo.filtrarPropiedades(maximo);
        mostrarResultadosFiltrados(filtradas);
    }

    private void ejecutarFiltradoPorUbicacion() {
        System.out.println("\n--- FILTRAR POR UBICACION ---");
        System.out.print("Texto o palabra clave a buscar en la ubicacion: ");
        String palabra = teclado.nextLine();

        List<Propiedad> filtradas = catalogo.filtrarPropiedades(palabra);
        mostrarResultadosFiltrados(filtradas);
    }

    private void ejecutarListadoPersonas() {
        System.out.println("\n--- LISTADO DE AGENTES ASESORES ---");
        for (Agente a : catalogo.getListaAgentes()) {
            System.out.println("ID: " + a.getId() + " | Nombre: " + a.getNombre() + " | Comisiones: $" + a.getComisionesAcumuladas());
        }
        System.out.println("\n--- LISTADO DE CLIENTES ---");
        for (Cliente c : catalogo.getListaClientes()) {
            System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre());
        }
    }

    private void mostrarResultadosFiltrados(List<Propiedad> listaFiltrada) {
        System.out.println("-> Resultados encontrados:");
        if (listaFiltrada.isEmpty()) {
            System.out.println("   Ninguna propiedad coincide con los criterios de busqueda.");
        } else {
            for (Propiedad p : listaFiltrada) {
                System.out.println("   " + p.toString());
            }
        }
    }
}
