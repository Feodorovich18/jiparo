
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.jiparo_1.jiparo.logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Holiiiiiiiiiiii</h1>
        
        <%List<Usuario> listaUsuario = (List) request.getSession().getAttribute("nombre_set");
        
        for(Usuario u: listaUsuario){
        %> 
            <!--HTML-->
            <p>Nombre: <%u.getNombreCompleto();%></p>

        
        <%    
        }
        
        %>
    </body>
</html>
