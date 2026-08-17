/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personas;

import java.util.regex.Pattern;

/**
 *
 * @author UTN
 */
public abstract class Persona {
    private static final Pattern PATRON_TELEFONO = Pattern.compile("^[0-9]+$");
    private String ID;
    private String nombre;
    private String telefono;

    public String getID() {
        return ID;
    }

    public String getnombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !PATRON_TELEFONO.matcher(telefono).matches()) {
            throw new IllegalArgumentException("El teléfono debe contener solamente números.");
        }
        this.telefono = telefono;
    }
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }


    public Persona(String ID, String Nombre_completo, String telefono) {
        if (ID == null) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }
        this.ID = ID;
        this.nombre = Nombre_completo;
        this.telefono = telefono;
    }
    
    public abstract String mostrarInformacion();

    @Override
    public String toString() {
        return mostrarInformacion();
    }
}
