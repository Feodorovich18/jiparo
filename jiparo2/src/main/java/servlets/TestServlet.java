package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@WebServlet("/api/test-conexion")
public class TestServlet extends HttpServlet {

    private Gson gson = new Gson();

    // Configuración de la base de datos - ¡CAMBIA ESTOS VALORES!
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "jiparo";
    private static final String DB_USER = "postgres"; // o app_contabilidad
    private static final String DB_PASSWORD = "Noexiste18"; // Cambia esto

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        JsonObject response = new JsonObject();

        try {
            // 1. Cargar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
            response.addProperty("driver", "Cargado correctamente");

            // 2. Intentar conectar
            String url = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

            Properties props = new Properties();
            props.setProperty("user", DB_USER);
            props.setProperty("password", DB_PASSWORD);
            props.setProperty("ssl", "false");

            long startTime = System.currentTimeMillis();

            try (Connection conn = DriverManager.getConnection(url, props)) {
                long endTime = System.currentTimeMillis();

                response.addProperty("conexion", "Exitosa");
                response.addProperty("tiempo_ms", endTime - startTime);
                response.addProperty("url", url);
                response.addProperty("basedatos", DB_NAME);

                // 3. Probar una consulta simple
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT 1 as prueba")) {

                    if (rs.next()) {
                        response.addProperty("consulta", "Funciona");
                        response.addProperty("resultado_prueba", rs.getInt("prueba"));
                    }
                }

                // 4. Obtener información de PostgreSQL
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT version()")) {

                    if (rs.next()) {
                        response.addProperty("version_postgresql", rs.getString(1).substring(0, 50) + "...");
                    }
                }

                response.addProperty("estado", "OK");
            }

        } catch (ClassNotFoundException e) {
            response.addProperty("estado", "ERROR");
            response.addProperty("error", "Driver no encontrado: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            response.addProperty("estado", "ERROR");
            response.addProperty("error", e.getMessage());
            response.addProperty("tipo_error", e.getClass().getName());

            // Mensajes más amigables para errores comunes
            if (e.getMessage().contains("refused")) {
                response.addProperty("solucion", "PostgreSQL no está corriendo. Ejecuta: sudo systemctl start postgresql");
            } else if (e.getMessage().contains("authentication")) {
                response.addProperty("solucion", "Usuario o contraseña incorrectos. Verifica DB_USER y DB_PASSWORD");
            } else if (e.getMessage().contains("database")) {
                response.addProperty("solucion", "La base de datos '" + DB_NAME + "' no existe. Créala con: sudo -u postgres createdb " + DB_NAME);
            }

            e.printStackTrace();
        }

        // Enviar respuesta
        String jsonResponse = gson.toJson(response);
        out.print(jsonResponse);
        out.flush();
    }
}