package ups.edu.ec.gisab.controller;

//retive image blob using servlet and display it at jsop 
//mine video mp4 
// video/mpeg


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import ups.edu.ec.gisab.dao.ContenidoDao;
import ups.edu.ec.gisab.dao.VideoDAO;
import ups.edu.ec.gisab.modelo.ContenidoTemporal;
import ups.edu.ec.gisab.modelo.Video;
import ups.edu.ec.gisab.utilidades.Constants;
import ups.edu.ec.gisab.utilidades.Utils;

@ManagedBean
@ViewScoped
public class FileUploadBean implements Serializable
{
	@Inject
	private VideoDAO pdao;
	
	private Video ct = null;

	private static final long serialVersionUID = 1L;
	
	private Part uploadFile;
	//private Part file;
	private String text;
	
	//private String folder = "/opt/wildfly-10/contentGisab";

	@PostConstruct
	public void init() {
		ct = new Video();
	}
	
	public Part getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(Part uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Video getCt() {
		return ct;
	}

	public void setCt(Video ct) {
		this.ct = ct;
	}

	public String uploadFile() throws IOException 
	{
		InputStream inputStream = null;
		
        
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        
        
        boolean file1Success = false;
        
        if (uploadFile.getSize() > 0)
        {
        	ct.setVid_name("Metodo Anticonceptivo");
        	ct.setVid_descripcion("Esta es una prueba de carga de contenido sobre Metodos Anticonceptivos");
        	
            String fileName = Utils.getFileNameFromPart(uploadFile);
            /**
            * destination where the file will be uploaded
            */
            
            String pth="/home/chriss/eclipse-workspace/SisGisab/src/main/webapp/resources";
            
            System.out.println("PATH --->  " + path);
            System.out.println("File.separator  --->  " + File.separator );
            System.out.println("fileName  --->  " + fileName );
            
            
            File outputFile = new File(pth + File.separator + fileName);
            
            String rutaComplete = pth+File.separator+fileName;
            
            System.out.println("RUTAAA COMPLETA -------> "+rutaComplete);
            
            System.out.println("output ---> "+outputFile);
            
            String ruta = "resources/"+fileName;
            
            inputStream = uploadFile.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            
            byte[] buffer = new byte[Constants.BUFFER_SIZE];
            int bytesRead = 0;
          
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
           if (inputStream != null) 
            {
                inputStream.close();
                ct.setVid_path(ruta);
                pdao.insertarContenido(ct);
            }
            file1Success = true;
        }
        
        if (file1Success)
        {
            System.out.println("File uploaded to : ");
            /**
            * set the success message when the file upload is successful
            */
            setText("File successfully uploaded to ");
           
            
        } else {
            /**
            * set the error message when error occurs during the file upload
            */
        	setText("Error, select atleast one file!");
        }
        /**
        * return to the same view
        */
        return null;
    }
}


	
	/*
	public void setFile(Part file) 
	{
		System.out.println("Got file ...");
		this.file = file;
		
		
		System.out.println(file+" NOMBREEE ");
				
		//file
		if (null != file) {
			System.out.println("... and trying to read it ...");
			try 
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String string = reader.readLine();
				StringBuilder builder = new StringBuilder();
				while (string != null) 
				{
					builder.append(string);
					string = reader.readLine();
				}
				text = builder.toString();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace(System.err);
			}
			System.out.println("... completed reading file.");
		} 
		else 
		{
			System.out.println("... but its null.");
		}
	}
*/


