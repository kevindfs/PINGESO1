/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.Stateless;
import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringLogger;
import otrasclases.Maper;

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
        List<String> listaStrings = new ArrayList<>();
        List<Integer> listaEnteros = new ArrayList<>();
        String query = "", fila = "", idString = "";
        int entero = 0, largoLista=0, i, j, largoFila = 0, minimaDistancia = 0;
        
        query = "MATCH (a: Term {accession:" + idNodoUno + "}),(b: Term {accession:" + idNodoDos + "}),p=a-[r:PADRE*..]->b" + "\n" + "RETURN reduce(distancia = -1, n IN nodes(p)| distancia + 1) AS reduction;";
        
        graphDataService = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        
        ExecutionResult result;
        Transaction transaction = graphDataService.beginTx();
        ExecutionEngine engine = new ExecutionEngine(graphDataService, StringLogger.SYSTEM);
        
        try {
            result = engine.execute(query);
            
            while (result.hasNext()) {
                
                fila = result.next().toString();
                listaEnteros.add(Maper.getInt(fila));
//                Map map = new Map();
//                largoFila = fila.length();
                
//                idString = "";
//                j=largoFila-2;
//                while(j > 0) {
//                    if(fila.charAt(j) == ' ') {
//                        break;
//                    }
//                    idString = idString + fila.charAt(j);
//                    j--;
//                }
//                listaEnteros.add(result.next().toString());
                //listaStrings.add(result.next().);
            }

            transaction.success();
            
        } finally {
            transaction.finish();
        }
        
        graphDataService.shutdown();
        
        //entero = Integer.parseInt(listaStrings.get(0));
        
        largoLista = listaStrings.size();
        
        i = 0;
        while(i < largoLista) {
            System.out.println(listaStrings.get(i));
            i++;
        }
        
        minimaDistancia = listaEnteros.get(0);
        largoLista = listaEnteros.size();
        i=0;
        while(i < largoLista) {
            if(listaEnteros.get(i) < minimaDistancia) minimaDistancia = listaEnteros.get(i);
            i++;
        }
        
        return minimaDistancia;
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

    @Override
    public int distanciaConDireccion(int idNodoUno, int idNodoDos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargaBaseDeDatos(String ruta) {
        String query = "";
        graphDataService = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        
        ExecutionResult result;
        Transaction transaction = graphDataService.beginTx();
        ExecutionEngine engine = new ExecutionEngine(graphDataService, StringLogger.SYSTEM);
        
        query = "LOAD CSV WITH HEADERS FROM \"" + ruta + "\" AS csvLine\nMERGE (padre:Term {accession: toInt(csvLine.accessionPadre)})\nMERGE (hijo: Term {accession: toInt(csvLine.accessionHijo)})\nCREATE (padre)-[:FATHER]->(hijo);";
        try {
            result = engine.execute(query);
            transaction.success();
            
        } finally {
            transaction.finish();
        }

        graphDataService.shutdown();
    }
}
