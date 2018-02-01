package ups.edu.ec.gisab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect 
{
	 public Connection conexion;
	    public Statement sentencia;
	    public ResultSet resultado;

	    Statement stmt = null;
	    //jdbc:mysql://localhost:3306/elgg
	    public final String url = "jdbc:mysql://localhost:3306/"; //por ser el puerto por defecto
	    public final String root = "root";
	    public final String password = "";//<----Ingresar la clase de root
	    public final String bd = "elgg";
	    //String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

	    public Connection ConectarBasedeDatos() 
	    {
	        System.out.print("ConectarBasedeDatos");
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("----------------Intentando conectar la base de datos----------------");
	            conexion = DriverManager.getConnection(url + bd, root, password);
	            if (conexion != null) {
	                System.out.println("Conexion a la base de datos " + bd + "  OK\n");
	            }
	        } catch (ClassNotFoundException | SQLException ex) {
	            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Aplicacion", JOptionPane.ERROR_MESSAGE);
	        }
	        return conexion;
	    }

	    public void DesconectarBasedeDatos() {
	        try {
	            if (conexion != null) {
	                if (sentencia != null) {
	                    sentencia.close();
	                }
	                conexion.close();
	            }
	        } catch (SQLException ex) {
	           // JOptionPane.showMessageDialog(null, ex.getMessage(), "Aplicacion", JOptionPane.ERROR_MESSAGE);
	            System.exit(1);
	        }
	    }

	    public Connection getConnection() {
	        return conexion;
	    }
}
