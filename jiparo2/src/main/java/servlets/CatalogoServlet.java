package servlets;

import com.google.gson.*;
import dao.CatalogoDAO;
import dao.CuentaDAO;
import dao.EmpresaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cuenta;
import model.Empresa;
import model.Usuario;
import model.Catalogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/api/catalogoServlet")
public class CatalogoServlet extends HttpServlet {


    /**********************************************************************
     * POST
     *********************************************************************/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        String accion = request.getParameter("action");

        switch(accion){

            case "crear_catalogo":
                crear_catalogo(request, response);
                break;

            case "crear_cuenta":
                crear_cuenta(request, response);
                break;
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

        Catalogo catalogo = new Catalogo();



        String json = gson.toJson(catalogo);
    }












    /****************************************************************************
    * METODOS ESTATICOS
     ****************************************************************************/

    private static void crear_catalogo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

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

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("empresa") != null) {

            // Para recuperar el id_empresa
            Empresa empresa = (Empresa) session.getAttribute("empresa");


            // Recibir JSON
            String datosJSON = ServletJson.capturarJson(request, response);

            // Flag, yo
            System.out.println("Datos recibidos: " + datosJSON);

            // Convertir JSON a objeto Java
            Catalogo catalogo = gson.fromJson(datosJSON, Catalogo.class);

            CatalogoDAO catalogoDAO = new CatalogoDAO();
            //catalogoDAO.agregarCatalogo(catalogo);


        } else {
            System.out.println("ERROR");
        }



        // 5. Crear respuesta
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("estado", true);
        //respuesta.addProperty("mensaje", "Usuario " + empresa.getNombre_empresa() + " recibido correctamente");
        respuesta.addProperty("exito", true);

        // 6. Enviar respuesta al frontend
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(respuesta));
        out.flush();
    }

    //*******************************************************************************************
    private static void crear_cuenta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

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



        // Recibir JSON
        String datosJSON = ServletJson.capturarJson(request, response);

        // Flag, yo'
        System.out.println("Datos recibidos: " + datosJSON);

        // Convertir JSON a objeto Java
        Cuenta cuenta = gson.fromJson(datosJSON, Cuenta.class);

        //
        asignarNaturaleza(cuenta);

        // BD
        CuentaDAO cuentaDAO = new CuentaDAO();
        cuentaDAO.agregarCuenta(cuenta);



        System.out.println("HEEEEEEEEEEEEEEEEEEEERE");
        System.out.println(cuenta.getNaturaleza());

    }

    //****************************************************
    private static void asignarNaturaleza(Cuenta cuenta){

        if(cuenta.getDenominacion_rubro().equals("Activo")){

            cuenta.setNaturaleza("DEUDORA");

        }else if(cuenta.getDenominacion_rubro().equals("Pasiva")){

            cuenta.setNaturaleza("ACREEDORA");
        }


    }

}