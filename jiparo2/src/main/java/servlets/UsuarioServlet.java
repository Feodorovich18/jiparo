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

import model.Empresa;
import model.Usuario;

@WebServlet("/api/usuarioServlet")
public class UsuarioServlet extends HttpServlet {

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
        Usuario usuario = gson.fromJson(datosJSON, Usuario.class);



        /*
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.agregarEmpresa(empresa.getNombre_empresa(), empresa.getRfc());
        */

        // 5. Crear respuesta
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("estado", "éxito");
        respuesta.addProperty("mensaje", "Usuario " + usuario.getNombreCompleto() + " recibido correctamente");
        respuesta.addProperty("recibido", true);

        // 6. Enviar respuesta al frontend
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(respuesta));
        out.flush();
    }


}