/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Kevin
 */
@Named(value = "fileDownloadView")
@RequestScoped
public class FileDownloadView implements Serializable{

    private static final long serialVersionUID = 1L;

    public FileDownloadView() {

    }
    public void downloadFileWP() throws IOException
   {
      File file = new File("F:\\WuPalmer.txt");
      InputStream fis = new FileInputStream(file);
      byte[] buf = new byte[1024];
      int offset = 0;
      int numRead = 0;
      while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
      {
        offset += numRead;
      }
      fis.close();
      HttpServletResponse response =
         (HttpServletResponse) FacesContext.getCurrentInstance()
        .getExternalContext().getResponse();

     response.setContentType("application/octet-stream");
     response.setHeader("Content-Disposition", "attachment;filename=Results.txt");
     response.getOutputStream().write(buf);
     response.getOutputStream().flush();
     response.getOutputStream().close();
     FacesContext.getCurrentInstance().responseComplete();
   }
    public void downloadFileLC() throws IOException
   {
      File file = new File("F:\\LeacockChodorow.txt");
      InputStream fis = new FileInputStream(file);
      byte[] buf = new byte[1024];
      int offset = 0;
      int numRead = 0;
      while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
      {
        offset += numRead;
      }
      fis.close();
      HttpServletResponse response =
         (HttpServletResponse) FacesContext.getCurrentInstance()
        .getExternalContext().getResponse();

     response.setContentType("application/octet-stream");
     response.setHeader("Content-Disposition", "attachment;filename=Results.txt");
     response.getOutputStream().write(buf);
     response.getOutputStream().flush();
     response.getOutputStream().close();
     FacesContext.getCurrentInstance().responseComplete();
   }
    public void downloadFileTBK() throws IOException
   {
      File file = new File("F:\\TBK.txt");
      InputStream fis = new FileInputStream(file);
      byte[] buf = new byte[1024];
      int offset = 0;
      int numRead = 0;
      while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
      {
        offset += numRead;
      }
      fis.close();
      HttpServletResponse response =
         (HttpServletResponse) FacesContext.getCurrentInstance()
        .getExternalContext().getResponse();

     response.setContentType("application/octet-stream");
     response.setHeader("Content-Disposition", "attachment;filename=Results.txt");
     response.getOutputStream().write(buf);
     response.getOutputStream().flush();
     response.getOutputStream().close();
     FacesContext.getCurrentInstance().responseComplete();
   }
}
