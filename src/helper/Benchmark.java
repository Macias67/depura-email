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
public class Benchmark {
    
    private static final int BHORA = 60*60*1000;
    private static final int BMINUTO = 60*1000;
    private static final int BSEGUNDO = 1000;
    
    public static String calculaTiempo(long totaltiempo) {
        int hora = (int) (totaltiempo/BHORA);
        int restaHora = (int) (totaltiempo%BHORA);
        int minuto = restaHora/BMINUTO;
        int restaMinuto = minuto%BMINUTO;
        int segundo = restaMinuto/BSEGUNDO;
        
        String shora = (hora<10) ? "0"+hora : hora+"";
        String sminuto = (minuto<10) ? "0"+minuto : minuto+"";
        String ssegundo = (segundo<10) ? "0"+segundo : segundo+"";
        
        return shora+":"+sminuto+":"+ssegundo;
    }
}
