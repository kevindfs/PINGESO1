/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import javax.ejb.Stateless;

/**
 *
 * @author Kevin
 */
@Stateless
public class LeacockChodorowSB implements LeacockChodorowSBLocal {

    @Override
    public float CalcularLeacockChodorow(int D, int D1, int D2) {
        float calculo = (float) -(Math.log((D1+D2+1)/(float)(2*D)));
        return calculo;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
