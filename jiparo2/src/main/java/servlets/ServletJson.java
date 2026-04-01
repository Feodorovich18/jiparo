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
import java.sql.SQLException;

import jakarta.servlet.http.HttpSession;
import model.Empresa;
import model.Usuario;
import dao.UsuarioDAO;

public class ServletJson extends HttpServlet{

    public static void crearJson(){

    }


    /*******************************************************************************************************************************
     * Capturar Json para convertirlo a objeto
     *******************************************************************************************************************************/
    public static String capturarJson(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        // Configurar respuesta
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


        return datosJSON;
    }
}
