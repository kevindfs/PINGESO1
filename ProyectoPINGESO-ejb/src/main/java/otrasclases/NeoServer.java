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

    private int raiz = -1;

    public int getRaiz() {
        return raiz;
    }

    public void setRaiz(int raiz) {
        this.raiz = raiz;
    }

    public int raiz() throws Exception {
        System.out.println("Raiz 1");
        if (raiz != -1) {
            return raiz;
        } else {
            System.out.println("Raiz 2");
            Class.forName("org.neo4j.jdbc.Driver");
            System.out.println("Clase");
            Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
            System.out.println("Conexion");
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("MATCH (n) WHERE NOT (n)<-[:FATHER]-() RETURN n.accession");
                rs.next();
                this.setRaiz(Integer.parseInt(rs.getString("n.accession")));
            }
            return raiz;
        }

    }

    public List<ParTerminos> ancestrosComunesMinimos(List<ParTerminos> lista) throws Exception {
        int root = this.raiz(), i, largoLista = lista.size(), term1, term2;
        String query;

        Class.forName("org.neo4j.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");

        try (Statement stmt = con.createStatement()) {
            ResultSet rs;
            i = 0;
            while (i < largoLista) {
                // Se obtienen los términos
                term1 = lista.get(i).getTermino1();
                term2 = lista.get(i).getTermino2();

                // Si los dos términos son iguales, el ACM es cualquiera de los
                // términos
                if (term1 == term2) {
                    lista.get(i).setAncestroComunMinimo(term1);
                } // Si uno de los términos es la raiz, ACM = raiz
                else if (term1 == root || term2 == root) {
                    lista.get(i).setAncestroComunMinimo(root);
                } // En otro caso obtengo el ACM del arbol
                else {
                    query = "MATCH (a: Term {accession: " + term1 + "}),(b: Term {accession: " + term2 + "}),p=a<-[r:FATHER*..]-c-[r:FATHER*..]->b RETURN c.accession LIMIT 1";
                    rs = stmt.executeQuery(query);
                    rs.next();
                    lista.get(i).setAncestroComunMinimo(Integer.parseInt(rs.getString("c.accession")));
                }
                i++;
            }
        }
        return lista;
    }

    public List<ParTerminos> distancias(List<ParTerminos> lista) throws Exception {
        int raiz = this.raiz(), i, largoLista = lista.size(), term1, term2, acm, d1, d2, d3;
        String query;

        Class.forName("org.neo4j.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");

        try (Statement stmt = con.createStatement()) {
            ResultSet rs;
            i = 0;
            while (i < largoLista) {
                term1 = lista.get(i).getTermino1();
                term2 = lista.get(i).getTermino2();
                acm = lista.get(i).getAncestroComunMinimo();
                // CALCULAR D1.
                if (acm == term1) {
                    lista.get(i).setD1(0);
                } else {
                    query = "MATCH (a: Term {accession: " + acm + "}),(b: Term {accession: " + term1 + "}),p=a-[r:FATHER*..]->b RETURN length(p) LIMIT 1";
                    rs = stmt.executeQuery(query);
                    rs.next();
                    lista.get(i).setD1(Integer.parseInt(rs.getString("length(p)")));
                }

                // CALCULAR D2
                if (acm == term2) {
                    lista.get(i).setD2(0);
                } else {
                    query = "MATCH (a: Term {accession: " + acm + "}),(b: Term {accession: " + term2 + "}),p=a-[r:FATHER*..]->b RETURN length(p) LIMIT 1";
                    rs = stmt.executeQuery(query);
                    rs.next();
                    lista.get(i).setD2(Integer.parseInt(rs.getString("length(p)")));
                }

                // CALCULAR D3
                if (raiz == acm) {
                    lista.get(i).setD3(0);
                } else {
                    query = "MATCH (a: Term {accession: " + raiz + "}),(b: Term {accession: " + acm + "}),p=a-[r:FATHER*..]->b RETURN length(p) LIMIT 1";
                    rs = stmt.executeQuery(query);
                    rs.next();
                    lista.get(i).setD3(Integer.parseInt(rs.getString("length(p)")));
                }

                i++;
            }
        }

        return lista;
    }

    public boolean esAncestro(int term1, int term2) throws Exception {
        List<String> lista = new ArrayList<>();
        int raiz = this.raiz(), resultado;

        // Si los términos son iguales, entonces son ancestro
        if (term1 == term2) {
            return true;
        }

        // Si uno de los terminos es la raiz
        if (term1 == raiz || term2 == raiz()) {
            return true;
        }

        Class.forName("org.neo4j.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("MATCH (a: Term {accession: " + term1 + "}),(b: Term {accession: " + term2 + "}),p=(a)-[relacion:FATHER*..]->(b) RETURN count(p) LIMIT 1");
            rs.next();
            resultado = Integer.parseInt(rs.getString("count(p)"));
            if (resultado > 0) {
                return true;
            }

            rs = stmt.executeQuery("MATCH (a: Term {accession: " + term2 + "}),(b: Term {accession: " + term1 + "}),p=(a)-[relacion:FATHER*..]->(b) RETURN count(p) LIMIT 1");
            rs.next();
            resultado = Integer.parseInt(rs.getString("count(p)"));
            if (resultado > 0) {
                return true;
            }
        }
        return false;
    }

    public int lambda(int term1, int term2) throws Exception {
        int raiz = this.raiz(), distancia1, distancia2;

        // si los terminos son iguales, misma jerarquis
        if (term1 == term2) {
            return 0;
        }

        // si uno de los terminos es la raiz, misma vecindad
        if (term1 == raiz || term2 == raiz) {
            return 1;
        }

        // Se obtienen las distancias
        Class.forName("org.neo4j.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
        try (Statement stmt = con.createStatement()) {
            // se obtiene la primera distancia
            ResultSet rs = stmt.executeQuery("MATCH (a: Term {accession: " + term1 + "}),(raiz: Term {accession: " + raiz + "}),p=(raiz)-[:FATHER*..]->(a) RETURN length(p) LIMIT 1");
            rs.next();
            distancia1 = Integer.parseInt(rs.getString("length(p)"));

            // se obtiene la segunda distancia
            rs = stmt.executeQuery("MATCH (a: Term {accession: " + term2 + "}),(raiz: Term {accession: " + raiz + "}),p=(raiz)-[:FATHER*..]->(a) RETURN length(p) LIMIT 1");
            rs.next();
            distancia2 = Integer.parseInt(rs.getString("length(p)"));
        }

        if (distancia1 == distancia2) {
            return 0;
        }
        return 1;
    }

    public int niveles() throws Exception {
        int raiz = this.raiz(), temporal, niveles = 0;

        Class.forName("org.neo4j.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/");

        try (Statement stmt = con.createStatement()) {
            // se obtienen las distancias de la raiz a las hojas
            ResultSet rs = stmt.executeQuery("MATCH (r: Term {accession: " + raiz + "}),(h: Term),p=(r)-[:FATHER*..]->(h) WHERE NOT (h)-[:FATHER]->() RETURN DISTINCT length(p)+1");

            // se guardan las distancias de la raiz a las hojas en una lista
            while (rs.next()) {
                temporal = Integer.parseInt(rs.getString("length(p)+1"));
                if (temporal > niveles) {
                    niveles = temporal;
                }
            }
        }

        return niveles;
    }
    
}
