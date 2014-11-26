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
public class LeacockChodorowSBLocalTest {
    
    public LeacockChodorowSBLocalTest() {
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
     * Test of CalcularLeacockChodorow method, of class LeacockChodorowSBLocal.
     */
    @Test
    public void testCalcularLeacockChodorow() {
        System.out.println("CalcularLeacockChodorow");
        int D = 0;
        int D1 = 0;
        int D2 = 0;
        LeacockChodorowSBLocal instance = new LeacockChodorowSBLocalImpl();
        float expResult = 0.0F;
        float result = instance.CalcularLeacockChodorow(D, D1, D2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    public class LeacockChodorowSBLocalImpl implements LeacockChodorowSBLocal {

        public float CalcularLeacockChodorow(int D, int D1, int D2) {
            return 0.0F;
        }
    }
    
}
