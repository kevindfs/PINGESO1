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
    
    public int ancestroComunMinimo(int accessionUno, int accessionDos) {
        int raiz = 1;
        if(accessionUno==raiz || accessionDos==raiz){
            return 1;
        }
        if (accessionDos == accessionUno){
            return accessionDos;
        }
        if(this.esPadre(accessionUno, accessionDos) ) {
            return accessionUno;
        }
        
        if(this.esPadre(accessionDos, accessionUno)) {
            return accessionDos;
        }
        return Integer.parseInt(Maper.getString(this.consulta("MATCH (a: Term {accession: " + accessionUno + "}),(b: Term {accession: " + accessionDos + "}),(c),p=a<-[r:FATHER*..]-c-[r:FATHER*..]->b RETURN c.accession LIMIT 1;").get(0)));
    }
    
    private void creaIndice() {
        this.consulta("CREATE INDEX ON :Term(accession);");
    }

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
    public boolean esPadre(int accessionUno, int accesionDos) {
        List<Integer> listaPadres = new ArrayList<>(this.padres(accesionDos));
        int largoLista = listaPadres.size(), i;
        
        i=0;
        while(i < largoLista) {
            if(accessionUno == listaPadres.get(i))  return true;
            i++;
        }
        return false;
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
    public void cargaBaseDeDatos(String ruta) {
        this.creaIndice();
        String query = "";
        graphDataService = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        
        ExecutionResult result;
        Transaction transaction = graphDataService.beginTx();
        ExecutionEngine engine = new ExecutionEngine(graphDataService, StringLogger.SYSTEM);
        
        query = "LOAD CSV WITH HEADERS FROM \"" + ruta + "\" AS csvLine\nMERGE (padre:Term {accession: toInt(csvLine.accessionPadre)})\nMERGE (hijo: Term {accession: toInt(csvLine.accessionHijo)})\nCREATE (padre)<-[:FATHER]-(hijo);";
        try {
            result = engine.execute(query);
            transaction.success();
            
        } finally {
            transaction.finish();
        }

        graphDataService.shutdown();
    }

    @Override
    public int distancia(int accessionUno, int accessionDos) {
        if (accessionDos == accessionUno){
            return 0;
        }
        String datoString, query = "MATCH (a: Term {accession: " + accessionUno + "}),(b: Term {accession: " + accessionDos + "}),p=a-[r:FATHER*..]->b RETURN reduce(distancia = -1, n IN nodes(p)| distancia + 1) AS reduction;";
        int i, datoEntero, largoLista, distancia;
        List<Integer> listaEnteros = new ArrayList<>();

        List<String> listaConsulta = new ArrayList<>(this.consulta(query));
        largoLista = listaConsulta.size();
        
        // Se obtienen los datos
        i=0;
        while(i < largoLista) {
            datoString = Maper.getString(listaConsulta.get(i));
            datoEntero = Integer.parseInt(datoString);
            listaEnteros.add(datoEntero);
            i++;
        }
        
        // Se obtienen la distancia mínima
        i=1;
        distancia = listaEnteros.get(0);
        //System.out.println("Distancia 0:" + distancia);
        while(i < largoLista) {
            if(listaEnteros.get(i) < distancia){
                distancia = listaEnteros.get(i);
            }
            i++;
        }

        return distancia;
    }

    @Override
    public List<Integer> padres(int accession) {
        List<String> listaConsulta = new ArrayList<>(this.consulta("MATCH (a: Term {accession: " + accession + "}),(a)<-[:FATHER]-(b) RETURN b.accession;"));
        List<Integer> listaAccessions = new ArrayList<>();
        String datoString;
        int i, largoLista = listaConsulta.size();

        i=0;
        while(i < largoLista) {
            datoString = Maper.getString(listaConsulta.get(i));
            listaAccessions.add(Integer.parseInt(datoString));
            i++;
        }
        return listaAccessions;
    }

    @Override
    public int raiz() {
        int root;
        List<String> lista = new ArrayList<>(this.consulta("MATCH (n) WHERE NOT (n)<-[:FATHER]-() RETURN n.accession;"));
        root = Integer.parseInt(Maper.getString(lista.get(0)));
        return root;
    }
}
