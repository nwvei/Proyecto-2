/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empresa;
import Contrato.Contrato;
import Contrato.EstadoContrato;
import contratos.Listacontratos;
import contratos.Listacontratos.ListaContratos;
import excepciones.CambioEstadoNoPermitidoException;
import excepciones.StoreBoxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Adriel
 */
public class ControladorEmpresa {
     private final Listacontratos contratos;
 
    public ControladorEmpresa() {
        this.contratos = new Listacontratos();
    }
    public void registrarContrato(Contrato contrato) {
        contratos.add(contrato);
    }
 
    public void activarContrato(int numeroContrato) throws StoreBoxException {
        Contrato contrato = buscarContratoPorNumero(numeroContrato);
 
        if (contrato.getEstado() != EstadoContrato.PENDIENTE) {
            throw new CambioEstadoNoPermitidoException(
                    "Solo se puede activar un contrato en estado Pendiente. "
                    + "Estado actual: " + contrato.getEstado());
        }
 
        contrato.setEstado(EstadoContrato.ACTIVO);
        contrato.getEspacio().ocupar();
    }
 
    public void finalizarContrato(int numeroContrato) throws StoreBoxException {
        Contrato contrato = buscarContratoPorNumero(numeroContrato);
 
        if (contrato.getEstado() != EstadoContrato.ACTIVO) {
            throw new CambioEstadoNoPermitidoException(
                    "Solo se puede finalizar un contrato en estado Activo. "
                    + "Estado actual: " + contrato.getEstado());
        }
 
        contrato.setEstado(EstadoContrato.FINALIZADO);
        contrato.getEspacio().liberar(); 
    }
 
   
    public void cancelarContrato(int numeroContrato) throws StoreBoxException {
        Contrato contrato = buscarContratoPorNumero(numeroContrato);
 
        if (contrato.getEstado() != EstadoContrato.PENDIENTE) {
            throw new CambioEstadoNoPermitidoException(
                    "Solo se puede cancelar un contrato en estado Pendiente. "
                    + "Estado actual: " + contrato.getEstado());
        }
 
        contrato.setEstado(EstadoContrato.CANCELADO);
        contrato.getEspacio().liberar(); // 
    }
 
   
    public List<Contrato> buscarContratos(Integer numeroContrato,
            String identificacionCliente, Integer numeroEspacio,
            LocalDate fecha, EstadoContrato estado) {
 
        List<Contrato> resultado = new ArrayList<>();
        Iterator it = contratos.getAll();
 
        while (it.hasNext()) {
            Contrato c = (Contrato) it.next();
 
            if (numeroContrato != null && c.getNumeroContrato() != numeroContrato) {
                continue;
            }
            if (identificacionCliente != null && !identificacionCliente.isBlank()
                    && (c.getCliente() == null
                        || !c.getCliente().getID().equals(identificacionCliente))) {
                continue;
            }
            if (numeroEspacio != null
                    && (c.getEspacio() == null
                        || c.getEspacio().getNumero() != numeroEspacio)) { // (Espacio)
                continue;
            }
            if (fecha != null
                    && (fecha.isBefore(c.getFechaInicio()) || fecha.isAfter(c.getFechaFin()))) {
                continue;
            }
            if (estado != null && c.getEstado() != estado) {
                continue;
            }
 
            resultado.add(c);
        }
 
        return resultado;
    }
 
    public int cantidadContratos() {
        return contratos.size();
    }

    private Contrato buscarContratoPorNumero(int numeroContrato) throws StoreBoxException {
        Iterator it = contratos.getAll();
        while (it.hasNext()) {
            Contrato c = (Contrato) it.next();
            if (c.getNumeroContrato() == numeroContrato) {
                return c;
            }
        }
        throw new StoreBoxException("No existe un contrato con número " + numeroContrato + ".");
    }

}
 
