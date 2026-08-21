/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Contrato;

/**
 *
 * @author Aaron
 * @param <T>
 */
public interface Views<T> {
    public void clear();
    public void showData(T data);
    public void showError(String error);
    public void showMessage(String message);
    
}
