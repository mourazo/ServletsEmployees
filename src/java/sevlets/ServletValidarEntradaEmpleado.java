/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevlets;

import beans.IncidenciasEJB;
import clasesIncidencias.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ServletValidarEntradaEmpleado", urlPatterns = {"/ServletValidarEntradaEmpleado"})
public class ServletValidarEntradaEmpleado extends HttpServlet {
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
            out.println("<title>ServletValidarEntrada</title>");            
            out.println("</head>");
            out.println("<body>");
            String nombre = request.getParameter("nombre").trim();
            String password = request.getParameter("password").trim();

            
            Empleado empl = new Empleado(nombre,password);
                if(!incidenciasEJB.existeEmpleado(empl))
            {
                out.println("No existe el usuario ");
                
           } else if(!incidenciasEJB.obtenerPassword(empl).equals(password)){
               
                out.println("La contraseña introducida: " +password+" para el usuario " +nombre + " es incorrecta");
                
             
            }else{
               out.println("Entrada de " +nombre + " validada correctamente!");
                
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
