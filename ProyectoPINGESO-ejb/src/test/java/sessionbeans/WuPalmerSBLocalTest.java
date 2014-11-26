/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Italo
 */
public class WuPalmerSBLocalTest {
    
    public WuPalmerSBLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of CalcularWuPalmer method, of class WuPalmerSBLocal.
     */
    @Test
    public void testCalcularWuPalmer() {
        System.out.println("CalcularWuPalmer");
        int D1 = 0;
        int D2 = 0;
        int D3 = 0;
        WuPalmerSBLocal instance = new WuPalmerSBLocalImpl();
        float expResult = 0.0F;
        float result = instance.CalcularWuPalmer(D1, D2, D3);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    public class WuPalmerSBLocalImpl implements WuPalmerSBLocal {

        public float CalcularWuPalmer(int D1, int D2, int D3) {
            return 0.0F;
        }
    }
    
}
