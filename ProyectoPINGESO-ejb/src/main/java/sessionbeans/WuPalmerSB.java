/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import javax.ejb.Stateless;


@Stateless
public class WuPalmerSB implements WuPalmerSBLocal {

    @Override
    public float CalcularWuPalmer(int D1, int D2, int D3) {
        float wp = (2*D3)/(float)((D1+D2)+(2*D3));
        return wp;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
