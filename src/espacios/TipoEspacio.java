/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package espacios;

/**
 *
 * @author HP
 */
public enum TipoEspacio {
    PEQUENO(5, 25_000),
    MEDIANO(10, 45_000),
    GRANDE(20, 70_000);

    private final int tamano;
    private final double precio;

    TipoEspacio(int tamano, double precio) {
        this.tamano = tamano;
        this.precio = precio;
    }

    public int getTamano() {
        return tamano;
    }

    public double getPrecio() {
        return precio;
    }
}
