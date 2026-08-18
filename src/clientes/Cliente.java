/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;
import personas.Persona;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

/**
 *
 * @author UTN
 */
public class Cliente extends Persona{
    private static final Pattern PATRON_CORREO =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    private LocalDate fecha_nac;
    private String correo;

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public String getCorreo() {
        return correo;
    }
    public String getNombreCompleto() {
        return getnombre();
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        setNombre(nombreCompleto);
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
        this.fecha_nac = fechaNacimiento;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        if (correoElectronico == null || !PATRON_CORREO.matcher(correoElectronico).matches()) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }
        this.correo = correoElectronico;
    }
    
    public Cliente(String identificacion, String nombreCompleto, LocalDate fechaNacimiento, String telefono, String correo) {
        super(identificacion, nombreCompleto, telefono);
        setFechaNacimiento(fechaNacimiento);
        setCorreoElectronico(correo);
    }
    
    public int calcularEdad() {
        if (fecha_nac == null) {
            return 0;
        }
        return Period.between(fecha_nac, LocalDate.now()).getYears();
    }
    
    @Override
    public String mostrarInformacion() {
        return getNombreCompleto() + " (" + getID() + "), " + calcularEdad() + " años";
    }
    
}
