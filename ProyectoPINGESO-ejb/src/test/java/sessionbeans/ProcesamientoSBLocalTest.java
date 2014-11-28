/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otrasclases.ParTerminos;

/**
 *
 * @author Italo
 */
public class ProcesamientoSBLocalTest {
    
    public ProcesamientoSBLocalTest() {
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
     * Test of terminosDeUnGen method, of class ProcesamientoSBLocal.
     */
    @Test
    public void testTerminosDeUnGen() {
        System.out.println("terminosDeUnGen");
        String gen = "";
        ProcesamientoSBLocal instance = new ProcesamientoSBLocalImpl();
        List<Integer> expResult = null;
        List<Integer> result = instance.terminosDeUnGen(gen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of combinaTerminos method, of class ProcesamientoSBLocal.
     */
    @Test
    public void testCombinaTerminos() {
        System.out.println("combinaTerminos");
        List<Integer> lista1 = null;
        List<Integer> lista2 = null;
        ProcesamientoSBLocal instance = new ProcesamientoSBLocalImpl();
        List<ParTerminos> expResult = null;
        List<ParTerminos> result = instance.combinaTerminos(lista1, lista2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of CoreApp method, of class ProcesamientoSBLocal.
     */
    @Test
    public void testCoreApp() {
        System.out.println("CoreApp");
        List<String> genes = null;
        int opcion = 0;
        ProcesamientoSBLocal instance = new ProcesamientoSBLocalImpl();
        String expResult = "";
        String result = instance.CoreApp(genes, opcion,1);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of invierteNumero method, of class ProcesamientoSBLocal.
     */
    @Test
    public void testInvierteNumero() {
        System.out.println("invierteNumero");
        int numero = 0;
        ProcesamientoSBLocal instance = new ProcesamientoSBLocalImpl();
        int expResult = 0;
        int result = instance.invierteNumero(numero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class ProcesamientoSBLocalImpl implements ProcesamientoSBLocal {

        public List<Integer> terminosDeUnGen(String gen) {
            return null;
        }

        public List<ParTerminos> combinaTerminos(List<Integer> lista1, List<Integer> lista2) {
            return null;
        }

        public float CoreApp(List<String> genes, int opcion) {
            return 0.0F;
        }

        public int invierteNumero(int numero) {
            return 0;
        }

        @Override
        public String CoreApp(List<String> genes, int opcion, int cluster) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
