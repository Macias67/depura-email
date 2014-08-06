/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Macias
 */
public enum NombreTablas {

    GRUPOS("grupos"),
    CORREOS("correos"),
    ORIGENES("origenes");

    private final String value;

    NombreTablas(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
