package dao;

import java.io.IOException;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


import model.Empresa;
import model.Usuario;

public class UsuarioDAO implements DAOIntefaz{

    // Constructor - cargar driver
    public UsuarioDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando driver PostgreSQL: " + e.getMessage());
        }
    }



    /********************************************************************
     * VALIDAR LOGIN
     ********************************************************************/
    public Usuario validarLogin(String username, String password) throws SQLException {
        String sql = "SELECT username, password, rol, id_usuario FROM usuario WHERE username = ? AND password = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Usar PreparedStatement para evitar SQL Injection
            stmt.setString(1, username);
            stmt.setString(2, password);  // Sin hashear, compara directamente


            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(rs.getString("rol"));

                    // Para guardar en la sesión
                    usuario.setId_usuario(rs.getLong("id_usuario"));

                    System.out.println("Login exitoso para usuario: " + username);
                    return usuario;


                }
            }
        }

        // Usuario no encontrado
        System.out.println("Login fallido para usuario: " + username);
        return null;
    }




    /*****************************************
     AGREGAR USUARIO
     ******************************************/
    public void agregarUsuario(Usuario usuario) {


        String sql = "INSERT INTO usuario(nombre, apellido_paterno, apellido_materno, email, telefono, activacion_contabilidad, activacion_nomina, " +
                "activacion_inventario, activacion_facturacion, activacion_reportes, activacion_administracion, username, password, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido_paterno());
            stmt.setString(3, usuario.getApellido_materno());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefono());
            stmt.setBoolean(6, usuario.isActivacion_contabilidad());
            stmt.setBoolean(7, usuario.isActivacion_nomina());
            stmt.setBoolean(8, usuario.isActivacion_inventario());
            stmt.setBoolean(9, usuario.isActivacion_facturacion());
            stmt.setBoolean(10, usuario.isActivacion_reportes());
            stmt.setBoolean(11, usuario.isActivacion_administracion());
            stmt.setString(12, usuario.getUsername());
            stmt.setString(13, usuario.getPassword());
            stmt.setString(14, usuario.getRol());



            //Verificacion
            stmt.executeUpdate();  // executeUpdate() para INSERT/UPDATE/DELETE


        } catch (SQLException e) {
            System.out.println("Error al agregar Usuario " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**********************************************************************
    * OBTENER USUARIO COMPLETO
     **********************************************************************/
    public static Usuario obtenerUsuarioCompleto(String username, String password) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, username);
                stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setId_usuario(rs.getLong("id_usuario"));
                    usuario.setNombre(rs.getString("nombre") + " " + rs.getString("apellido_paterno") + " " + rs.getString("apellido_materno"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setActivacion_contabilidad(rs.getBoolean("activacion_contabilidad"));
                    usuario.setActivacion_nomina(rs.getBoolean("activacion_nomina"));
                    usuario.setActivacion_inventario(rs.getBoolean("activacion_inventario"));
                    usuario.setActivacion_facturacion(rs.getBoolean("activacion_facturacion"));
                    usuario.setActivacion_reportes(rs.getBoolean("activacion_reportes"));
                    usuario.setActivacion_administracion(rs.getBoolean("activacion_administracion"));

                    System.out.println("Obtener usuario exitoso");
                    return usuario;


                }
            }



        }catch (SQLException e){
            System.out.println("Error al obtener usuario" + e.getMessage());
            e.printStackTrace();

        }

        return null;
    }




    /*****************************************************************************
    * OBTENER LISTA DE USUARIOS
     *****************************************************************************/
    public List<Usuario> obtenerListaUsuarios() throws IOException, SQLException {

        List<Usuario> lista_usuarios = new ArrayList<Usuario>();

        /********************
         * CONCAT para el nombre completo
         ***************/
        String sql = "SELECT id_usuario, CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) AS nombre_completo," +
                "email, telefono, estatus, activacion_contabilidad, activacion_nomina, activacion_inventario, activacion_facturacion," +
                "activacion_reportes, activacion_administracion, username, password, rol FROM usuario;";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setId_usuario(rs.getLong("id_usuario"));
                    usuario.setNombreCompleto(rs.getString("nombre_completo"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setEstatus(rs.getBoolean("estatus"));
                    usuario.setActivacion_contabilidad(rs.getBoolean("activacion_contabilidad"));
                    usuario.setActivacion_nomina(rs.getBoolean("activacion_nomina"));
                    usuario.setActivacion_inventario(rs.getBoolean("activacion_inventario"));
                    usuario.setActivacion_facturacion(rs.getBoolean("activacion_facturacion"));
                    usuario.setActivacion_reportes(rs.getBoolean("activacion_reportes"));
                    usuario.setActivacion_administracion(rs.getBoolean("activacion_administracion"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(rs.getString("rol"));


                    lista_usuarios.add(usuario);
                }
                return lista_usuarios;
            }


        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER EMPRESAS");
            e.printStackTrace();
        }

        return null;
    }





    /*********************************************
     * ELIMINAR USUARIO
     ************************************************/
    public static void eliminarUsuario(String nombre){

        String sql = "DELETE FROM usuario WHERE nombre = ?; ";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, nombre);

            stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }





    }
}
