package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.CuentaDAO;
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
import java.util.*;

import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Cuenta;
import model.Usuario;

@WebServlet("/api/CuentaServlet")
public class CuentaServlet extends HttpServlet{

    Gson gson = new Gson();

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
        System.out.println("Datos recibidos: " + datosJSON);

        // 3. Convertir JSON a objeto Java
        Cuenta cuenta = gson.fromJson(datosJSON, Cuenta.class);

        try {

            CuentaDAO cuentaDAO = new CuentaDAO();
            cuentaDAO.agregarCuenta(cuenta);

            // Crear objeto JSON
            JsonObject respuestaJSON = new JsonObject();

            if (cuenta != null) {

                respuestaJSON.addProperty("exito", true);

            } else {
                respuestaJSON.addProperty("exito", false);

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
