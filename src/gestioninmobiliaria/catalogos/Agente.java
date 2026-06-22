package gestioninmobiliaria.catalogos;

/**
 * Representa al asesor inmobiliario, extendiendo a Persona con atributos financieros particulares.
 */
public class Agente extends Persona {
    private double comisionesAcumuladas;

    public Agente(String id, String nombre) {
        super(id, nombre); // Invoca al constructor de Persona
        this.comisionesAcumuladas = 0.0; // Estado inicial mandatorio
    }

    /**
     * Suma de forma directa el monto provisto al acumulado de su cartera.
     */
    public void acumularComision(double monto) {
        if (monto > 0) {
            this.comisionesAcumuladas += monto;
        }
    }

    public double getComisionesAcumuladas() {
        return comisionesAcumuladas;
    }

    public void setComisionesAcumuladas(double comisionesAcumuladas) {
        this.comisionesAcumuladas = comisionesAcumuladas;
    }
    
    @Override
    public String toString() {
        return "Agente - ID: " + getId() + " | Nombre: " + getNombre() + " | Comisiones: $" + comisionesAcumuladas;
    }
}