package gestioninmobiliaria.contratos;

import gestioninmobiliaria.catalogos.*;
import gestioninmobiliaria.catalogos.Agente;
import gestioninmobiliaria.catalogos.Cliente;
import gestioninmobiliaria.catalogos.Propiedad;
import java.time.LocalDate;

public class ContratoVenta extends Contrato {

    public ContratoVenta(String idContrato, LocalDate fechaRegistro, Cliente cliente, Agente agente, Propiedad propiedad) {
        super(idContrato, fechaRegistro, cliente, agente, propiedad);
    }

    // Cumplimiento del contrato de la interfaz IComisionable
    @Override
    public double calcularComision() {
        // Regla de negocio aprobada: 3% del valor comercial de la propiedad
        return getPropiedad().getPrecio() * 0.03;
    }

    @Override
    public void procesarCierre() {
        // Altera de manera definitiva el estado del inmueble a VENDIDA
        getPropiedad().setEstado(EstadoPropiedad.VENDIDA);
        // Transfiere e inyecta la comisión calculada directamente a la billetera del agente
        getAgente().acumularComision(calcularComision());
    }

    @Override
    public String toString() {
        return "Contrato Venta - ID: " + getIdContrato() +
               " | Fecha Firma: " + getFechaRegistro() +
               " | Cliente: " + getCliente().getNombre() +
               " | Agente Asesor: " + getAgente().getNombre() +
               " | Inmueble ID: " + getPropiedad().getId() +
               " | Valor Inmueble: $" + String.format("%.2f", getPropiedad().getPrecio()) +
               " | Comisión de Agencia (3%%): $" + String.format("%.2f", calcularComision());
    }
}
