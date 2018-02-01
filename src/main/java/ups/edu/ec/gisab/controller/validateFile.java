package ups.edu.ec.gisab.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ManagedBean
@ViewScoped
public class validateFile {
	private Part uploadedFile;
	private String fileContent;

	public void validar(FacesContext ctx, UIComponent comp, Object value) 
	{
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		
		//System.out.println(file.getSubmittedFileName());
		//if (file.getSize() > 2024) 
	//	{
		//	System.out.println("-------------------");
		//	msgs.add(new FacesMessage("Sobrepasa limite peso"));
		//}
		if (!"text/plain".equals(file.getContentType())) 
		{
			System.out.println("************************");
			//msgs.add(new FacesMessage("not a text file"));
		}
		if (!msgs.isEmpty()) 
		{
			throw new ValidatorException(msgs);
		}
	}

	public void uploadFile() {
		try 
		{
			
			fileContent = new Scanner(uploadedFile.getInputStream()).useDelimiter("\\A").next();
			System.out.println("********** ARCHIVO CARGADO CON EXITO" + fileContent);
		} 
		catch (IOException e)
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error uploading file", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFileContent() {
		return fileContent;
	}
}
