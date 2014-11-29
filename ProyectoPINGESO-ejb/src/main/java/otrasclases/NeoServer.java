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
    

}
