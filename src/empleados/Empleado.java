/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empleados;
import personas.Persona;
/**
 *
 * @author UTN
 */
public class Empleado extends Persona{
    private Puesto puesto;
    private double salario;

    public Empleado(String identificacion, String nombre, String telefono, Puesto puesto) {
        super(identificacion, nombre, telefono);
        this.puesto = puesto;
    }
    
    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        if (puesto == null) {
            throw new IllegalArgumentException("El puesto es obligatorio.");
        }
        this.puesto = puesto;
        this.salario = puesto.getSalario();
    }
    
    public double getSalario() {
        return salario;
    }

    @Override
    public String mostrarInformacion() {
        return getnombre() + " (" + getID() + ") - " + puesto;
    }
}
