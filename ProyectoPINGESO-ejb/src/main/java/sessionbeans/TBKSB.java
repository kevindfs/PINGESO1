/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Kevin
 */
@Stateless
public class TBKSB implements TBKSBLocal {
    @EJB
    private WuPalmerSBLocal wuPalmerSB;

    @Override
    public float calcularTBK(int D1, int D2, int D3, int Lambda) {
        float wp = wuPalmerSB.CalcularWuPalmer(D1, D2, D3);
        System.out.println("Wu Palmer: " + wp);
        System.out.println(D1+ "  "+D2 + "  "+D3);
        int minimo = Math.min(D1+D3, D2+D3);
        float termino1 = ((1-Lambda)*minimo);
        System.out.println("Termino1: " +termino1);
        float termino2 = (float) (Lambda * Math.pow((Math.abs(D1-D2)+1),-1));
        System.out.println("Termino2: "+ termino2);
        float tbk = wp * (termino1+termino2);
        return tbk;
    }

    
}
