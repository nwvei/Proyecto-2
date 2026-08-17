/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package empleados;

/**
 *
 * @author UTN
 */
public enum Puesto {
    ADMINISTRADOR(950_000),
    RECEPCIONISTA(700_000),
    ENCARGADO_BODEGA(650_000),
    MANTENIMIENTO(600_000),
    OPERARIO_CARGA(575_000);

    private final double salario;

    Puesto(double salario) {
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }
}
