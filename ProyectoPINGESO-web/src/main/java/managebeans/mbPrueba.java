/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import entities.Anotaciones;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbeans.ProcesamientoSBLocal;
/**
 *
 * @author Kevin
 */
@Named(value = "mbPrueba")
@RequestScoped
public class mbPrueba {
    @EJB
    private ProcesamientoSBLocal procesamientoSB;    
    
    private int a = 0;
    private static List<String> genes = new ArrayList<>();
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public static List<String> getGenes() {
        return genes;
    }

    public static void setGenes(List<String> genes) {
        mbPrueba.genes = genes;
    }  
    
    @PostConstruct
    public void init() {
        genes.clear();
        genes.add("gen1");genes.add("gen2");genes.add("gen3");
        String salida;
        //salida = procesamientoSB.CoreApp(genes, 1,1,);
        
  }
    public mbPrueba() {
    }
    public void mb () throws Exception{
        otrasclases.OtroMainNoLoToquen.main();
    }
}
