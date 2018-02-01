package ups.edu.ec.gisab.controller;

import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ups.edu.ec.gisab.dao.Connect;

@ManagedBean(name = "tableBean")
@SessionScoped
public class TableBean 
{
	private String content_Descripcion;
    private String content_Titulo;
    private String imageLength;
    private static final Connect database = new Connect();
    private static Connection con = database.ConectarBasedeDatos();
    
    
    public String getContent_Descripcion() 
    {
		return content_Descripcion;
	}
	
    public void setContent_Descripcion(String content_Descripcion) 
	{
		this.content_Descripcion = content_Descripcion;
	}
	
	public String getContent_Titulo() 
	{
		return content_Titulo;
	}
	
	public void setContent_Titulo(String content_Titulo) 
	{
		this.content_Titulo = content_Titulo;
	}
    
	public String getImageLength() {
        return imageLength;
    }
 
    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    
    /*
     *   con = database.ConectarBasedeDatos();
        Statement stm;
        stm = con.createStatement();
        String sql = "select * from recursos";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<Cuento> customerList = new ArrayList<>();

        while (rst.next()) 
        {
            Cuento customer = new Cuento(
                    rst.getInt("idrecurso"), 
                    rst.getString("titulorecurso"), 
                    rst.getString("autorrecurso"), 
                    rst.getBytes("imgrecurso"), 
                    rst.getBytes("filerecurso")
            );
            
            customerList.add(customer);
        }
        return customerList;
    }
     */
    /**
     * REVISARRR CONECCION 
     * @return
     */
    /*
    public List<TableBean> getAllImage() 
    {
        List<TableBean> imageInfo = new ArrayList<TableBean>();
        con = database.ConectarBasedeDatos();
        Statement stm;
        try {
            stmt = con.createStatement();
            String strSql = "select image_id,Image_name from upload_image order by image_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBean tbl = new TableBean();
                tbl.setImageID(rs.getString("image_id"));
                tbl.setImageName(rs.getString("Image_name"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    */
}
