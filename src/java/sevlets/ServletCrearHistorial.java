/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevlets;

import beans.IncidenciasEJB;
import clasesIncidencias.Empleado;
import clasesIncidencias.Historial;
import clasesIncidencias.Incidencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
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
@WebServlet(name = "ServletCrearHistorial", urlPatterns = {"/ServletCrearHistorial"})
public class ServletCrearHistorial extends HttpServlet {
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
            /* TODO output your page here. You may use following sample code. */
             out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crearHistorial</title>");            
            out.println("</head>");
            out.println("<body>");
            // Recogemos los datos del formulario
         
            //int idevento =Integer.parseInt(request.getParameter("idevento"));       
            String tipo = request.getParameter("tipo");
            String fechahora = request.getParameter("fechahora");
            String empleado = request.getParameter("empleado");
            

            
            Empleado empl = new Empleado(empleado);
            
            int idevento = incidenciasEJB.findMaxIdHistorial()+1;
            
            if(!incidenciasEJB.existeEmpleado(empl)){
                out.println("No existe el empleado");  
                 
            }else if(!tipo.equals("U") && !tipo.equals("I") && !tipo.equals("C")){
                out.println("Las incidencias solo pueden ser I U o C");
               
            }else{
                Historial his = new Historial(idevento, tipo, fechahora);              
                his.setEmpleado(empl);
      
                if (incidenciasEJB.insertarHistorial(his) ) {
                    out.println("Incidencia creada");
                } else {
                    out.println("Ya existe una incidencia con ese id.");
                }
            }
            out.println("<form action=\"index.jsp\" method=\"POST\">"
                    + "Volver a la pagina inicial  "
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
