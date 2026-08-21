/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contratos;
    import Contrato.Contrato;
import Dynamic.DynamicList;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author Adriel
 */
  
public class Listacontratos implements DynamicList<Contrato> {
 
    private final LinkedList<Contrato> contratos;
 
    public Listacontratos() {
        this.contratos = new LinkedList<>();
    }
 
    @Override
    public boolean add(Contrato item) {
        if (item == null) {
            return false;
        }
        return contratos.add(item);
    }
 
    @Override
    public Iterator getAll() {
        return contratos.iterator();
    }
 
    @Override
    public int size() {
        return contratos.size();
    }
 
    @Override
    public boolean isEmpty() {
        return contratos.isEmpty();
    }
}

