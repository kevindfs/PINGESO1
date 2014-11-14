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
public class LeacockChodorowSBTest {
    
    public LeacockChodorowSBTest() {
    }

    /**
     * Test of CalcularLeacockChodorow method, of class LeacockChodorowSB.
     * @throws java.lang.Exception
     */
    @Test
    
    public void testCalcularLeacockChodorow() throws Exception {
        System.out.println("CalcularLeacockChodorow");
        int D = 0;
        int D1 = 0;
        int D2 = 0;
        try (EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer()) {
            LeacockChodorowSBLocal instance = (LeacockChodorowSBLocal)container.getContext().lookup("java:global/classes/LeacockChodorowSB");
            float expResult = 0.0F;
            float result = instance.CalcularLeacockChodorow(D, D1, D2);
            assertEquals(expResult, result, 0.0);
        }
    }
    
}
