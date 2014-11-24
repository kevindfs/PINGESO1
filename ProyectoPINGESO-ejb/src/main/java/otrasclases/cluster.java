/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otrasclases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class cluster {
    private int id;
    private List<String> genes;

    public cluster(int id, List<String> genes) {
        this.id = id;
        this.genes = genes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getGenes() {
        return genes;
    }

    public void setGenes(List<String> genes) {
        this.genes = genes;
    }
    
}
