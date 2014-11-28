/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import java.io.BufferedReader;
import java.io.FileWriter;
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
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import otrasclases.cluster;
import sessionbeans.ProcesamientoSBLocal;

@ManagedBean
@SessionScoped

public class FileUploadManagedBean {

    @EJB
    private ProcesamientoSBLocal procesamientoSB;

    UploadedFile file;
    public String output;
    public boolean archivoCorrecto = false;
    private String[] selectedOption;

    public String[] getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String[] selectedOption) {
        this.selectedOption = selectedOption;
    }

    public boolean isArchivoCorrecto() {
        return archivoCorrecto;
    }

    public void setArchivoCorrecto(boolean archivoCorrecto) {
        this.archivoCorrecto = archivoCorrecto;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

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
            numeroCluster = new ArrayList<>();
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect Format File"));
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
            System.out.println("Error: " + ex);
        }
        return "=D";
    }

    public void calcularIndiceTBK() throws IOException {
        output = "";
        FileWriter fw = new FileWriter("F:\\TBK.txt");
        fw.write("TBKIndice\t|\tCluster");
        fw.write(System.lineSeparator());
        fw.write("----------------------------");
        fw.write(System.lineSeparator());
        double timeStart, timeStop;
        if (genesTotales.isEmpty() | archivoCorrecto == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("First Upload Your File"));
        } else {
            for (int i = 0; i < clusterUnico.size(); i++) {
                timeStart = System.currentTimeMillis();
                output = procesamientoSB.CoreApp(genesTotales.get(i).getGenes(), 0, genesTotales.get(i).getId());
                fw.write(output);
                timeStop = System.currentTimeMillis();
                double tiempoSegundos = (timeStop - timeStart) * 0.001;
                System.out.println("Tiempo iteración: " + tiempoSegundos + " segundos");
            }
        }
        fw.close();
    }

    public void calcularIndiceWp() throws IOException {
        output = "";
        double timeStart, timeStop;
        FileWriter fw = new FileWriter("F:\\WuPalmer.txt");
        fw.write("WuPalmerIndice\t|\tCluster");
        fw.write(System.lineSeparator());
        fw.write("----------------------------");
        fw.write(System.lineSeparator());
        if (genesTotales.isEmpty() | archivoCorrecto == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe Cargar un archivo"));
        } else {
            for (int i = 0; i < clusterUnico.size(); i++) {
                timeStart = System.currentTimeMillis();
                output = procesamientoSB.CoreApp(genesTotales.get(i).getGenes(), 1, genesTotales.get(i).getId());
                fw.write(output);
                fw.write(System.lineSeparator());
                System.out.println(output);
                timeStop = System.currentTimeMillis();
                double tiempoSegundos = (timeStop - timeStart) * 0.001;
                System.out.println("Tiempo Iteración: " + tiempoSegundos + " segundos");
            }
        }
        fw.close();
    }

    public void calcularIndiceLC() throws IOException {
        output = "";
        double timeStart, timeStop;
        FileWriter fw = new FileWriter("F:\\LeacockChodorow.txt");
        fw.write("LeacockChodorowIndice\t|\tCluster");
        fw.write(System.lineSeparator());
        fw.write("----------------------------");
        fw.write(System.lineSeparator());
        if (genesTotales.isEmpty() || archivoCorrecto == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe Cargar un archivo"));
        } else {
            for (int i = 0; i < clusterUnico.size(); i++) {
                timeStart = System.currentTimeMillis();
                output = procesamientoSB.CoreApp(genesTotales.get(i).getGenes(), 2, genesTotales.get(i).getId());
                fw.write(output);
                System.out.println(output);
                timeStop = System.currentTimeMillis();
                double tiempoSegundos = (timeStop - timeStart) * 0.001;
                System.out.println("Tiempo iteración: " + tiempoSegundos + " segundos");
            }
        }
        fw.close();
    }

    //Método para el CheckBox que quedó a medias.

    public void mostrarValores() throws IOException {
        System.out.println("Indices a calcular: ");
        for (int i = 0; i < selectedOption.length; i++) {
            if (selectedOption[i].equals("WP")) {
                System.out.println("Wu Palmer");
                calcularIndiceWp();
            }
            if (selectedOption[i].equals("LC")) {
                System.out.println("Leacock Chodorow");
                calcularIndiceLC();
            }
            if (selectedOption[i].equals("JC")) {
                System.out.println("Jean Conrath");
            }
            if (selectedOption[i].equals("TBK")) {
                System.out.println("T.B.K.");
                calcularIndiceTBK();
            }
            if (selectedOption[i].equals("L")) {
                System.out.println("Lin");
            }
            if (selectedOption[i].equals("R")) {
                System.out.println("Resnik");
            }
        }
    }
}
