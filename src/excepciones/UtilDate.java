/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Aaron
 */
public class UtilDate {
    public static String toString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static LocalDate toLocalDate(String date){
        return LocalDate.parse(date,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static boolean esFechaFutura(LocalDate fecha) {
        return fecha != null && fecha.isAfter(LocalDate.now());
    }

    public static boolean esRangoValido(LocalDate inicio, LocalDate fin) {
        return inicio != null && fin != null && fin.isAfter(inicio);
    }

    public static int calcularDias(LocalDate inicio, LocalDate fin) {
    int dias = 0;
    LocalDate fecha = inicio;

    while (fecha.isBefore(fin)) {
        dias++;
        fecha = fecha.plusDays(1);
    }

    return dias;
}

    public static int calcularPeriodos(long dias) {
        int periodos = (int) (dias / 30);
        if (dias % 30 != 0) {
            periodos++;
        }
        return periodos;
    }
    
    public static int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    
    public static boolean seSolapan(LocalDate inicioA, LocalDate finA, LocalDate inicioB, LocalDate finB) {
        return !inicioA.isAfter(finB) && !inicioB.isAfter(finA);
    }
}
