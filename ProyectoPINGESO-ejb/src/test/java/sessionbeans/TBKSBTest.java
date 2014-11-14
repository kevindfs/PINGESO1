/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import javax.ejb.embeddable.EJBContainer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Italo
 */
public class TBKSBTest {
    
    public TBKSBTest() {
    }
    
    /**
     * Test of calcularTBK method, of class TBKSB.
     */
    @Test
    public void testCalcularTBK() throws Exception {
        System.out.println("calcularTBK");
        int D1 = 0;
        int D2 = 0;
        int D3 = 0;
        int Lambda = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TBKSBLocal instance = (TBKSBLocal)container.getContext().lookup("java:global/classes/TBKSB");
        float expResult = 0.0F;
        float result = instance.calcularTBK(D1, D2, D3, Lambda);
        assertEquals(expResult, result, 0.0);
        container.close();
    }
    
}
