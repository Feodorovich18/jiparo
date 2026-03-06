
package com.jiparo_1.jiparo.dao;



import com.jiparo_1.jiparo.logica.Usuario;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UsuarioDAO {
    // Configuración de conexión a PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/jiparo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Noexiste18";
    
    // Constructor - cargar driver
    public UsuarioDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando driver PostgreSQL: " + e.getMessage());
        }
    }
    
    /**
     * Valida el login del usuario comparando credenciales con la BD
     * @param username Usuario ingresado
     * @param password Contraseña ingresada
     * @return Objeto Usuario si es válido, null si no
     */
    public Usuario validarLogin(String username, String password) throws SQLException {
        String sql = "SELECT username, email FROM usuario WHERE username = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Usar PreparedStatement para evitar SQL Injection
            stmt.setString(1, username);
            stmt.setString(2, password);  // Sin hashear, compara directamente
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Usuario encontrado - LA BD YA VALIDÓ LAS CREDENCIALES
                    Usuario usuario = new Usuario();
                    
                    usuario.setUsername(rs.getString("username"));
                    usuario.setEmail(rs.getString("email"));
                    
                    System.out.println("✓ Login exitoso para usuario: " + username);
                    return usuario;
                }
            }
        }
        
        // Usuario no encontrado o contraseña incorrecta
        System.out.println("✗ Login fallido para usuario: " + username);
        return null;
    }
    
   
}