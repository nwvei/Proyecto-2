/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;

import Dynamic.KeyDynamicList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author UTN
 */
public class clientesList implements KeyDynamicList<Cliente,String>{
    private HashMap<String,Cliente> clientes;
 
    public clientesList() {
        this.clientes = new HashMap();
    }
 
    @Override
    public Cliente get(String identificacion) {
        if(!clientes.containsKey(identificacion)){
            return null;
        }else{
        return clientes.get(identificacion);
        }
    }
 
    @Override
    public boolean remove(String identificacion) {
        return clientes.remove(identificacion)!=null;
    }
 
    @Override
    public boolean add(Cliente item) {
        if(clientes.containsKey(item.getID())) return false;
        return clientes.put(item.getID(), item)==null;
    }
 
    @Override
    public Iterator getAll() {
        if (clientes.isEmpty()) return null;
        return clientes.values().iterator();
    }
 
    @Override
    public int size() {
        return clientes.size();
    }
 
    @Override
    public boolean isEmpty() {
        return clientes.isEmpty();
    }
}
