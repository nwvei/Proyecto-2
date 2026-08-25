/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empresa;

import Contrato.Contrato;
import Contrato.EstadoContrato;
import clientes.Cliente;
import contratos.Listacontratos;
import empleados.Empleado;
import empleados.Puesto;
import espacios.Espacio;
import espacios.ListaEspacios;
import espacios.TipoEspacio;
import excepciones.CambioEstadoNoPermitidoException;
import excepciones.ClientenoRegistradoException;
import excepciones.EspacioDuplicadoException;
import excepciones.EspacioOcupadoException;
import excepciones.EspacionoDisponiblException;
import excepciones.ID_duplicadosExc;
import excepciones.ServicioNoDisponibleException;
import excepciones.StoreBoxException;
import excepciones.UtilDate;
import excepciones.cliente_con_contratosExc;
import excepciones.datos_faltantesExc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import servicios.ListaServicios;
import servicios.Servicio;
/*
 *
 * @author Adriel
 */
public class ControladorEmpresa {

   
    private final Map<String, Cliente> clientes;      
    private final List<Empleado> empleados;           
    private final ListaEspacios espacios;             
    private final ListaServicios servicios;           
    private final Listacontratos contratos;           

    public ControladorEmpresa() {
        this.clientes = new HashMap<>();
        this.empleados = new ArrayList<>();
        this.espacios = new ListaEspacios();
        this.servicios = new ListaServicios();
        this.contratos = new Listacontratos();
    }


    public void agregarCliente(Cliente cliente) throws ID_duplicadosExc, datos_faltantesExc {
        if (cliente == null) {
            throw new datos_faltantesExc("Los datos del cliente son obligatorios.");
        }
        if (clientes.containsKey(cliente.getID())) {
            throw new ID_duplicadosExc("Ya existe un cliente con la identificación " + cliente.getID() + ".");
        }
        clientes.put(cliente.getID(), cliente);
    }

    public void actualizarCliente(String identificacion, String nombre, String telefono, String correo)
            throws StoreBoxException {
        Cliente cliente = obtenerCliente(identificacion);
        if (nombre != null) {
            cliente.setNombreCompleto(nombre);
        }
        if (telefono != null) {
            cliente.setTelefono(telefono);
        }
        if (correo != null) {
            cliente.setCorreoElectronico(correo);
        }
    }

    public void eliminarCliente(String identificacion) throws StoreBoxException {
        obtenerCliente(identificacion);

        Iterator it = contratos.getAll();
        while (it.hasNext()) {
            Contrato c = (Contrato) it.next();
            boolean esDelCliente = c.getCliente() != null && c.getCliente().getID().equals(identificacion);
            boolean estaActivoOPendiente = c.getEstado() == EstadoContrato.PENDIENTE
                    || c.getEstado() == EstadoContrato.ACTIVO;
            if (esDelCliente && estaActivoOPendiente) {
                throw new cliente_con_contratosExc(
                        "No se puede eliminar: el cliente tiene contratos Pendientes o Activos.");
            }
        }
        clientes.remove(identificacion);
    }

