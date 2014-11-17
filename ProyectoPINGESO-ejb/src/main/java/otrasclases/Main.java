/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otrasclases;

import java.util.ArrayList;
import java.util.List;
import sessionbeans.Neo4J;

/**
 *
 * @author alonso
 */
public class Main {
    public static void main(String args[]) {
        // Se crea una instancia de base de datos en la carpeta mibase
        // OJO ESTA DIRECCION ES DE MI NOTEBOOK, TIENES QUE CAMBIARLA 
        //Neo4J bd = new Neo4J("C:/Users/Italo/Desktop/PINGESO/godb");
        
        // Ejemplo de carga de la base de datos.
        // Se le entrega la ubicación del archivo como argunmento.
        //int accessionUno = 7, accessionDos = 8;
        //System.out.println(bd.ancestroComunMinimo(3,8150));
//        System.out.println(bd.consulta("MATCH (a: Term {accession: 8}),(a)<-[:FATHER]-(b) RETURN b.accession;").get(1));
    }
}
