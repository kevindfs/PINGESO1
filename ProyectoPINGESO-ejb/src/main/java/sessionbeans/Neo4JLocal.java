/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alonso
 */
@Local
public interface Neo4JLocal {
    // Recibe una query en String y retorna un List<String> con la respuesta de la bd
    List<String> consulta(String _query);
    // Dados los ids de dos nodos, retorna el id del ancestro común mínimo.
    int ancestroComunMinimo(int idNodoUno, int idNodoDos);

    // Dado los ids de dos nodos, retorna la distancia entre ellos
    int distancia(int idNodoUno, int idNodoDos);
    
    // Dados dos nodos, retorna la distancia entre ellos considerando 
    // la dirección de la relación
    int distanciaConDireccion(int idNodoUno, int idNodoDos);
    
    // Dado un nodo retorna su nivel en el árbol
    int nivel(int idNodo);
    
    // Dado dos nodos, retorna true si estos estan en la misma jerarquia. Dos
    // nodos están en la misma jerarquía cuando estan el mismo nivel de 
    // profundidad con respecto al nodo raiz.
    boolean mismaJerarquia(int idNodoUno, int NodoDos);
    

    // Dado dos nodos, retorna true si estos estan en la misma vecindad
    boolean mismaVecindad(int idNodoUno, int idNodoDos);
    
    // Dado los ids de dos nodos, retorna true si el nodoUno es padre del nodoDos
    boolean esPadre(int idNodoUno, int idNodoDos);
    
    // Dado los ids de dos nodos, retorna true si el nodoUno es padre del nodoDos
    boolean esHijo(int idNodoUno, int idNodoDos);
    
    // Dado el id de un nodo retorna el nivel de profundidad del nodo con respecto a la raiz.
    int profundidad(int idNodo);
}
