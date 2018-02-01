package ups.edu.ec.gisab.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author Vishal.Gilbile
 */
public class retriveImage extends HttpServlet {

    Connection con;
    CallableStatement stat;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("image/jpeg");
        //PrintWriter out = response.getWriter();
        Blob ePhoto = null;
        byte[] rawBytes = null;
        ServletOutputStream out = response.getOutputStream();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            //byte[] rawBytes = null;
            String id = request.getQueryString();
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //con = DriverManager.getConnection("jdbc:odbc:myCon", "", "");
            //stat = con.prepareCall("{call prc_getEPhoto(?)}");
           // stat.setInt(1, Integer.parseInt(id));
            //ResultSet rs = stat.executeQuery();
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elgg", "root", "");
           
           // Statement stm;
           // stm = con.createStatement();
            String strSql = "select content_video from elgg_contenidotemp where content_Codigo=?";
            PreparedStatement ps = con.prepareStatement(strSql);
            ps.setString(1, id);
            
            ResultSet rst = ps.executeQuery(strSql);
            
            rst.next();
			// out.println("<html>");
			// out.println("<head>");
			// out.println("<title>Servlet retriveImage</title>");
			// out.println("</head>");
			// out.println("<body>");
            rawBytes = rst.getBytes(1);
            out.write(rawBytes);
			// out.println("</body>");
			// out.println("</html>");
            out.flush();
            stat.close();

        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            try {
                con.close();
                out.close();
            } catch (Exception ex) {
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}