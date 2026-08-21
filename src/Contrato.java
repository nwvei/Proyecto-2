
import Contrato.EstadoContrato;
import Exceptions.FechaFinAnteriorException;
import Exceptions.UtilDate;
import clientes.Cliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aaron
 */
public class Contrato {
    private static int contadorContratos = 0;
    private static final double IVA = 0.13;
    private final int numeroContrato;
    private Cliente cliente;
    private Espacio espacio;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private EstadoContrato estado;
    private List<Servicio> serviciosAdicionales;
    
    private double subtotal;
    private double impuesto;
    private double total;

    public Contrato(Cliente cliente, Espacio espacio, LocalDate fechaInicio, LocalDate fechaFin)
        throws FechaFinAnteriorException {

    if (!UtilDate.esRangoValido(fechaInicio, fechaFin)) {
        throw new FechaFinAnteriorException(
                "La fecha de finalización debe ser posterior a la fecha de inicio.");
    }
    
    this.numeroContrato = ++contadorContratos;
    this.cliente = cliente;
    this.espacio = espacio;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.estado = EstadoContrato.PENDIENTE;
    this.serviciosAdicionales = new ArrayList<>();

    calcularCosto();
}

   public int getNumeroContrato() {
        return numeroContrato;
    }
 
    public Cliente getCliente() {
        return cliente;
    }
 
    public Espacio getEspacio() {
        return espacio;
    }
 
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
        calcularCosto();
    }
 
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
 
    public LocalDate getFechaFin() {
        return fechaFin;
    }
 
    public EstadoContrato getEstado() {
        return estado;
    }
    
     public void setEstado(EstadoContrato estado) {
        this.estado = estado;
    }
 
    public List<Servicio> getServiciosAdicionales() {
        return serviciosAdicionales;
    }
 
    public double getSubtotal() {
        return subtotal;
    }
 
    public double getImpuesto() {
        return impuesto;
    }
 
    public double getTotal() {
        return total;
    }

    private void calcularCosto() {
       int periodos = getCantidadPeriodos();
 
        double costoEspacio = espacio.getPrecio() * periodos;
        double costoServicios = 0;
        for (int i = 0; i < serviciosAdicionales.size(); i++) {
    costoServicios += serviciosAdicionales.get(i).getPrecio();
}
 
        this.total = costoEspacio + costoServicios;
        this.subtotal = total / (1 + IVA);
        this.impuesto = total - subtotal;
}
    
    public int getCantidadDias() {
    return UtilDate.calcularDias(fechaInicio, fechaFin);
}
    public int getCantidadPeriodos() {
        return UtilDate.calcularPeriodos(getCantidadDias());
    }
     @Override
    public String toString() {
        return "Contrato #" + numeroContrato + " [" + estado + "] "
                + "Cliente: " + (cliente != null ? cliente.getNombreCompleto() : "N/A")
                + " - Total: " + total;
    }
}
