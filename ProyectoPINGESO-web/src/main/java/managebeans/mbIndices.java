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

    private String option;
    private Integer nroOpcion;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer opciones(String opc) {
        if (null != opc) switch (opc) {
            case "TBK":
                nroOpcion = 0;
                break;
            case "WP":
                nroOpcion = 1;
                break;
            case "LC":
                nroOpcion = 2;
                break;
        }
        return nroOpcion;
    }

    /**
     * Creates a new instance of mbIndices
     */
    public mbIndices() {
    }

}
