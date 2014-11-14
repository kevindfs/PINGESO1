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
public class WuPalmerSBTest {
    
    public WuPalmerSBTest() {
    }
    
    /**
     * Test of CalcularWuPalmer method, of class WuPalmerSB.
     */
    @Test
    public void testCalcularWuPalmer() throws Exception {
        System.out.println("CalcularWuPalmer");
        int D1 = 0;
        int D2 = 0;
        int D3 = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        WuPalmerSBLocal instance = (WuPalmerSBLocal)container.getContext().lookup("java:global/classes/WuPalmerSB");
        float expResult = 0.0F;
        float result = instance.CalcularWuPalmer(D1, D2, D3);
        assertEquals(expResult, result, 0.0);
        container.close();
    }
    
}
