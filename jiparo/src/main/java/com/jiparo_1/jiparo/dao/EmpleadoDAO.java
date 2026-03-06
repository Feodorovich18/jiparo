
package com.jiparo_1.jiparo.dao;

import com.jiparo_1.jiparo.logica.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.*;


public class EmpleadoDAO {
    
    Connection conn;
    
    // Cadena de conexión (mejor como constantes)
    private static final String URL = "jdbc:postgresql://localhost:5432/jiparo";
    private static final String USUARIO = "postgres";
    private static final String CONTRASEÑA = "Noexiste18";
    
    //*****************************************
    private void conectar() {
        try {
            // Cargar el driver explícitamente
            Class.forName("org.postgresql.Driver");
            
            conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            
            if (conn != null) {
                System.out.println("✅ Conexión exitosa a PostgreSQL");
                // NO CIERRES AQUÍ, la necesitarás después
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver no encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //***************************************
    public void crearEmpleado(String nombre, String apellido_paterno, 
                              String apellido_materno, String email) {
        try {
            this.conectar();
            
            // Verificar que la conexión está abierta
            if (conn == null || conn.isClosed()) {
                System.out.println("❌ La conexión no está disponible");
                return;
            }
            
            // Insertar en BD
            String sql = "INSERT INTO empleado(nombre, apellido_paterno, apellido_materno, email, salario_base) VALUES (?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido_paterno);
            stmt.setString(3, apellido_materno);
            stmt.setString(4, email);
            stmt.setDouble(5, 10.0);
            
            int resultado = stmt.executeUpdate();  // executeUpdate() para INSERT/UPDATE/DELETE
            
            if (resultado > 0) {
                System.out.println("✅ Empleado creado exitosamente");
            }
            
            // Cerrar recursos
            stmt.close();
            conn.close();  // AHORA sí cierra, después de usarla
            
        } catch (SQLException e) {
            System.out.println("❌ Error al crear empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}