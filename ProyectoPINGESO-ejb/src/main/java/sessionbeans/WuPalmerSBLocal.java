/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import javax.ejb.Local;

/**
 *
 * @author Kevin
 */
@Local
public interface WuPalmerSBLocal {

    float CalcularWuPalmer(int D1, int D2, int D3);
    
}
