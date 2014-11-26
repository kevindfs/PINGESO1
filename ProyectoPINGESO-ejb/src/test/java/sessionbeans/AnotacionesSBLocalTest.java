/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Anotaciones;
import java.util.List;
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
public class AnotacionesSBLocalTest {
    
    public AnotacionesSBLocalTest() {
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
     * Test of create method, of class AnotacionesSBLocal.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Anotaciones anotaciones = null;
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        instance.create(anotaciones);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class AnotacionesSBLocal.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        Anotaciones anotaciones = null;
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        instance.edit(anotaciones);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class AnotacionesSBLocal.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Anotaciones anotaciones = null;
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        instance.remove(anotaciones);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class AnotacionesSBLocal.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Object id = null;
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        Anotaciones expResult = null;
        Anotaciones result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class AnotacionesSBLocal.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        List<Anotaciones> expResult = null;
        List<Anotaciones> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class AnotacionesSBLocal.
     */
    @Test
    public void testFindRange() {
        System.out.println("findRange");
        int[] range = null;
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        List<Anotaciones> expResult = null;
        List<Anotaciones> result = instance.findRange(range);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class AnotacionesSBLocal.
     *
    @Test
    public void testCount() {
        System.out.println("count");
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encontrarTerminos method, of class AnotacionesSBLocal.
     */
    @Test
    public void testEncontrarTerminos() {
        System.out.println("encontrarTerminos");
        String nombreGen = "";
        AnotacionesSBLocal instance = new AnotacionesSBLocalImpl();
        List<Anotaciones> expResult = null;
        List<Anotaciones> result = instance.encontrarTerminos(nombreGen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class AnotacionesSBLocalImpl implements AnotacionesSBLocal {

        public void create(Anotaciones anotaciones) {
        }

        public void edit(Anotaciones anotaciones) {
        }

        public void remove(Anotaciones anotaciones) {
        }

        public Anotaciones find(Object id) {
            return null;
        }

        public List<Anotaciones> findAll() {
            return null;
        }

        public List<Anotaciones> findRange(int[] range) {
            return null;
        }

        public int count() {
            return 0;
        }

        public List<Anotaciones> encontrarTerminos(String nombreGen) {
            return null;
        }
    }
}
