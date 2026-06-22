package Main;

// Importaciones del módulo 1: Gestión de Catálogos (Nueva ubicación del menú incluida)
import gestioninmobiliaria.catalogos.CatalogoInmobiliario;
import gestioninmobiliaria.catalogos.MenuGestionCatalogos; 

// Importaciones del módulo 2: Operaciones e Intermediación
import gestioninmobiliaria.operaciones.MenuOperacionesEIntermediacion;

// Importaciones del módulo 3: Contratos y Cierre Comercial
import gestioninmobiliaria.contratos.MenuContratoyCierreComercial;

// Importaciones del módulo 4: Reportes y Rendimiento
import gestioninmobiliaria.reportes.VisualizadorReportes;
import gestioninmobiliaria.reportes.MenuModuloReportesyRendimiento;

import java.util.Scanner;

/**
 * Orquestador principal del Sistema Inmobiliario NovaCasa.
 * Implementa el menú raíz por consola para la navegación entre los 4 módulos de forma segura.
 */
public class Main {

    public static void main(String[] args) {
        CatalogoInmobiliario catalogoMaestro = new CatalogoInmobiliario();
        catalogoMaestro.precargarDatosEjemplo();

        // Módulo 2: Operaciones (Maneja visitas y asignación de agentes)
        gestioninmobiliaria.operaciones.GestorOperaciones gestorOperaciones = 
                new gestioninmobiliaria.operaciones.GestorOperaciones(catalogoMaestro);

        // Módulo 3: Contratos y Cierre Comercial (Maneja ventas y arrendamientos)
        gestioninmobiliaria.contratos.GestorContratos gestorContratosCierre = 
                new gestioninmobiliaria.contratos.GestorContratos(catalogoMaestro);

        // Módulo 4: Reportes y Rendimiento
        gestioninmobiliaria.reportes.GestorReportes gestorReportes = 
                new gestioninmobiliaria.reportes.GestorReportes(catalogoMaestro, gestorContratosCierre);

        // -------------------------------------------------------------------------
        // 2. INICIALIZACIÓN ÚNICA DE LAS VISTAS (MENÚS INTERNOS)
        // -------------------------------------------------------------------------
        
        // Menú Módulo 1: Gestión de Catálogos
        // Solucionado el error de compilación al añadir el import correcto arriba
        MenuGestionCatalogos menuCatalogos = new MenuGestionCatalogos(catalogoMaestro);

        // Menú Módulo 2: Operaciones e Intermediación
        MenuOperacionesEIntermediacion menuOperaciones = new MenuOperacionesEIntermediacion(gestorOperaciones);

        // Menú Módulo 3: Contratos y Cierre Comercial
        MenuContratoyCierreComercial menuContratosCierre = new MenuContratoyCierreComercial(gestorContratosCierre);

        // Menú Módulo 4: Reportes y Rendimiento
        VisualizadorReportes visualizadorReportes = new VisualizadorReportes(gestorReportes);
        MenuModuloReportesyRendimiento menuReportes = new MenuModuloReportesyRendimiento(visualizadorReportes);

        // -------------------------------------------------------------------------
        // 3. FLUJO OPERATIVO DEL MENÚ PRINCIPAL (DO-WHILE / SWITCH / SCANNER)
        // -------------------------------------------------------------------------
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=======================================================");
            System.out.println("      SISTEMA INTEGRADO INMOBILIARIO NOVACASA          ");
            System.out.println("=======================================================");
            System.out.println("1. Modulo: Gestion de Catalogos");
            System.out.println("2. Modulo: Operaciones e Intermediacion");
            System.out.println("3. Modulo: Contratos y Cierre Comercial");
            System.out.println("4. Modulo: Reportes y Rendimiento");
            System.out.println("5. Salir del Sistema");
            System.out.println("-------------------------------------------------------");
            System.out.print("Seleccione el modulo al que desea ingresar: ");

            // Validación de control de excepciones del búfer para evitar bucles infinitos
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el salto de línea
            } else {
                opcion = 0; 
                teclado.nextLine(); // Limpiar entrada incorrecta del búfer
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n[Sistema] Abriendo Modulo de Gestion de Catalogos...");
                    menuCatalogos.iniciar(); 
                    System.out.println("\n[Sistema] Regresando al Menu Principal Unificado.");
                    break;

                case 2:
                    System.out.println("\n[Sistema] Abriendo Modulo de Operaciones e Intermediacion...");
                    menuOperaciones.iniciarMenu(); 
                    System.out.println("\n[Sistema] Regresando al Menu Principal Unificado.");
                    break;

                case 3:
                    System.out.println("\n[Sistema] Abriendo Modulo de Contratos y Cierre Comercial...");
                    menuContratosCierre.iniciarMenu(); 
                    System.out.println("\n[Sistema] Regresando al Menu Principal Unificado.");
                    break;

                case 4:
                    System.out.println("\n[Sistema] Abriendo Modulo de Reportes y Rendimiento...");
                    menuReportes.iniciarMenu(); 
                    System.out.println("\n[Sistema] Regresando al Menu Principal Unificado.");
                    break;

                case 5:
                    System.out.println("\n=======================================================");
                    System.out.println(" Cierre de sesion exitoso. Estado de memoria liberado. ");
                    System.out.println("=======================================================");
                    break;

                default:
                    System.out.println("\nERROR: Opcion invalida. Ingrese un numero entero del 1 al 5.");
                    break;
            }

        } while (opcion != 5);

        teclado.close();
    }
}