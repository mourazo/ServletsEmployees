/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import clasesIncidencias.Empleado;
import clasesIncidencias.Historial;
import clasesIncidencias.Incidencia;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author alvar
 */
@Stateless
public class IncidenciasEJB {
   @PersistenceUnit
    EntityManagerFactory emf;
    public List findAllEmpleados() {
        return emf.createEntityManager().createNamedQuery("Empleado.findAll").getResultList();
    }
  public boolean insertarEmpleado(Empleado empl) {
        if (!existeEmpleado(empl)) {
            EntityManager em = emf.createEntityManager();
            em.persist(empl);
            em.flush();   
            em.close();
            return true;
        }
        return false;
    }
        public boolean existeEmpleado(Empleado empl) {
        EntityManager em = emf.createEntityManager();
        Empleado encontrado = em.find(Empleado.class, empl.getNombreusuario());
        em.close();
        return encontrado != null;
    }
        public boolean updateContraseña(Empleado empl) {
        EntityManager em = emf.createEntityManager();
        Empleado e = em.find(Empleado.class, empl.getNombreusuario());
        if (e!= null) {
            e.setPassword(empl.getPassword());
            em.persist(e);
            em.close();
            return true;
        }
        return false;
    }
         public String obtenerPassword(Empleado empl) {
             
        EntityManager em = emf.createEntityManager();
        Empleado e = em.find(Empleado.class, empl.getNombreusuario());
        String pass = e.getPassword();
        return pass;
    }
        public boolean eliminarEmpleado(Empleado empl) {
        EntityManager em = emf.createEntityManager();
        Empleado e = em.find(Empleado.class, empl.getNombreusuario());
        if (e != null) {
            em.remove(e);
            em.close();
            return true;
        }
        return false;
    }
        public boolean updateEmpleado(Empleado empl) {
        EntityManager em = emf.createEntityManager();
        Empleado e = em.find(Empleado.class, empl.getNombreusuario());
        if (e!= null) {
            e.setNombrecompleto(empl.getNombrecompleto());
            e.setPassword(empl.getPassword());
            e.setTelefono(empl.getTelefono());
            em.persist(e);
            em.close();
            return true;
        }
        return false;
        }
        
        public List findAllIncidencias() {
        return emf.createEntityManager().createNamedQuery("Incidencia.findAll").getResultList();
    }
        
    public boolean findIncidenciaByID(Incidencia in, Incidencia in2) {
        EntityManager em = emf.createEntityManager();
        Incidencia inc = em.find(Incidencia.class, in.getIdincidencia());
        if (inc!= null) {
            in2.setIdincidencia(in.getIdincidencia());
            in2.setFechahora(inc.getFechahora());
            in2.setOrigen(inc.getOrigen() );
            in2.setDestino(inc.getDestino());
            in2.setDetalle(inc.getDetalle());
            in2.setTipo(inc.getTipo());
            return true;
        }
            return false;
        }
      public boolean insertarIncidencia(Incidencia in) {
        if (!existeIncidencia(in)) {
            EntityManager em = emf.createEntityManager();
            em.persist(in);
            //        em.flush();   Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }
        public boolean existeIncidencia(Incidencia in) {
        EntityManager em = emf.createEntityManager();
        Incidencia encontrada = em.find(Incidencia.class, in.getIdincidencia());
        em.close();
        return encontrada != null;
    }
       public int findMaxIncidencia() {
        EntityManager em = emf.createEntityManager();
        int maxid = (Integer) em.createNativeQuery("SELECT max(idincidencia) from Incidencia").getSingleResult();
        return maxid;
    }
        public List findIncidenciasByEmpleadoDestino(String empleado) {
        EntityManager emfempl = emf.createEntityManager();
        Empleado empl = emfempl.find(Empleado.class, empleado);
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Incidencia i WHERE i.destino= :empleado");
        q.setParameter("empleado", empl);
        List incidenciasEmpleadoDestino = q.getResultList();
        return incidenciasEmpleadoDestino;
    }
        
         public List findIncidenciasByEmpleadoOrigen(String empleado) {
        EntityManager emfempl = emf.createEntityManager();
        Empleado empl = emfempl.find(Empleado.class, empleado);
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Incidencia i WHERE i.origen= :empleado");
        q.setParameter("empleado", empl);
        List incidenciasEmpleadoOrigen = q.getResultList();
        return incidenciasEmpleadoOrigen;
    }
         
        public int findMaxHistorial() {
        EntityManager em = emf.createEntityManager();
        int maxid = (Integer) em.createNativeQuery("SELECT max(idevento) from Historial").getSingleResult();
        return maxid;
    }
        public List findAllHistorial() {
        return emf.createEntityManager().createNamedQuery("Historial.findAll").getResultList();
    }    
        
         public List findMaxHistorialEmpleado(Empleado emple) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT h FROM Historial h WHERE h.empleado = :empleado and h.idevento= (SELECT max(h.idevento) FROM Historial h WHERE h.empleado = :empleado)");
        q.setParameter("empleado", emple);
        List incidenciasEmpleadoOrigen = q.getResultList();
        return incidenciasEmpleadoOrigen;
      }
         
         public List findRankingHistorial() {
        EntityManager em = emf.createEntityManager();     
        // Query q = em.createNativeQuery("SELECT empleado, COUNT(*) as cuantos from Historial where tipo ='U' group by empleado order by cuantos DESC");
         Query q = em.createQuery("SELECT h from Historial h where h.tipo= :tipo group by h.empleado order by count(h.empleado) DESC");
         q.setParameter("tipo", "U");
        List rank = q.getResultList();
        return rank;
      }
         
         public boolean insertarHistorial(Historial his) {
        if (!existeHistorial(his)) {
            EntityManager em = emf.createEntityManager();
            em.persist(his);
            //        em.flush();   Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }
         
        public boolean existeHistorial(Historial his) {
        EntityManager em = emf.createEntityManager();
        Historial encontrado = em.find(Historial.class, his.getIdevento());
        em.close();
        return encontrado != null;
    }
      public int findMaxIdHistorial() {
        EntityManager em = emf.createEntityManager();
        int maxid = (Integer) em.createNativeQuery("SELECT max(idevento) from Historial").getSingleResult();
        return maxid;
    }

}
        

