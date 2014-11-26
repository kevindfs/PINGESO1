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
public class Neo4JLocalTest {
    
    public Neo4JLocalTest() {
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
     * Test of esAncestro method, of class Neo4JLocal.
     */
    @Test
    public void testEsAncestro() {
        System.out.println("esAncestro");
        int _accessionTerminoUno = 0;
        int _accessionTerminoDos = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        boolean expResult = false;
        boolean result = instance.esAncestro(_accessionTerminoUno, _accessionTerminoDos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of probabilidad method, of class Neo4JLocal.
     */
    @Test
    public void testProbabilidad() {
        System.out.println("probabilidad");
        int _accessionTermino = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        float expResult = 0.0F;
        float result = instance.probabilidad(_accessionTermino);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of ancestrosComunesMinimos method, of class Neo4JLocal.
     */
    @Test
    public void testAncestrosComunesMinimos() {
        System.out.println("ancestrosComunesMinimos");
        List<ParTerminos> _listaParTerminos = null;
        Neo4JLocal instance = new Neo4JLocalImpl();
        List<Integer> expResult = null;
        List<Integer> result = instance.ancestrosComunesMinimos(_listaParTerminos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of distancias method, of class Neo4JLocal.
     */
    @Test
    public void testDistancias() {
        System.out.println("distancias");
        List<ParTerminos> _listaParTerminos = null;
        Neo4JLocal instance = new Neo4JLocalImpl();
        List<Integer> expResult = null;
        List<Integer> result = instance.distancias(_listaParTerminos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of raiz method, of class Neo4JLocal.
     */
    @Test
    public void testRaiz() {
        System.out.println("raiz");
        Neo4JLocal instance = new Neo4JLocalImpl();
        int expResult = 0;
        int result = instance.raiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of cargaBaseDeDatos method, of class Neo4JLocal.
     */
    @Test
    public void testCargaBaseDeDatos() {
        System.out.println("cargaBaseDeDatos");
        String path = "";
        Neo4JLocal instance = new Neo4JLocalImpl();
        instance.cargaBaseDeDatos(path);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of padres method, of class Neo4JLocal.
     */
    @Test
    public void testPadres() {
        System.out.println("padres");
        int accession = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        List<Integer> expResult = null;
        List<Integer> result = instance.padres(accession);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of consulta method, of class Neo4JLocal.
     */
    @Test
    public void testConsulta() {
        System.out.println("consulta");
        String _query = "";
        Neo4JLocal instance = new Neo4JLocalImpl();
        List<String> expResult = null;
        List<String> result = instance.consulta(_query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of ancestroComunMinimo method, of class Neo4JLocal.
     */
    @Test
    public void testAncestroComunMinimo() {
        System.out.println("ancestroComunMinimo");
        int idNodoUno = 0;
        int idNodoDos = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        int expResult = 0;
        int result = instance.ancestroComunMinimo(idNodoUno, idNodoDos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of distancia method, of class Neo4JLocal.
     */
    @Test
    public void testDistancia() {
        System.out.println("distancia");
        int accessionUno = 0;
        int accessionDos = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        int expResult = 0;
        int result = instance.distancia(accessionUno, accessionDos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of nivel method, of class Neo4JLocal.
     */
    @Test
    public void testNivel() {
        System.out.println("nivel");
        int idNodo = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        int expResult = 0;
        int result = instance.nivel(idNodo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of mismaJerarquia method, of class Neo4JLocal.
     */
    @Test
    public void testMismaJerarquia() {
        System.out.println("mismaJerarquia");
        int idNodoUno = 0;
        int NodoDos = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        boolean expResult = false;
        boolean result = instance.mismaJerarquia(idNodoUno, NodoDos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of mismaVecindad method, of class Neo4JLocal.
     */
    @Test
    public void testMismaVecindad() {
        System.out.println("mismaVecindad");
        int idNodoUno = 0;
        int idNodoDos = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        boolean expResult = false;
        boolean result = instance.mismaVecindad(idNodoUno, idNodoDos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of esPadre method, of class Neo4JLocal.
     */
    @Test
    public void testEsPadre() {
        System.out.println("esPadre");
        int accessionUno = 0;
        int accessionDos = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        boolean expResult = false;
        boolean result = instance.esPadre(accessionUno, accessionDos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of esHijo method, of class Neo4JLocal.
     */
    @Test
    public void testEsHijo() {
        System.out.println("esHijo");
        int idNodoUno = 0;
        int idNodoDos = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        boolean expResult = false;
        boolean result = instance.esHijo(idNodoUno, idNodoDos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of profundidad method, of class Neo4JLocal.
     */
    @Test
    public void testProfundidad() {
        System.out.println("profundidad");
        int idNodo = 0;
        Neo4JLocal instance = new Neo4JLocalImpl();
        int expResult = 0;
        int result = instance.profundidad(idNodo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    public class Neo4JLocalImpl implements Neo4JLocal {

        public boolean esAncestro(int _accessionTerminoUno, int _accessionTerminoDos) {
            return false;
        }

        public float probabilidad(int _accessionTermino) {
            return 0.0F;
        }

        public List<Integer> ancestrosComunesMinimos(List<ParTerminos> _listaParTerminos) {
            return null;
        }

        public List<Integer> distancias(List<ParTerminos> _listaParTerminos) {
            return null;
        }

        public int raiz() {
            return 0;
        }

        public void cargaBaseDeDatos(String path) {
        }

        public List<Integer> padres(int accession) {
            return null;
        }

        public List<String> consulta(String _query) {
            return null;
        }

        public int ancestroComunMinimo(int idNodoUno, int idNodoDos) {
            return 0;
        }

        public int distancia(int accessionUno, int accessionDos) {
            return 0;
        }

        public int nivel(int idNodo) {
            return 0;
        }

        public boolean mismaJerarquia(int idNodoUno, int NodoDos) {
            return false;
        }

        public boolean mismaVecindad(int idNodoUno, int idNodoDos) {
            return false;
        }

        public boolean esPadre(int accessionUno, int accessionDos) {
            return false;
        }

        public boolean esHijo(int idNodoUno, int idNodoDos) {
            return false;
        }

        public int profundidad(int idNodo) {
            return 0;
        }
    }
    
}
