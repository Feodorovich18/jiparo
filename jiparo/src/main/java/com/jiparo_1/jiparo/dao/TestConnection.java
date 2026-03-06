

/*
//TestConnection

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
          
            
            // Intentar conectar
            String url = "jdbc:postgresql://localhost:5432/jiparo";
            String usuario = "postgres";
            String contraseña = "Noexiste18";
            
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            
            if (conn != null) {
                System.out.println("✅ Conexión exitosa a PostgreSQL");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

*/
