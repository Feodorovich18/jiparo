
package com.jiparo_1.jiparo.servlets;




import com.jiparo_1.jiparo.dao.UsuarioDAO;
import com.jiparo_1.jiparo.logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtener parámetros del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validar que los campos no estén vacíos
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            response.sendRedirect("login.jsp?error=1");
            return;
        }
        
        try {
            // Crear instancia del DAO para consultar la BD
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.validarLogin(username, password);
            
            if (usuario != null) {
      
                 // Redirigir a página principal
                response.sendRedirect("dashboard_admin.jsp");
                
            } else {
                // Login fallido - usuario o contraseña incorrectos
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (Exception e) {
            // Error en la base de datos
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=2");
        }
    }
}