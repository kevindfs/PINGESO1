/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otrasclases;

/**
 *
 * @author alonso
 */
public class Maper {
//    private final String palabra;
    
//    public Maper(String _palabra) {
//        palabra = _palabra;
//    }
    
    public static String getString(String _palabra) {
        String fila = _palabra, dato;
        int largoFila = fila.length(), j;
        
        dato = "";
        j=largoFila-2;
        while(j > 0) {
            if(fila.charAt(j) == ' ') break;
            dato = fila.charAt(j) + dato;
            j--;
        }
        return dato;
    }
    
    public static int getInt(String _palabra) {

        return Integer.parseInt(Maper.getString(_palabra));
        
    }
}