
package com.jiparo_1.jiparo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
    }

  
    //***************************************************************************
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
        
        
        processRequest(request, response);
    }

    
    
    //****************************************************************************
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido_paterno = request.getParameter("apellido_paterno");
        String apellido_materno = request.getParameter("apellido_materno");
        String email = request.getParameter("email");
        String telelfono = request.getParameter("telefono");
        
        //String fecha_ingreso = request.getParameter("fecha_ingreso");
        
        
        /*
        boolean contabilidad = request.getParameter("contabilidad");
        boolean nomina = request.getParameter("nomina");
        boolean inventario = request.getParameter("inventario");
        boolean facturacion = request.getParameter("facturacion");
        boolean reportes = request.getParameter("reportes");
        boolean administracion = request.getParameter("administracion");
        
        
        System.out.println(usuario);
        System.out.println(contrasenia);
        */
        
        
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
