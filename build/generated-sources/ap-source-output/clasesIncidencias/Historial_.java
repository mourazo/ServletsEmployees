package clasesIncidencias;

import clasesIncidencias.Empleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-04T12:46:35")
@StaticMetamodel(Historial.class)
public class Historial_ { 

    public static volatile SingularAttribute<Historial, String> fechahora;
    public static volatile SingularAttribute<Historial, Integer> idevento;
    public static volatile SingularAttribute<Historial, String> tipo;
    public static volatile SingularAttribute<Historial, Empleado> empleado;

}