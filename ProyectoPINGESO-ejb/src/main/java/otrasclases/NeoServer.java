/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otrasclases;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alonso
 */
public class NeoServer {
    public int raiz() throws Exception {
        int raiz = 0;
        Class.forName("org.neo4j.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
        try(Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("MATCH (n) WHERE NOT (n)<-[:FATHER]-() RETURN n.accession");
            rs.next();
            raiz = Integer.parseInt(rs.getString("n.accession"));
        }
        return raiz;
    }
    
    public List<ParTerminos> ancestrosComunesMinimos(List<ParTerminos> lista) throws Exception{
        int raiz = this.raiz(), i, largoLista = lista.size(), term1, term2;
        String query;

        Class.forName("org.neo4j.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
        
        try(Statement stmt = con.createStatement()) {
            ResultSet rs;
            i=0;
            while (i < largoLista) {
                // Se obtienen los términos
                term1 = lista.get(i).getTermino1();
                term2 = lista.get(i).getTermino2();
                
                // Si los dos términos son iguales, el ACM es cualquiera de los
                // términos
                if( term1 == term2) {
                    lista.get(i).setAncestroComunMinimo(term1);
                }
                
                // Si uno de los términos es la raiz, ACM = raiz
                else if (term1 == raiz || term2 == raiz) {
                    lista.get(i).setAncestroComunMinimo(raiz);
                }

                // En otro caso obtengo el ACM del arbol
                else {
                    query = "MATCH (a: Term {accession: " + term1 +"}),(b: Term {accession: " + term2 +"}),p=a<-[r:FATHER*..]-c-[r:FATHER*..]->b RETURN c.accession LIMIT 1";
                    rs = stmt.executeQuery(query);
                    rs.next();
                    lista.get(i).setAncestroComunMinimo(Integer.parseInt(rs.getString("c.accession")));
//                    while(rs.next()) {
//                        System.out.println(Integer.parseInt(rs.getString("raiz.accession")));
//                    }
                }
                i++;
            }
            
            
        }
        
        return lista;
    }
    

}
