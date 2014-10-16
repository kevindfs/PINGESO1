/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Kevin
 */
@Named(value = "fileUploadManagedBean")
@RequestScoped
public class FileUploadManagedBean {

    /**
     * Creates a new instance of FileUploadManagedBean
     */
    public FileUploadManagedBean() {
    }
    
}
