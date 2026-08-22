/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espacios;

import Dynamic.KeyDynamicList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author HP
 */
public class ListaEspacios implements KeyDynamicList<Espacio, Integer> {

    private final TreeMap<Integer, Espacio> espacios;

    public ListaEspacios() {
        this.espacios = new TreeMap<>();
    }

    @Override
    public boolean add(Espacio item) {
        if (item == null || espacios.containsKey(item.getNumero())) {
            return false;
        }
        espacios.put(item.getNumero(), item);
        return true;
    }

    @Override
    public Espacio get(Integer numero) {
        return espacios.get(numero);
    }

    @Override
    public boolean remove(Integer numero) {
        return espacios.remove(numero) != null;
    }

    @Override
    public Iterator getAll() {
        return espacios.values().iterator();
    }

    @Override
    public int size() {
        return espacios.size();
    }

    @Override
    public boolean isEmpty() {
        return espacios.isEmpty();
    }
}
