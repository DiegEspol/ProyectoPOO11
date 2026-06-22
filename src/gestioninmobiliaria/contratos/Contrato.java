package gestioninmobiliaria.contratos;

import gestioninmobiliaria.catalogos.Agente;
import gestioninmobiliaria.catalogos.Cliente;
import gestioninmobiliaria.catalogos.Propiedad;
import java.time.LocalDate;

// La clase abstracta ahora implementa la interfaz IComisionable
public abstract class Contrato implements IComisionable {
    private final String idContrato;
    private final LocalDate fechaRegistro;
    private final Cliente cliente;
    private final Agente agente;
    private final Propiedad propiedad;

    public Contrato(String idContrato, LocalDate fechaRegistro, Cliente cliente, Agente agente, Propiedad propiedad) {
        this.idContrato = idContrato;
        this.fechaRegistro = fechaRegistro;
        this.cliente = cliente;
        this.agente = agente;
        this.propiedad = propiedad;
    }

    // Métodos de acceso exclusivos de lectura (Garantía de integridad financiera)
    public String getIdContrato() {
        return idContrato;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Agente getAgente() {
        return agente;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    // Método abstracto obligatorio para el cambio de estado (calcularComision ya viene de IComisionable)
    public abstract void procesarCierre();
}