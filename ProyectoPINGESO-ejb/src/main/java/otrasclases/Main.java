/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otrasclases;

import sessionbeans.Neo4J;

/**
 *
 * @author alonso
 */
public class Main {
    public static void main(String args[]) {
        // Se crea una instancia de base de datos en la carpeta mibase
        // OJO ESTA DIRECCION ES DE MI NOTEBOOK, TIENES QUE CAMBIARLA 
        Neo4J bd = new Neo4J("C:\\Users\\alonso\\Documents\\Neo4j\\mibase");

        // Se busca la distancia minima entre los nodos 8 y 0
        System.out.println("La distancia mínima entre los nodos 8 y 0 es: " + bd.distancia(8, 0));
        
        // Se busca la distancia mínima entre los nodos 0 y 8
        System.out.println("La distancia mínima entre los nodos 0 y 8 es: " + bd.distancia(0, 8));
        
        // Se busca la distancia mínima entre los nodos 3 y 1
        System.out.println("La distancia mínima entre los nodos 3 y 1 es: " + bd.distancia(3, 1));
        
        
    }
}
