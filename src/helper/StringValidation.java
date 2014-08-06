/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

/**
 *
 * @author Macias
 */
public class StringValidation {
    
    public static String validaTexto(String texto) {
        if (!texto.isEmpty()) {
            texto = texto.trim().toUpperCase();
            return texto;
        } else {
            return null;
        }
    }
    
}
