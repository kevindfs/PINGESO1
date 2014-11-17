/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Anotaciones;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.List;
import javax.ejb.Stateless;
import otrasclases.ParTerminos;
import javax.xml.registry.Query;


/**
 *
 * @author Italo
 */
@Stateless
public class ProcesamientoSB implements ProcesamientoSBLocal {
    @EJB
    private Neo4JLocal neo4J;


    @Override
    public List obtenerTerminos(String NombreGen) {
        return null;
    }

    @EJB
    private AnotacionesSBLocal anotacionesSB;
    
    @Override
    public float CoreApp(List<String> genes) {
        //Si indice es 1 => Llamar a WuPalmer
        //Si indice es 2 => LLamar a TBK
        //Si indice es 3 => Llamar a Leacock-Chodorow
        int largoListaGenes=genes.size();
        System.out.println("Largo Lista Genes: "+ largoListaGenes);
        List<List<Integer>> matrizTerminos = new ArrayList<>();
        for (int i = 0; i < largoListaGenes; i++) {
            //System.out.println("Gen: " + genes.get(i));
            matrizTerminos.add(terminosDeUnGen(genes.get(i)));
            
        }
        System.out.println(matrizTerminos);
        int i=0,j=0;
        List <ParTerminos> listaTemp = new ArrayList<>();
        List <ParTerminos> listaPares = new ArrayList<>();
        while (i<largoListaGenes) {
            j=i+1;
            while (j<largoListaGenes) {
                listaTemp = combinaTerminos(matrizTerminos.get(i),matrizTerminos.get(j));
                for (int k = 0; k < listaTemp.size(); k++) {
                    listaPares.add(listaTemp.get(k));
                }
                j++;
            }
            i++;
        }
        int largoListaPares = listaPares.size();
        int term1, term2;
        System.out.println("Cantidad Pares a consultar:" + largoListaPares);
        int D1,D2,D3;
        Neo4J db = new Neo4J("C:\\Users\\Kevin\\Documents\\Neo4j\\godb");
        for (int k = 0; k < largoListaPares; k++) {
            System.out.println("Entré al for, valor k: " + k);
            term1 = listaPares.get(k).getTermino1();
            term2 = listaPares.get(k).getTermino2();
            System.out.println("Termino1: " +term1 +" Termino2: "+term2);
            int idACM = db.ancestroComunMinimo(term1, term2);
            System.out.println("ACM: "+ idACM);
            D1 = db.distancia(idACM, term1);
            System.out.println("D1: " + D1);
            D2 = db.distancia(idACM, term2);
            System.out.println("D2: " + D2);
            D3 = db.distancia(8150, idACM);
            System.out.println("D3: " + D3);
        }
        
        //int dist = db.distancia(8150, 1);
        //System.out.println("idACM: "+ idACM);
        //System.out.println("dist: "+ dist);
        
        /*for (int k = 0; k < largoListaPares; k++) {
            //Imprimir los pares
            System.out.println(listaPares.get(k).getTermino1() + " : "+ listaPares.get(k).getTermino2());
        */
        
        
        return 0.0F;
    }

    @Override
    public List<Integer> terminosDeUnGen(String gen) {
        List<Anotaciones> termino = anotacionesSB.encontrarTerminos(gen);
        List<Integer> listaTerminos = new ArrayList<>();
        int largoLista = termino.size();
        for (int i = 0; i < largoLista; i++) {
            listaTerminos.add(termino.get(i).getTermino());
        }
        return listaTerminos;
    }

    @Override
    public List<ParTerminos> combinaTerminos(List<Integer> lista1, List<Integer> lista2) {
        int largoLista1 = lista1.size();
        int largoLista2 = lista2.size();
        int i=0,j;
        List<ParTerminos> listaPares2 = new ArrayList<>();
        while (i<largoLista1) {
            j=0;
            while (j<largoLista2) {
                listaPares2.add(new ParTerminos(lista1.get(i),lista2.get(j)));
                //System.out.println(i + " : "+ j);
                j++;
            }
            i++;
        }
        //System.out.println("Cantidad Pares por combinacion de genes:" + listaPares2.size());
        return listaPares2;
    }
}