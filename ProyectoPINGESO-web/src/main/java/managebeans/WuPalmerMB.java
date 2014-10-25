/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbeans.WuPalmerSBLocal;

/**
 *
 * @author Kevin
 */
@Named(value = "wuPalmerMB")
@RequestScoped
public class WuPalmerMB {
    @EJB
    private WuPalmerSBLocal wuPalmerSB;

    private int D1;
    private int D2;
    private int D3;
    private float wp;

    public void func_wp(){
        
        wp = wuPalmerSB.CalcularWuPalmer(D1, D2, D3);
        
    }
    
    
    
    public float getWp() {
        return wp;
    }

    public void setWp(float wp) {
        this.wp = wp;
    }
    
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

    public int getD3() {
        return D3;
    }

    public void setD3(int D3) {
        this.D3 = D3;
    }
    
    public WuPalmerMB() {
    }
    
}
