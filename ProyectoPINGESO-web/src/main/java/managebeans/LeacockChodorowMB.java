/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbeans.LeacockChodorowSBLocal;

/**
 *
 * @author Kevin
 */
@Named(value = "leacockChodorowMB")
@RequestScoped
public class LeacockChodorowMB {
    @EJB
    private LeacockChodorowSBLocal leacockChodorow;

    /**
     * Creates a new instance of LeacockChodorowMB*/
    int D1,D2,D;
    float salida;

    public int getD1() {
        return D1;
    }

    public void setD1(int D1) {
        this.D1 = D1;
    }

    public int getD2() {
        return D2;
    }

    public void setD2(int D2) {
        this.D2 = D2;
    }

    public int getD() {
        return D;
    }

    public void setD(int D) {
        this.D = D;
    }

    public float getSalida() {
        return salida;
    }

    public void setSalida(float salida) {
        this.salida = salida;
    }
    
    
    public LeacockChodorowMB() {
    }
    
    public void func_lc(){
        salida = leacockChodorow.CalcularLeacockChodorow(D, D1, D2);
    }
}
