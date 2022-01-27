package clasesIncidencias;

import clasesIncidencias.Historial;
import clasesIncidencias.Incidencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-04T12:46:35")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> nombreusuario;
    public static volatile CollectionAttribute<Empleado, Incidencia> incidenciaCollection1;
    public static volatile SingularAttribute<Empleado, String> password;
    public static volatile CollectionAttribute<Empleado, Historial> historialCollection;
    public static volatile SingularAttribute<Empleado, String> nombrecompleto;
    public static volatile CollectionAttribute<Empleado, Incidencia> incidenciaCollection;
    public static volatile SingularAttribute<Empleado, String> telefono;

}