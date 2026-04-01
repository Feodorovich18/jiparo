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


    /******************************************
    / POST
     *****************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Configurar la respuesta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Leer el JSON que viene del fetch
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String datosJSON = buffer.toString();
        System.out.println("Datos recibidos: " + datosJSON);



        // Convertir JSON a objeto Java
        Catalogo catalogo = gson.fromJson(datosJSON, Catalogo.class);

        CatalogoDAO catalogoDAO = new CatalogoDAO();
        catalogoDAO.agregarCatalogo(catalogo);

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