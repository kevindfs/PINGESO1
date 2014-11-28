/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otrasclases;

/**
 *
 * @author Kevin
 */
public class ParTerminos {
    int termino1;
    int termino2;
    int ancestroComunMinimo;
    int d1;
    int d2;
    int d3;

    public ParTerminos(int termino1, int termino2) {
        this.termino1 = termino1;
        this.termino2 = termino2;
    }

    public int getAncestroComunMinimo() {
        return ancestroComunMinimo;
    }

    public void setAncestroComunMinimo(int ancestroComunMinimo) {
        this.ancestroComunMinimo = ancestroComunMinimo;
    }

    public int getD1() {
        return d1;
    }

    public void setD1(int d1) {
        this.d1 = d1;
    }

    public int getD2() {
        return d2;
    }

    public void setD2(int d2) {
        this.d2 = d2;
    }

    public int getD3() {
        return d3;
    }

    public void setD3(int d3) {
        this.d3 = d3;
    }

    public int getTermino1() {
        return termino1;
    }

    public void setTermino1(int termino1) {
        this.termino1 = termino1;
    }

    public int getTermino2() {
        return termino2;
    }

    public void setTermino2(int termino2) {
        this.termino2 = termino2;
    }
    
}
