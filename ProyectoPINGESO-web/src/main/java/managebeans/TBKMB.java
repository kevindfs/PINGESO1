/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbeans.TBKSBLocal;
/**
 *
 * @author Kevin
 * ManageBeans Para el índice TBK
 */
@Named(value = "tBKMB")
@RequestScoped
public class TBKMB {
    
    @EJB
    private TBKSBLocal tBKSB;

    private int D1;
    private int D2;
    private int D3;
    private int Lambda;
    private float tbk;

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
    public int getLambda() {
        return Lambda;
    }
    public void setLambda(int Lambda) {
        this.Lambda = Lambda;
    }
    public float getTbk() {
        return tbk;
    }
    public void setTbk(float tbk) {
        this.tbk = tbk;
    }
    /**
     * Creates a new instance of TBKMB
     */
    public TBKMB() {
    }
 
    public void func_tbk(){
    
        tbk = tBKSB.calcularTBK(D1, D2, D3, Lambda);
    }
    
}
