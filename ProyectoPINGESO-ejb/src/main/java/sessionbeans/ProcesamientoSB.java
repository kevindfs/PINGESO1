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
import otrasclases.NeoServer;
import otrasclases.OtroMainNoLoToquen;

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

    //@EJB
    //private Neo4JLocal neo4J;

    @EJB
    private AnotacionesSBLocal anotacionesSB;  
    
    private int contador;
    
    //Neo4J db = new Neo4J("C:\\Users\\Kevin\\Documents\\Neo4j\\Sp3db2");

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
        
    @Override
    public String CoreApp(List<String> genes, int opcion, int cluster) throws Exception{
        //opcion == 0 => TBK
        //opcion == 1 => WuPalmer
        //opcion == 2 => Leacock-Chodorow
        NeoServer neo = new NeoServer();
        float indiceWuPalmer = 0;
        float indiceTBK = 0;
        float indiceLeacockChodorow = 0;
        contador = 0;
        int largoListaGenes = genes.size();
        System.out.println("Largo Lista Genes: " + largoListaGenes);
        List<List<Integer>> matrizTerminos = new ArrayList<>();
        for (int i = 0; i < largoListaGenes; i++) {
            //System.out.println("Gen: " + genes.get(i));
            matrizTerminos.add(terminosDeUnGen(genes.get(i)));

        }
        System.out.println(matrizTerminos);
        int i = 0;
        int j;
        List<ParTerminos> listaTemp = new ArrayList<>();
        List<ParTerminos> listaPares = new ArrayList<>();
        while (i < largoListaGenes) {
            j = i + 1;
            while (j < largoListaGenes) {
                listaTemp = combinaTerminos(matrizTerminos.get(i), matrizTerminos.get(j));
                for (int k = 0; k < listaTemp.size(); k++) {
                    listaPares.add(listaTemp.get(k));
                }
                j++;
            }
            i++;
        }

        System.out.println("0");
        int raiz = neo.raiz();
        System.out.println("1");
        
        
        listaPares = new ArrayList<>(neo.ancestrosComunesMinimos(listaPares));
        System.out.println("2");
        listaPares = new ArrayList<>(neo.distancias(listaPares));
        System.out.println("3");
        int largoListaPares = listaPares.size();
        int term1, term2;
        System.out.println("Cantidad Pares a consultar:" + largoListaPares);
        //@param raiz = raiz del arbol
        
        //@param D = Profundidad
        int D = neo.niveles();
        int D1,D2,D3,lambda=0;
        float f;
        for (int k = 0; k < largoListaPares; k++) {
            term1 = listaPares.get(k).getTermino1();
            term2 = listaPares.get(k).getTermino2();
            D1 = listaPares.get(k).getD1();
            D2 = listaPares.get(k).getD2();
            D3 = listaPares.get(k).getD3();
            System.out.println("Par: " + (contador + 1) + "  Termino1: " + term1 + "  Termino2: " + term2);
            if (opcion == 0) {
                if(neo.esAncestro(term1, term2)){
                    f=1;
                    float tbk = wuPalmerSB.CalcularWuPalmer(D1, D2, D3);
                    indiceTBK = indiceTBK + tbk;
                    System.out.println("TBK:  " + tbk);
                    contador++;
                }
                else{
                    lambda = neo.lambda(term1, term2);
                    float tbk = tBKSB.calcularTBK(D1, D2, D3, lambda);
                    indiceTBK = indiceTBK + tbk;
                    System.out.println("TBK:  " + tbk);
                    contador++;
                }
                if (contador == largoListaPares) {
                    float indiceTbkFinal = (indiceTBK / largoListaGenes);
                    System.out.println("T.B.K. Final: " + indiceTbkFinal);
                    String salida = Float.toString(indiceTbkFinal) + "\t|\t" + cluster;
                    return salida;
                }
            }
            if (opcion == 1) {
                float wp = wuPalmerSB.CalcularWuPalmer(D1, D2, D3);
                indiceWuPalmer = indiceWuPalmer + wp;
                System.out.println("Wu Palmer:  " + wp);
                contador++;
                if (contador == largoListaPares) {
                    float indiceWuPalmerFinal = (indiceWuPalmer / largoListaPares);
                    System.out.println("Wu Palmer Final: " + indiceWuPalmerFinal);
                    String salida = Float.toString(indiceWuPalmerFinal) + "\t|\t" + cluster;
                    return salida;
                }
            }
            if (opcion == 2) {
                float lc = leacockChodorowSB.CalcularLeacockChodorow(D, D1, D2);
                indiceLeacockChodorow = indiceLeacockChodorow + lc;
                System.out.println("Leacock Chodorow:  " + lc);
                contador++;
                if (contador == largoListaPares) {
                    float indiceLCFinal = (indiceLeacockChodorow / largoListaGenes);
                    System.out.println("L.C. Final: " + indiceLCFinal);
                    String salida = Float.toString(indiceLCFinal) + "\t|\t" + cluster;
                    return salida;
                }
            }
        }
        /*for (int k = 0; k < largoListaPares; k++) {
         //Imprimir los pares
         System.out.println(listaPares.get(k).getTermino1() + " : "+ listaPares.get(k).getTermino2());
         */
        System.out.println("Retorno Final ");
        return "False";
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
        int i = 0, j;
        List<ParTerminos> listaPares2 = new ArrayList<>();
        while (i < largoLista1) {
            j = 0;
            while (j < largoLista2) {
                listaPares2.add(new ParTerminos(lista1.get(i), lista2.get(j)));
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
        int numeroInvertido = 0;
        int division = numero;
        int restoDivision = 0;
        while (division != 0) {
            restoDivision = division % 10;
            division = division / 10;
            numeroInvertido = numeroInvertido * 10 + restoDivision;
        }
        return numeroInvertido;
    }
}
