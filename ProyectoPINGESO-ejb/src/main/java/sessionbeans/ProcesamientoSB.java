/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.xml.registry.Query;

/**
 *
 * @author Italo
 */
@Stateless
public class ProcesamientoSB implements ProcesamientoSBLocal {

    @Override
    public List obtenerTerminos(String NombreGen) {
        return null;
    }
    
   }