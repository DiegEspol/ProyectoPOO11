package gestioninmobiliaria.contratos;

/**
 * Interfaz que define el comportamiento para cualquier entidad o servicio
 * del sistema que sea susceptible de generar una comisión financiera.
 */
public interface IComisionable {
    double calcularComision();
}