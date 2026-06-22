package gestioninmobiliaria.catalogos;

/**
 * Representa al cliente comprador o arrendatario, heredando su identidad de Persona.
 */
public class Cliente extends Persona {

    public Cliente(String id, String nombre) {
        super(id, nombre); // Invoca al constructor de Persona
    }
    
    @Override
    public String toString() {
        return "Cliente - ID: " + getId() + " | Nombre: " + getNombre();
    }
}