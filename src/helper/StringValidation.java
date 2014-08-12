/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.regex.Pattern;

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

    public static boolean validaCorreo(String correo) {
        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern patron = Pattern.compile(email_pattern);
        return patron.matcher(correo).matches();
    }

}
