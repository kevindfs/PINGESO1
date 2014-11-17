/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Anotaciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Italo
 */
@Local
public interface AnotacionesSBLocal {

    void create(Anotaciones anotaciones);

    void edit(Anotaciones anotaciones);

    void remove(Anotaciones anotaciones);

    Anotaciones find(Object id);

    List<Anotaciones> findAll();

    List<Anotaciones> findRange(int[] range);

    int count();

    List<Anotaciones> encontrarTerminos(String nombreGen);
    
}
