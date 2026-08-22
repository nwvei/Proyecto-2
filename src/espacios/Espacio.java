/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espacios;

/**
 *
 * @author HP
 */
public class Espacio {
    private final int numero;
    private TipoEspacio tipo;
    private double precio;
    private EstadoEspacio estado;

    public Espacio(int numero, TipoEspacio tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de espacio es obligatorio.");
        }
        this.numero = numero;
        this.tipo = tipo;
        this.precio = tipo.getPrecio();
        this.estado = EstadoEspacio.DISPONIBLE;
    }

    public int getNumero() {
        return numero;
    }

    public TipoEspacio getTipo() {
        return tipo;
    }

    public int getTamano() {
        return tipo.getTamano();
    }

    public double getPrecio() {
        return precio;
    }

    public EstadoEspacio getEstado() {
        return estado;
    }

    public boolean isDisponible() {
        return estado == EstadoEspacio.DISPONIBLE;
    }
    public void setTipo(TipoEspacio tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de espacio es obligatorio.");
        }
        this.tipo = tipo;
        this.precio = tipo.getPrecio();
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero.");
        }
        this.precio = precio;
    }
    private void cambiarEstado(EstadoEspacio nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void ocupar() {
        cambiarEstado(EstadoEspacio.OCUPADO);
    }

    public void liberar() {
        cambiarEstado(EstadoEspacio.DISPONIBLE);
    }

    @Override
    public String toString() {
        return "Espacio #" + numero + " [" + tipo + ", " + getTamano() + "m2] - "
                + estado + " - " + precio;
    }
}