    public List<Cliente> buscarCliente(String identificacion, String nombre) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c : clientes.values()) {
            if (identificacion != null && !identificacion.isBlank() && !c.getID().contains(identificacion)) {
                continue;
            }
            if (nombre != null && !nombre.isBlank()
                    && !c.getNombreCompleto().toLowerCase().contains(nombre.toLowerCase())) {
                continue;
            }
            resultado.add(c);
        }
        return resultado;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }

    private Cliente obtenerCliente(String identificacion) throws StoreBoxException {
        Cliente cliente = clientes.get(identificacion);
        if (cliente == null) {
            throw new ClientenoRegistradoException(
                    "No existe un cliente registrado con identificación " + identificacion + ".");
        }
        return cliente;
    }



    public void agregarEmpleado(Empleado empleado) throws ID_duplicadosExc {
        if (empleado == null) {
            throw new IllegalArgumentException("Los datos del empleado son obligatorios.");
        }
        if (buscarEmpleadoPorId(empleado.getID()) != null) {
            throw new ID_duplicadosExc("Ya existe un empleado con la identificación " + empleado.getID() + ".");
        }
        empleados.add(empleado);
    }

    public void actualizarEmpleado(String identificacion, String nombre, String telefono, Puesto puesto)
            throws StoreBoxException {
        Empleado empleado = obtenerEmpleado(identificacion);
        if (nombre != null) {
            empleado.setNombre(nombre);
        }
        if (telefono != null) {
            empleado.setTelefono(telefono);
        }
        if (puesto != null) {
            empleado.setPuesto(puesto); 
        }
    }

    public void eliminarEmpleado(String identificacion) throws StoreBoxException {
        Empleado empleado = obtenerEmpleado(identificacion);
        empleados.remove(empleado);
    }

    public List<Empleado> buscarEmpleado(String identificacion, String nombre, Puesto puesto) {
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : empleados) {
            if (identificacion != null && !identificacion.isBlank() && !e.getID().contains(identificacion)) {
                continue;
            }
            if (nombre != null && !nombre.isBlank()
                    && !e.getnombre().toLowerCase().contains(nombre.toLowerCase())) {
                continue;
            }
            if (puesto != null && e.getPuesto() != puesto) {
                continue;
            }
            resultado.add(e);
        }
        return resultado;
    }

    public List<Empleado> listarEmpleados() {
        return new ArrayList<>(empleados);
    }

    private Empleado obtenerEmpleado(String identificacion) throws StoreBoxException {
        Empleado empleado = buscarEmpleadoPorId(identificacion);
        if (empleado == null) {
            throw new StoreBoxException("No existe un empleado con identificación " + identificacion + ".");
        }
        return empleado;
    }

    private Empleado buscarEmpleadoPorId(String identificacion) {
        for (Empleado e : empleados) {
            if (e.getID().equals(identificacion)) {
                return e;
            }
        }
        return null;
    }



    public void agregarEspacio(Espacio espacio) throws EspacioDuplicadoException {
        if (espacio == null || !espacios.add(espacio)) {
            String numero = espacio != null ? String.valueOf(espacio.getNumero()) : "N/A";
            throw new EspacioDuplicadoException("Ya existe un espacio con el número " + numero + ".");
        }
    }

    public void actualizarEspacio(int numero, TipoEspacio tipo, Double precio) throws StoreBoxException {
        Espacio espacio = obtenerEspacio(numero);
        if (tipo != null) {
            espacio.setTipo(tipo);
        }
        if (precio != null) {
            espacio.setPrecio(precio);
        }
    }

    public void eliminarEspacio(int numero) throws StoreBoxException {
        Espacio espacio = obtenerEspacio(numero);
        if (!espacio.isDisponible()) {
            throw new EspacioOcupadoException("No se puede eliminar el espacio " + numero + " porque está ocupado.");
        }
        espacios.remove(numero);
    }

    public List<Espacio> buscarEspacio(Integer numero, TipoEspacio tipo, Boolean disponible,
            Double precioMin, Double precioMax) {
        List<Espacio> resultado = new ArrayList<>();
        Iterator it = espacios.getAll();
        while (it.hasNext()) {
            Espacio e = (Espacio) it.next();
            if (numero != null && e.getNumero() != numero) {
                continue;
            }
            if (tipo != null && e.getTipo() != tipo) {
                continue;
            }
            if (disponible != null && e.isDisponible() != disponible) {
                continue;
            }
            if (precioMin != null && e.getPrecio() < precioMin) {
                continue;
            }
            if (precioMax != null && e.getPrecio() > precioMax) {
                continue;
            }
            resultado.add(e);
        }
        return resultado;
    }

    public List<Espacio> listarEspacios() {
        return buscarEspacio(null, null, null, null, null);
    }

    private Espacio obtenerEspacio(int numero) throws StoreBoxException {
        Espacio espacio = espacios.get(numero);
        if (espacio == null) {
            throw new StoreBoxException("No existe un espacio con número " + numero + ".");
        }
        return espacio;
    }

   

    public Servicio agregarServicio(String nombre, String descripcion, double precio) {
        Servicio servicio = new Servicio(nombre, descripcion, precio);
        servicios.add(servicio);
        return servicio;
    }

    public void actualizarServicio(int codigo, String descripcion, Double precio) throws StoreBoxException {
        Servicio servicio = obtenerServicio(codigo);
        if (descripcion != null) {
            servicio.setDescripcion(descripcion);
        }
        if (precio != null) {
            servicio.setPrecio(precio);
        }
    }

 
    public void eliminarServicio(int codigo) throws StoreBoxException {
        Servicio servicio = obtenerServicio(codigo);

        Iterator it = contratos.getAll();
        while (it.hasNext()) {
            Contrato c = (Contrato) it.next();
            boolean enUso = c.getEstado() == EstadoContrato.ACTIVO || c.getEstado() == EstadoContrato.PENDIENTE;
            if (enUso && c.getServiciosAdicionales().contains(servicio)) {
                throw new ServicioNoDisponibleException(
                        "No se puede eliminar: el servicio está incluido en un contrato Activo o Pendiente.");
            }
        }
        servicios.remove(codigo);
    }

    public List<Servicio> buscarServicio(Integer codigo, String nombre) {
        List<Servicio> resultado = new ArrayList<>();
        Iterator it = servicios.getAll();
        while (it.hasNext()) {
            Servicio s = (Servicio) it.next();
            if (codigo != null && s.getCodigo() != codigo) {
                continue;
            }
            if (nombre != null && !nombre.isBlank() && !s.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                continue;
            }
            resultado.add(s);
        }
        return resultado;
    }

    public List<Servicio> listarServicios() {
        return buscarServicio(null, null);
    }

    private Servicio obtenerServicio(int codigo) throws StoreBoxException {
        Servicio servicio = servicios.get(codigo);
        if (servicio == null) {
            throw new StoreBoxException("No existe un servicio con código " + codigo + ".");
        }
        return servicio;
    }

  
    public Contrato crearContrato(String identificacionCliente, TipoEspacio tipoEspacio,
            LocalDate fechaInicio, LocalDate fechaFin) throws StoreBoxException {

        Cliente cliente = clientes.get(identificacionCliente);
        if (cliente == null) {
            throw new ClientenoRegistradoException(
                    "No existe un cliente registrado con identificación " + identificacionCliente + ".");
        }

        List<Espacio> disponibles = buscarEspaciosDisponibles(tipoEspacio, fechaInicio, fechaFin);
        if (disponibles.isEmpty()) {
            throw new EspacionoDisponiblException(
                    "No hay espacios de tipo " + tipoEspacio + " disponibles para ese período.");
        }

        Espacio espacioAsignado = disponibles.get(0);
        Contrato contrato = new Contrato(cliente, espacioAsignado, fechaInicio, fechaFin);
        contratos.add(contrato);
        return contrato;
    }

    public List<Espacio> buscarEspaciosDisponibles(TipoEspacio tipoEspacio, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Espacio> resultado = new ArrayList<>();
        for (Espacio espacio : buscarEspacio(null, tipoEspacio, null, null, null)) {
            if (espacioLibreEnPeriodo(espacio, fechaInicio, fechaFin)) {
                resultado.add(espacio);
            }
        }
        return resultado;
    }

    private boolean espacioLibreEnPeriodo(Espacio espacio, LocalDate inicio, LocalDate fin) {
        Iterator it = contratos.getAll();
        while (it.hasNext()) {
            Contrato c = (Contrato) it.next();
            boolean mismoEspacio = c.getEspacio() == espacio;
            boolean ocupaAgenda = c.getEstado() == EstadoContrato.PENDIENTE || c.getEstado() == EstadoContrato.ACTIVO;
            if (mismoEspacio && ocupaAgenda && UtilDate.seSolapan(c.getFechaInicio(), c.getFechaFin(), inicio, fin)) {
                return false;
            }
        }
        return true;
    }

    public void agregarServicioAContrato(int numeroContrato, int codigoServicio) throws StoreBoxException {
        Contrato contrato = buscarContratoPorNumero(numeroContrato);
        Servicio servicio = obtenerServicio(codigoServicio);
        contrato.agregarServicio(servicio);
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
        contrato.getEspacio().liberar();
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
                        || c.getEspacio().getNumero() != numeroEspacio)) {
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

    public List<Contrato> listarContratos() {
        return buscarContratos(null, null, null, null, null);
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