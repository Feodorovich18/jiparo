
package com.jiparo_1.jiparo.servlets;

import com.jiparo_1.jiparo.logica.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;



@WebServlet(name = "PruebaServlet", urlPatterns = {"/PruebaServlet"})
public class PruebaServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        response.setContentType("text/html;charset=UTF-8");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Isaac", "Villanueva"));
        listaUsuarios.add(new Usuario("Johan", "Isidoro"));
        
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("nombre_set", listaUsuarios);
        
        response.sendRedirect("prueba_2");
        
        processRequest(request, response);
    }

   
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
