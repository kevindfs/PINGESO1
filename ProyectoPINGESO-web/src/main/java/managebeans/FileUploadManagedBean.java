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
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import otrasclases.cluster;
import sessionbeans.ProcesamientoSBLocal;

@ManagedBean
@SessionScoped

public class FileUploadManagedBean {

    @EJB
    private ProcesamientoSBLocal procesamientoSB;

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
        obtenerDatos(this.file);
        // Add message
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(obtenerDatos(this.file)));
    }

//funcion para convertir datos en cadena de strings
    ArrayList<String> nombreGen = new ArrayList<>();
    ArrayList<String> numeroCluster = new ArrayList<>();
    ArrayList<String> clusterUnico = new ArrayList<>();
    ArrayList<cluster> genesTotales = new ArrayList<>();

    private String obtenerDatos(UploadedFile archivo) {

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            InputStream input = archivo.getInputstream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            nombreGen = new ArrayList<>();
            clusterUnico = new ArrayList<>();
            boolean archivoCorrecto = false;
            while ((line = br.readLine()) != null) {
                String linea[] = line.split(",");
                if (linea.length == 2) {
                    //Es decir gen + cluster
                    //System.out.println("Largo linea: " + linea.length);
                    //System.out.println("parte 0: " + linea[0]);
                    //System.out.println("parte 1: " + linea[1]);
                    nombreGen.add(linea[0]);
                    numeroCluster.add(linea[1]);
                    archivoCorrecto = true;
                } else {
                    archivoCorrecto = false;
                    System.out.println("Archivo incorrecto - Generar error");
                }
            }
            //Ver cuantos clusters hay
            if (archivoCorrecto == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File Upload Success"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File Upload Error"));
            }
            String grupo;
            for (int i = 0; i < numeroCluster.size(); i++) {
                if (!(clusterUnico.contains(numeroCluster.get(i)))) {
                    grupo = numeroCluster.get(i);
                    //System.out.println("Grupo: " + grupo);
                    clusterUnico.add(grupo);
                }
            }
            //Crear Clusters de genes - Clase cluster
            genesTotales = new ArrayList<>();
            for (int i = 0; i < clusterUnico.size(); i++) {
                //System.out.println("ClusterUnico.size: " + clusterUnico.size());
                ArrayList<String> genesPorCluster = new ArrayList<>();
                for (int j = 0; j < nombreGen.size(); j++) {
                    //System.out.println("nombreGen.size: " + nombreGen.size());
                    if (clusterUnico.get(i).equals(numeroCluster.get(j))) {
                        genesPorCluster.add(nombreGen.get(j));
                        //System.out.println("Nombre del gen: " + nombreGen.get(j) + " Cluster: " + clusterUnico.get(i));
                    }
                }
                cluster clusterTemporal = new cluster(parseInt(clusterUnico.get(i)), genesPorCluster);
                genesTotales.add(clusterTemporal);
            }

            for (int i = 0; i < clusterUnico.size(); i++) {
                System.out.println("Cluster: " + genesTotales.get(i).getId());
                for (int j = 0; j < genesTotales.get(i).getGenes().size(); j++) {
                    System.out.print("Gen: " + genesTotales.get(i).getGenes().get(j));
                }
            }
        } catch (IOException ex) {
        }
        return "=D";
    }

    private void calcularIndiceTBK() {
        if (genesTotales.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe Cargar un archivo"));
        }
        else {
            for (int i = 0; i < clusterUnico.size(); i++) {
                //System.out.println("Cluster: " + genesTotales.get(i).getId());
                for (int j = 0; j < genesTotales.get(i).getGenes().size(); j++) {
                    float proc;
                    proc = procesamientoSB.CoreApp(genesTotales.get(i).getGenes(), 0);
                }
            }
        }
    }
        
    private void calcularIndiceWp() {
        if (genesTotales.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe Cargar un archivo"));
        } else {
            for (int i = 0; i < clusterUnico.size(); i++) {
                //System.out.println("Cluster: " + genesTotales.get(i).getId());
                for (int j = 0; j < genesTotales.get(i).getGenes().size(); j++) {
                    float proc;
                    proc = procesamientoSB.CoreApp(genesTotales.get(i).getGenes(), 1);
                }
            }
        }
    }

    private void calcularIndiceLC() {
        if (genesTotales.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe Cargar un archivo"));
        } 
        else {
            for (int i = 0; i < clusterUnico.size(); i++) {
                //System.out.println("Cluster: " + genesTotales.get(i).getId());
                for (int j = 0; j < genesTotales.get(i).getGenes().size(); j++) {
                    float proc;
                    proc = procesamientoSB.CoreApp(genesTotales.get(i).getGenes(), 2);
                }
            }
        }
    }
}
