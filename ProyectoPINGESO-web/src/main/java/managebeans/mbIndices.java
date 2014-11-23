/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import sessionbeans.ProcesamientoSBLocal;
/**
 *
 * @author Italo
 */


@Named(value = "mbIndices")
@Dependent
@ManagedBean
public class mbIndices {
    
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
    
    private String option;
    private Integer nroOpcion;
    private float outputIndice;

    public float getOutputIndice() {
        return outputIndice;
    }

    public void setOutputIndice(float outputIndice) {
        this.outputIndice = outputIndice;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public static List<String> getGenes() {
        return genes;
    }

    public static void setGenes(List<String> genes) {
        mbIndices.genes = genes;
    }
    
    public mbIndices() {
    }

    public void opciones(String option) {
        System.out.println("Llamé al mbIndices ");
        double timeStart, timeStop;
        timeStart = System.currentTimeMillis();
        System.out.println("tiempo de partida: "+timeStart);
        
        if (null != option) switch (option) {
            case "TBK":
                nroOpcion = 0;
                System.out.println("Opcion TBK:" + nroOpcion);
                genes.clear();
                genes.add("gen6");genes.add("gen7");
                setOutputIndice(procesamientoSB.CoreApp(genes, nroOpcion));
                System.out.println("OutputIndice: "+ outputIndice);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Indice: ",Float.toString(outputIndice)));
                break;
            case "WP":
                nroOpcion = 1;
                System.out.println("Opcion WP:" + nroOpcion);
                genes.clear();
                genes.add("gen2");genes.add("gen3");
                outputIndice = procesamientoSB.CoreApp(genes, nroOpcion);
                System.out.println("OutputIndice: "+ outputIndice);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Indice: ",Float.toString(outputIndice)));
                
                        
                System.out.println("indice: "+Float.toString(outputIndice));
                
                break;
            case "LC":
                nroOpcion = 2;
                System.out.println("Opcion LC:" + nroOpcion);
                genes.clear();
                genes.add("gen1");genes.add("gen2");
                outputIndice = procesamientoSB.CoreApp(genes, nroOpcion);
                System.out.println("OutputIndice: "+ outputIndice);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Indice: ",Float.toString(outputIndice)));               
                break;
                
                
        }
        timeStop = System.currentTimeMillis();
        System.out.println("Tiempo final: "+timeStop);
        double tiempoSegundos = (timeStop-timeStart)*0.001;
        System.out.println("Tiempo total: "+ tiempoSegundos+"segundos");
        
    }
}
