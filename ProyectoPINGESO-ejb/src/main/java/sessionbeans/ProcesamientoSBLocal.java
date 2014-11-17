/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Anotaciones;
import java.util.List;
import javax.ejb.Local;
import otrasclases.ParTerminos;
/**
 *
 * @author Italo
 */
@Local
public interface ProcesamientoSBLocal {

    float CoreApp(List<String> genes);

    List<Integer> terminosDeUnGen(String gen);

    List<ParTerminos> combinaTerminos(List<Integer> lista1, List<Integer> lista2);

}
