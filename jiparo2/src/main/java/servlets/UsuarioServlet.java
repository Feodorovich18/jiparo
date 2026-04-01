package servlets;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

import model.Empresa;
import model.Usuario;

@WebServlet("/api/usuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private Gson gson = new Gson();

    /******************************************************************
    POST
     ******************************************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String datosJSON = ServletJson.capturarJson(req, resp);

        // Convertir JSON a objeto Java
        Usuario usuario = gson.fromJson(datosJSON, Usuario.class);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.agregarUsuario(usuario);


        // 5. Crear respuesta
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("exito", "true");
        respuesta.addProperty("mensaje", "Usuario " + usuario.getNombre() + " recibido correctamente");
        respuesta.addProperty("recibido", true);

        // 6. Enviar respuesta al frontend
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(respuesta));
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




    }


}