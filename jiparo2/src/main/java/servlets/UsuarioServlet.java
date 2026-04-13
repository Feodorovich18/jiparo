package servlets;


import com.google.gson.*;
import dao.EmpresaDAO;
import dao.UsuarioDAO;
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
import java.util.List;
import java.time.*;

import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Usuario;

@WebServlet("/api/usuarioServlet")
public class UsuarioServlet extends HttpServlet {


    /******************************************************************
    POST
     ******************************************************************/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("application/json");
        String accion = request.getParameter("action");

        switch (accion){
            case "agregar_usuario":
                agregarUsuario(request, response);
                break;

        }

    }

    /******************************************************************************
     * GET
     ******************************************************************************/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        response.setContentType("application/json");
        String accion = request.getParameter("action");

        switch(accion){
            case "lista_usuarios":
                obtenerListaUsuarios(request, response);
                break;
        }



    }







    /***********************************************************************
    * METODOS STATIC PARA DIFERENTES OPERACIONES DENTRO DE LA MISMA PAGINA
     **********************************************************************/

    private static void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

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



        String datosJSON = ServletJson.capturarJson(request, response);

        // Convertir JSON a objeto Java
        Usuario usuario = gson.fromJson(datosJSON, Usuario.class);



        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.agregarUsuario(usuario);


        // 5. Crear respuesta
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("exito", true);
        respuesta.addProperty("mensaje", "Usuario " + usuario.getNombre() + " recibido correctamente");
        respuesta.addProperty("recibido", true);

        // 6. Enviar respuesta al frontend
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(respuesta));
        out.flush();
    }


    //*****************************************************************
    private static void obtenerListaUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

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

        try {
            List<Usuario> lista_usuarios;
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            lista_usuarios = usuarioDAO.obtenerListaUsuarios();


            /*************************
             * Para contar la cantidad de licencias activas, yo
             ************************/
            for(Usuario u: lista_usuarios){

                int cantidad_licencias = 0;

                if(u.isActivacion_contabilidad()) cantidad_licencias++;
                if(u.isActivacion_nomina()) cantidad_licencias++;
                if(u.isActivacion_inventario()) cantidad_licencias++;
                if(u.isActivacion_facturacion()) cantidad_licencias++;
                if(u.isActivacion_reportes()) cantidad_licencias++;
                if(u.isActivacion_administracion()) cantidad_licencias++;

                u.setCantidad_licencias(cantidad_licencias);

            }

            // Convertir a JSON
            String jsonOutput = gson.toJson(lista_usuarios);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonOutput);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}