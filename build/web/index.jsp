<%-- 
    Document   : index
    Created on : 02-may-2020, 19:39:08
    Author     : alvar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INDEX JSP</title>
    </head>
    <body>
           <body>
        <h1>ACTIVIDAD</h1>
        <h2>----------------------------------------------------</h2>
        <h2>EMPLEADOS</h2>
        <form action="gestionEmpleados.html" method="POST">Gestionar empleados
            <input type="submit" name="gestionEmpleados" value="GESTIONAR" />
         </form>
        <h2>-------------------------------------------------</h2>
            <h2>INCIDENCIAS</h2>
        <form action="gestionIncidencias.html" method="POST">Gestionar incidencias
            <input type="submit" name="gestionIncidencias" value="GESTIONAR" />
        </form>
        <h2>-------------------------------------------------</h2>
             <h2>HISTORIAL</h2>
        <form action="gestionHistorial.html" method="POST">Gestionar historial
            <input type="submit" name="gestionHistorial" value="GESTIONAR" />
        </form>
        <h2>-------------------------------------------------</h2>
    </body>
    </body>
</html>
