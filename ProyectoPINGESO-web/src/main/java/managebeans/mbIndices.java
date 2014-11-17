/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author Italo
 */
@Named(value = "mbIndices")
@Dependent
@ManagedBean
public class mbIndices {

    private String[] indicesSelecionados;
    private List<String> indices;
    private Integer nroIndice;

    @PostConstruct
    public void init() {
        indices = new ArrayList<String>();
        indices.add("TBK");
        //indices.add("GS2");
        indices.add("Wu-Palmer");
        //indices.add("RESNIK");
        //indices.add("LIN");
        indices.add("LC");
        //indices.add("JC");
    }

    public String[] getIndicesSelecionados() {
        return indicesSelecionados;
    }

//    public Integer getNumeroIndice(List<String> s){
//        return nroIndice;
//    }
    
    public void setIndicesSelecionados(String[] indicesSelecionados) {
        this.indicesSelecionados = indicesSelecionados;
        System.out.println("indices seleccionados" + indicesSelecionados.toString());
    }

    public List<String> getIndices() {
        //System.out.println("indices"+indices);
        return indices;
    }

    /**
     * Creates a new instance of mbIndices
     */
    public mbIndices() {
    }

}
