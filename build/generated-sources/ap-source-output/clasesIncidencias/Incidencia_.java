package clasesIncidencias;

import clasesIncidencias.Empleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-04T12:46:35")
@StaticMetamodel(Incidencia.class)
public class Incidencia_ { 

    public static volatile SingularAttribute<Incidencia, String> fechahora;
    public static volatile SingularAttribute<Incidencia, String> tipo;
    public static volatile SingularAttribute<Incidencia, Integer> idincidencia;
    public static volatile SingularAttribute<Incidencia, Empleado> origen;
    public static volatile SingularAttribute<Incidencia, Empleado> destino;
    public static volatile SingularAttribute<Incidencia, String> detalle;

}