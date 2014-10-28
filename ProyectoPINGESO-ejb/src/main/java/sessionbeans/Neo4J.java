/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringLogger;

/**
 *
 * @author alonso
 */
@Stateless
public class Neo4J implements Neo4JLocal {
    private String dbPath;
    private GraphDatabaseService graphDataService;

    public Neo4J() {
    }
    
    public Neo4J(String _dbPath) {
        dbPath = _dbPath;
    }

    @Override
    public int ancestroComunMinimo(int idNodoUno, int idNodoDos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int distancia(int idNodoUno, int idNodoDos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int nivel(int idNodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mismaJerarquia(int idNodoUno, int NodoDos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mismaVecindad(int idNodoUno, int idNodoDos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean esPadre(int idNodoUno, int idNodoDos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean esHijo(int idNodoUno, int idNodoDos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int profundidad(int idNodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> consulta(String _query) {
        List<String> lista = new ArrayList<>();
        graphDataService = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        
        ExecutionResult result;
        Transaction transaction = graphDataService.beginTx();
        ExecutionEngine engine = new ExecutionEngine(graphDataService, StringLogger.SYSTEM);
        
        try {
            result = engine.execute(_query);
            
            while (result.hasNext()) {
                lista.add(result.next().toString());
            }

            transaction.success();
            
        } finally {
            transaction.finish();
        }
        
        graphDataService.shutdown();

        return lista;
    }
}
