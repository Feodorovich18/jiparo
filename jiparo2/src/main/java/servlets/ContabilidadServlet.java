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
import java.util.*;

import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Usuario;

@WebServlet("/api/ContabilidadServlet")
public class ContabilidadServlet extends HttpServlet {

    private Gson gson = new Gson();

    /**********************************************************************
    * POST
     *********************************************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String datosJSON = ServletJson.capturarJson(req, resp);
        System.out.println("Datos recibidos: " + datosJSON);

        // Convertir JSON a objeto Java
        Empresa empresa = gson.fromJson(datosJSON, Empresa.class);

        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.agregarEmpresa(empresa);


        // 5. Crear respuesta
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("estado", "éxito");
        respuesta.addProperty("mensaje", "Usuario " + empresa.getNombre_empresa() + " recibido correctamente");
        respuesta.addProperty("recibido", true);

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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("usuario") != null) {

            Usuario usuario = (Usuario) session.getAttribute("usuario");


            System.out.println("HEEEEEEEEEEEEEREEEEEEEEEEE");

            System.out.println(usuario.getId_usuario());
            System.out.println(usuario.getUsername());

            /*
            // Convertir a JSON
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(datosUsuario);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonResponse);

             */

        } else {
            response.sendRedirect("login_usuarios.html");
        }
    }

}