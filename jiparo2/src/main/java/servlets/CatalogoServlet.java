package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.CatalogoDAO;
import dao.EmpresaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Usuario;
import model.Catalogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/api/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {

    private Gson gson = new Gson();


    /**********************************************************************
     * POST
     *********************************************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("empresa") != null) {

            Empresa empresa = (Empresa) session.getAttribute("empresa");


            // Recibir JSON
            String datosJSON = ServletJson.capturarJson(req, resp);

            // Flag, yo
            System.out.println("Datos recibidos: " + datosJSON);

            // Convertir JSON a objeto Java
            Catalogo catalogo = gson.fromJson(datosJSON, Catalogo.class);

            CatalogoDAO catalogoDAO = new CatalogoDAO();
            //catalogoDAO.agregarCatalogo();


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

        Gson gson = new Gson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Catalogo catalogo = new Catalogo();

        //PROFEEEEEEEEEEEEEEEEEEEEEEEEEeee


        String json = gson.toJson(catalogo);
    }

}