/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dynamic;

import java.util.Iterator;

/**
 *
 * @author UTN
 * @param <T>
 */
public interface DynamicList <T>{
    public boolean add(T item);
    public Iterator getAll();
    public int size();
    public boolean isEmpty();
}
