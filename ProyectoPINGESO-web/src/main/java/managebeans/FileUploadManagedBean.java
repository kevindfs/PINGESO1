/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class FileUploadManagedBean {

    UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void fileUploadListener(FileUploadEvent e) {
        // Get uploaded file from the FileUploadEvent
        this.file = e.getFile();
        // Print out the information of the file
        System.out.println("Uploaded File Name Is :: " + file.getFileName() + " :: Uploaded File Size :: " + file.getSize());
        System.out.println(obtenerDatos(this.file));

        // Add message
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(obtenerDatos(this.file)));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File Uploaded Successfully"));

    }

//funcion para convertir datos en cadena de strings
    ArrayList<String> nombreGen = new ArrayList<String>();
    ArrayList<String> numeroGen = new ArrayList<String>();

    private String obtenerDatos(UploadedFile archivo) {

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            InputStream input = archivo.getInputstream();

            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            while ((line = br.readLine()) != null) {
                String linea[] = line.split(",");
                System.out.println("parte 0: " + linea[0]);
                System.out.println("parte 1: " + linea[1]);
                nombreGen.add(linea[0]);
                numeroGen.add(linea[1]);

                sb.append(line + '\n');
            }
            Genes();

        } catch (IOException ex) {
        }
        return sb.toString();
    }

    private void Genes() {
        for (int i = 0; i < nombreGen.size(); i++) {
            System.out.print("de arreglo de genes: " + nombreGen.get(i) + " " + numeroGen.get(i));
        }
    }
}
