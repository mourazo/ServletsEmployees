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
@WebServlet(name = "ServletMostrarUltimoInicio", urlPatterns = {"/ServletMostrarUltimoInicio"})
public class ServletMostrarUltimoInicio extends HttpServlet {
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
          String empleado = request.getParameter("nombre");
          Empleado emple = new Empleado(empleado);
           try (PrintWriter out = response.getWriter()) {
           List<Historial> his = incidenciasEJB.findMaxHistorialEmpleado(emple);
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletMostrarUltimoInicio</title>");            
            out.println("</head>");
            out.println("<body>");         
            
           int maxId = incidenciasEJB.findMaxHistorial();
         
         
           
           if(!incidenciasEJB.existeEmpleado(emple)){
               
                out.println("<p>No existe el empleado de destino "+ empleado+"</p>");
                
                  out.println("<form action=\"index.jsp\" method=\"POST\">"
                    + "Volver a la pagina principal  "
                    + "<input type=\"submit\" name=\"volver\" value=\"VOLVER\" />"
                    + "</form>");
           }else{
          
               out.println("ULTIMO INICIO DE SESIÓN DEL EMPLEADO "+empleado.toUpperCase() );
          for(int i=0; i<maxId;i++){

                out.print("<p><b>ID: </b>" + 
                       his.get(i).getIdevento() +
                        ", <b>Fecha hora: </b>" + 
                        his.get(i).getFechahora()+
                        ", <b>Tipo: </b>" +
                        his.get(i).getTipo()  +                     
                         " <b>Empleado: </b>" +               
                        his.get(i).getEmpleado().getNombreusuario() +"</p><br>");
                
            }
                  out.println("<form action=\"index.jsp\" method=\"POST\">"
                    + "Volver a la pagina principal  "
                    + "<input type=\"submit\" name=\"volver\" value=\"VOLVER\" />"
                    + "</form>");
           }
          
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
