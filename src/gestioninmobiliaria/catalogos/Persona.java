package gestioninmobiliaria.catalogos;

/**
 * Clase abstracta que unifica los atributos y comportamientos comunes 
 * de todos los seres humanos registrados en el sistema NovaCasa.
 */
public abstract class Persona {
    private String id;
    private String nombre;

    public Persona(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters comunes
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}