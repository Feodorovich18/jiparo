package servlets;

import com.google.gson.*;
import dao.EmpresaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Usuario;
import dao.UsuarioDAO;

@WebServlet("/api/loginUsuarioServlet")
public class LoginUsuarioServlet extends HttpServlet {

    //**************************************************************************************
    //Deserialización para el LocalDate
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext ctx)
                        throws JsonParseException {
                    return LocalDate.parse(json.getAsString());
                }
            })
            .create();
    //******************************************************************************************


    /*******************************************************************
    * POST
     ******************************************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String datosJSON = ServletJson.capturarJson(req, resp);

        //Convertir JSON a objeto Java
        Usuario usuario_a_comparar = gson.fromJson(datosJSON, Usuario.class);



        try {
            // Crear instancia del DAO para consultar la BD
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.validarLogin(usuario_a_comparar.getUsername(), usuario_a_comparar.getPassword());

            // Crear objeto JSON
            JsonObject respuestaJSON = new JsonObject();

            if (usuario != null) {

                   respuestaJSON.addProperty("exito", true);
                   respuestaJSON.addProperty("mensaje", "BIENVENIDO " + usuario.getNombre());
                   respuestaJSON.addProperty("rol", usuario.getNombre());




                   //URL Destino
                    String contextPath = req.getContextPath();   //devuelve el contextPath de la configuracion en el servidor
                    String urlDestino = "";


                    // Redireccionar y guardar sesión
                    if("ADMIN".equals(usuario.getRol())){
                        urlDestino = contextPath + "/dashboard_admin.html";
                    } else if ("USER".equals(usuario.getRol())) {
                        urlDestino = contextPath + "/contabilidad.html";

                        // Guardar Sesión
                        HttpSession sessionLogin = req.getSession(true);
                        sessionLogin.setAttribute("usuario", usuario);



                    } else {
                        urlDestino = contextPath + "/empresa.html";
                    }


                    respuestaJSON.addProperty("redirectUrl", urlDestino);


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