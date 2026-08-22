/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Dynamic.DynamicList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author HP
 */
public class ListaServicios implements DynamicList<Servicio> {

    private final List<Servicio> servicios;

    public ListaServicios() {
        this.servicios = new ArrayList<>();
    }

    @Override
    public boolean add(Servicio item) {
        if (item == null) {
            return false;
        }
        return servicios.add(item);
    }

    public Servicio get(int codigo) {
        for (Servicio s : servicios) {
            if (s.getCodigo() == codigo) {
                return s;
            }
        }
        return null;
    }

    public boolean remove(int codigo) {
        return servicios.removeIf(s -> s.getCodigo() == codigo);
    }

    @Override
    public Iterator getAll() {
        return servicios.iterator();
    }

    @Override
    public int size() {
        return servicios.size();
    }

    @Override
    public boolean isEmpty() {
        return servicios.isEmpty();
    }
}
