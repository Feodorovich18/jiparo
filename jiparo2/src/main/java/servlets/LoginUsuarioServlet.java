package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.EmpresaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import model.Empresa;
import model.Usuario;
import dao.UsuarioDAO;

@WebServlet("/api/loginUsuarioServlet")
public class LoginUsuarioServlet extends HttpServlet {

    private Gson gson = new Gson();

    // Método para recibir datos del formulario (POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Configurar la respuesta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 2. Leer el JSON que viene del fetch
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String datosJSON = buffer.toString();
        System.out.println("📥 Datos recibidos: " + datosJSON);

        // 3. Convertir JSON a objeto Java
        Usuario usuario_a_comparar = gson.fromJson(datosJSON, Usuario.class);

        try {
            // Crear instancia del DAO para consultar la BD
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.validarLogin(usuario_a_comparar.getUsername(), usuario_a_comparar.getPassword());


            // Crear objeto JSON
            JsonObject respuestaJSON = new JsonObject();

            if (usuario != null) {

                   respuestaJSON.addProperty("exito", true);
                   respuestaJSON.addProperty("mensaje", "BIENVENIDO " + usuario.getNombreCompleto());
                   respuestaJSON.addProperty("rol", usuario.getNombreCompleto());


                   //URL Destino
                    String contextPath = req.getContextPath();   //devuelve el contextPath de la configuracion en el servidor
                    String urlDestino = "";

                    if("admin".equals(usuario.getRol())){
                        urlDestino = contextPath + "/creacion_usuario.html";
                    } else if ("user".equals(usuario.getRol())) {
                        urlDestino = contextPath + "/contabilidad.html";
                    } else {
                        urlDestino = contextPath + "/login_usuarios.html";
                    }


                    respuestaJSON.addProperty("redirectUrl", urlDestino);

                    // Guardar Sesión
                    req.getSession().setAttribute("usuario", usuario);


            } else {
                // Login fallido - usuario o contraseña incorrectos
                respuestaJSON.addProperty("exito", false);
                respuestaJSON.addProperty("mensaje", "Usuario o contraseña incorrectos");
            }

            //Enviar JSON
            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(respuestaJSON));
            out.flush();


        } catch (Exception e) {
            // Error en la base de datos
            e.printStackTrace();

            JsonObject errorRespuesta = new JsonObject();
            errorRespuesta.addProperty("exito", false);
            errorRespuesta.addProperty("mensaje", "Error en el servidor " + e.getMessage());

            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(errorRespuesta));
            out.flush();
        }


    }


}