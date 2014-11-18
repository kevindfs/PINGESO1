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
    private LeacockChodorowSBLocal leacockChodorowSB;
    
    @EJB
    private TBKSBLocal tBKSB;
    
    @EJB
    private WuPalmerSBLocal wuPalmerSB;
    
    @EJB
    private Neo4JLocal neo4J;

    @EJB
    private AnotacionesSBLocal anotacionesSB;
    
    @Override
    public float CoreApp(List<String> genes, int opcion) {
        //Si indice es 1 => Llamar a WuPalmer
        //Si indice es 2 => LLamar a TBK
        //Si indice es 3 => Llamar a Leacock-Chodorow
        float indiceWuPalmer=0;
        float indiceTBK = 0;
        float indiceLeacockChodorow = 0;
        int largoListaGenes=genes.size();
        System.out.println("Largo Lista Genes: "+ largoListaGenes);
        List<List<Integer>> matrizTerminos = new ArrayList<>();
        for (int i = 0; i < largoListaGenes; i++) {
            //System.out.println("Gen: " + genes.get(i));
            matrizTerminos.add(terminosDeUnGen(genes.get(i)));
            
        }
        System.out.println(matrizTerminos);
        int i=0;
        int j;
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
        /*@param raiz = raiz del arbol*/
        int raiz = 1;
        int D1=0,D2=0,D3=0, idACM=raiz;
        /*@param D = Profundidad*/
        int D = 5; 
        Neo4J db = new Neo4J("C:\\Users\\Kevin\\Documents\\Neo4j\\Sp3db");
        for (int k = 0; k < largoListaPares; k++) {
            term1 = listaPares.get(k).getTermino1();
            term2 = listaPares.get(k).getTermino2();
            System.out.println("Termino1: " +term1 +" Termino2: "+term2);
            idACM = db.ancestroComunMinimo(term1, term2);
            System.out.println("ACM: "+ idACM);
            D1 = db.distancia(idACM, term1);
            System.out.println("D1: " + D1);
            D2 = db.distancia(idACM, term2);
            System.out.println("D2: " + D2);
            D3 = db.distancia(raiz, idACM);
            System.out.println("D3: " + D3);
            float wp = wuPalmerSB.CalcularWuPalmer(D1, D2, D3);
            float lc = leacockChodorowSB.CalcularLeacockChodorow(D, D1, D2);
            float tbk = tBKSB.calcularTBK(D1, D2, D3, 1);
            indiceLeacockChodorow = indiceLeacockChodorow +lc;
            indiceTBK = indiceTBK + tbk;
            indiceWuPalmer = indiceWuPalmer + wp;
            System.out.println("Wu Palmer:  "+ wp);
            System.out.println("Leacock Chodorow:  "+ lc);
            System.out.println("TBK:  "+ tbk);
        }
        //int dist = db.distancia(8150, 1);
        //System.out.println("idACM: "+ idACM);
        //System.out.println("dist: "+ dist);
        
        /*for (int k = 0; k < largoListaPares; k++) {
            //Imprimir los pares
            System.out.println(listaPares.get(k).getTermino1() + " : "+ listaPares.get(k).getTermino2());
        */
        float indiceWuPalmerFinal = (indiceWuPalmer/largoListaPares);
        float indiceLCFinal = (indiceLeacockChodorow/largoListaGenes);
        float indiceTbkFinal = (indiceTBK/largoListaGenes);
        System.out.println("Wu Palmer Final: " + indiceWuPalmerFinal);
        System.out.println("L.C. Final: " + indiceLCFinal);
        System.out.println("T.B.K. Final: " + indiceTbkFinal);
        
        return indiceWuPalmerFinal;
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

    @Override
    public int invierteNumero(int numero) {
        int numeroInvertido = 0 ; 
        int division = numero ; 
        int restoDivision = 0; 
        while (division != 0) { 
            restoDivision = division % 10 ; 
            division = division / 10 ; 
            numeroInvertido = numeroInvertido * 10 + restoDivision; 
        } 
        return numeroInvertido;
    }
    
}