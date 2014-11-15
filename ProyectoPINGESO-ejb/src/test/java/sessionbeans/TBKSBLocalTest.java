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
public class TBKSBLocalTest {
    
    public TBKSBLocalTest() {
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
     * Test of calcularTBK method, of class TBKSBLocal.
     */
    @Test
    public void testCalcularTBK() {
        System.out.println("calcularTBK");
        int D1 = 0;
        int D2 = 0;
        int D3 = 0;
        int Lambda = 0;
        TBKSBLocal instance = new TBKSBLocalImpl();
        float expResult = 0.0F;
        float result = instance.calcularTBK(D1, D2, D3, Lambda);
        assertEquals(expResult, result, 0.0);
    }

    public class TBKSBLocalImpl implements TBKSBLocal {

        public float calcularTBK(int D1, int D2, int D3, int Lambda) {
            return 0.0F;
        }
    }
    
}
