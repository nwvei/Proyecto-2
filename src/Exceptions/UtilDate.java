/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

import java.time.LocalDate;
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
}
