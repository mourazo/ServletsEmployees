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
@WebServlet(name = "ServletObtenerIncidenciaDeEmpleado", urlPatterns = {"/ServletObtenerIncidenciaDeEmpleado"})
public class ServletObtenerIncidenciaDeEmpleado extends HttpServlet {
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
         try (PrintWriter out = response.getWriter()) {
             List<Incidencia> in = incidenciasEJB.findIncidenciasByEmpleadoDestino(empleado);
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletObtenerIncidenciaDeEmpleado</title>");            
            out.println("</head>");
            out.println("<body>");         
            
           int maxId = incidenciasEJB.findMaxIncidencia();
           Empleado empl = new Empleado(empleado);
           
           if(!incidenciasEJB.existeEmpleado(empl)){
               
                out.println("<p>No existe el empleado de destino "+ empleado+"</p>");
                
                  out.println("<form action=\"index.jsp\" method=\"POST\">"
                    + "Volver a la pagina principal  "
                    + "<input type=\"submit\" name=\"volver\" value=\"VOLVER\" />"
                    + "</form>");
           }else{
           out.println("<h1>Listado de incidencias del empleado " +empleado +" </h1>");
            for(int i=0; i<maxId;i++){
                out.print("<b>ID: </b>" + 
                        in.get(i).getIdincidencia() +
                        ", <b>Fecha y Hora: </b>" + 
                        in.get(i).getFechahora() +
                        ", <b>Detalle: </b>" +
                        in.get(i).getDetalle()+
                        ", <b>Tipo: </b>" +
                        in.get(i).getTipo()+
                        ", <b>Empleado Destino: </b>" +
                         in.get(i).getOrigen().getNombreusuario() +"<br>");
            }
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
