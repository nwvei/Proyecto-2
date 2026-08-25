/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empleados;

import Dynamic.KeyDynamicList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author UTN
 */
public class empleadosList implements KeyDynamicList<Empleado, String>{
    private ArrayList<Empleado> empleados;
 
    public empleadosList() {
        this.empleados = new ArrayList();
    }
 
    @Override
    public Empleado get(String identificacion) {
        for (Empleado emp : empleados) {
            if (emp.getID().equals(identificacion)) {
                return emp;
            }
        }
        return null;
    }
 
    @Override
    public boolean remove(String identificacion) {
        Empleado emp = get(identificacion);
        if (emp == null) {
            return false;
        }
        return empleados.remove(emp);
    }
 
    @Override
    public boolean add(Empleado item) {
        if (get(item.getID()) != null) {
            return false;
        }
        return empleados.add(item);
    }
 
    @Override
    public Iterator getAll() {
        if (empleados.isEmpty()) return null;
        return empleados.iterator();
    }
 
    @Override
    public int size() {
        return empleados.size();
    }
 
    @Override
    public boolean isEmpty() {
        return empleados.isEmpty();
    }
}
