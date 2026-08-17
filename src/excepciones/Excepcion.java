/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author UTN
 */
public class Excepcion extends Exception{
    public Excepcion(String message) {
        super(message);
    }

    public Excepcion(String message, Throwable cause) {
        super(message, cause);
    }
}
