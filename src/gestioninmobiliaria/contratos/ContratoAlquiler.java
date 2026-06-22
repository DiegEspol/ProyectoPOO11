package gestioninmobiliaria.contratos;

import gestioninmobiliaria.catalogos.*;
import gestioninmobiliaria.catalogos.Agente;
import gestioninmobiliaria.catalogos.Cliente;
import gestioninmobiliaria.catalogos.Propiedad;
import java.time.LocalDate;

public class ContratoAlquiler extends Contrato {
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final int mesesDuracion;
    private EstadoContrato estado;

    public ContratoAlquiler(String idContrato, LocalDate fechaRegistro, Cliente cliente, Agente agente, Propiedad propiedad, LocalDate fechaInicio, int mesesDuracion) {
        super(idContrato, fechaRegistro, cliente, agente, propiedad);
        this.fechaInicio = fechaInicio;
        this.mesesDuracion = mesesDuracion;
        // Encapsulamiento defensivo: la fecha final se calcula matemáticamente para evitar inconsistencias de entrada
        this.fechaFin = fechaInicio.plusMonths(mesesDuracion);
        this.estado = EstadoContrato.ACTIVO;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getMesesDuracion() {
        return mesesDuracion;
    }

    public EstadoContrato getEstado() {
        return estado;
    }

    // Cumplimiento del contrato de la interfaz IComisionable
    @Override
    public double calcularComision() {
        // Regla de negocio aprobada: 10% sobre el monto total del periodo pactado (precio mensual * meses)
        return (getPropiedad().getPrecio() * mesesDuracion) * 0.10;
    }

    @Override
    public void procesarCierre() {
        // Altera el estado del inmueble en el catálogo a ALQUILADA
        getPropiedad().setEstado(EstadoPropiedad.ALQUILADA);
        // Transfiere e inyecta la comisión calculada a la billetera del agente
        getAgente().acumularComision(calcularComision());
    }

    public void finalizarContrato() {
        this.estado = EstadoContrato.FINALIZADO;
        // Regla de negocio: al concluir el arrendamiento, el inmueble se libera automáticamente regresando a DISPONIBLE
        getPropiedad().setEstado(EstadoPropiedad.DISPONIBLE);
    }

    @Override
    public String toString() {
        return "Contrato Alquiler - ID: " + getIdContrato() +
               " | Estado Acuerdo: " + estado +
               " | Periodo Vigencia: " + fechaInicio + " al " + fechaFin + " (" + mesesDuracion + " meses)" +
               " | Cliente: " + getCliente().getNombre() +
               " | Agente Asesor: " + getAgente().getNombre() +
               " | Inmueble ID: " + getPropiedad().getId() +
               " | Renta Mensual: $" + String.format("%.2f", getPropiedad().getPrecio()) +
               " | Comision Total (10%%): $" + String.format("%.2f", calcularComision());
    }
}