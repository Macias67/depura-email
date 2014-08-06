/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlador.RegistraOrigen;
import vistas.Principal;
import helper.StringValidation;
import modelo.Origen;
/**
 *
 * @author Macias
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Principal.main(args);
        
        String nuevo_origen=StringValidation.validaTexto("outlook");
        System.out.println("origen nuevo es "+nuevo_origen);
        
        try {
            Origen org = new Origen(nuevo_origen);
            RegistraOrigen checar = new RegistraOrigen(org);
            
            System.out.println("resultado main :"+checar.checarOrigen(org));
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        
    }

}
