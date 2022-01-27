/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevlets;

import beans.IncidenciasEJB;
import clasesIncidencias.Empleado;
import clasesIncidencias.Incidencia;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Date.parse;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alvar
 */
@WebServlet(name = "ServletObtenerIncidencia", urlPatterns = {"/ServletObtenerIncidencia"})
public class ServletObtenerIncidencia extends HttpServlet {
@EJB
IncidenciasEJB incidenciasEJB;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          try (PrintWriter out = response.getWriter()) {
          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletObtenerIncidencia</title>");            
            out.println("</head>");
            out.println("<body>");        
            String idobtain = request.getParameter("id");
            
           int id = Integer.parseInt(idobtain);
           Incidencia in = new Incidencia(id);
           Incidencia in2 = new Incidencia();
            if (incidenciasEJB.findIncidenciaByID(in,in2)) {
                out.println("<p>ID: " + in2.getIdincidencia()+"</p>");
                out.println("<p>FECHA Y HORA: " + in2.getFechahora()+"<p>" );
                out.println("<p>TIPO: " + in2.getTipo()+"<p>");
                out.println("<p>DETALLE: " + in2.getDetalle() +"<p>");
                out.println("<p>ORIGEN: " + in2.getOrigen().getNombreusuario() +"<p>");
                out.println("<p>DESTINO: " + in2.getDestino().getNombreusuario() +"<p>");
            } else {
                out.println("No una incidencia con esa id");
            }
            out.println("<form action=\"index.jsp\" method=\"POST\">"
                    + "Volver a la pagina principal  "
                    + "<input type=\"submit\" name=\"volver\" value=\"VOLVER\" />"
                    + "</form>");
            out.println("</body>");
            out.println("</html>");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
