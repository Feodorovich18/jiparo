package servlets;

import com.google.gson.*;
import dao.EmpresaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Usuario;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/api/empresaServlet")
public class EmpresaServlet extends HttpServlet {



    /***********************************************
    * POST
     *********************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{


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

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        String datosJson = ServletJson.capturarJson(req, res);

        Empresa empresa = gson.fromJson(datosJson, Empresa.class);


        HttpSession session = req.getSession(false);
        if (session != null) {
            session.setAttribute("empresa", empresa);
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write("{\"status\":\"ok\"}");
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("{\"status\":\"no session\"}");
        }

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
