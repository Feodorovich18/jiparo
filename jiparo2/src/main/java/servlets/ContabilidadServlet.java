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
import java.util.*;

import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Usuario;

@WebServlet("/api/contabilidadServlet")
public class ContabilidadServlet extends HttpServlet {


    /**********************************************************************
    * POST
     *********************************************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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



        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("usuario") != null) {

            Usuario usuario = (Usuario) session.getAttribute("usuario");


            // Recibir JSON
            String datosJSON = ServletJson.capturarJson(req, resp);

            // Flag, yo
            System.out.println("Datos recibidos: " + datosJSON);

            // Convertir JSON a objeto Java
            Empresa empresa = gson.fromJson(datosJSON, Empresa.class);

            EmpresaDAO empresaDAO = new EmpresaDAO();
            empresaDAO.agregarEmpresa(empresa, usuario);


        } else {
            System.out.println("ERROR al conseguir el id_usuario");
        }




        // 5. Crear respuesta
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("estado", "éxito");
        //respuesta.addProperty("mensaje", "Usuario " + empresa.getNombre_empresa() + " recibido correctamente");
        respuesta.addProperty("exito", true);

        // 6. Enviar respuesta al frontend
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(respuesta));
        out.flush();
    }


    /******************************************************************************
    * DO GET
     ******************************************************************************/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("usuario") != null) {

            Usuario usuario = (Usuario) session.getAttribute("usuario");


            try {
                List<Empresa> empresas;
                EmpresaDAO empresaDAO = new EmpresaDAO();
                empresas = empresaDAO.obtenerListaEmpresas(usuario.getId_usuario());


                // Convertir a JSON
                String jsonResponse = gson.toJson(empresas);

                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonResponse);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }





        } else {
            response.sendRedirect("login_usuarios.html");
        }
    }

}