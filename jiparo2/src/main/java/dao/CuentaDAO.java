package dao;


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

import model.Catalogo;
import model.Cuenta;
import model.Empresa;
import model.Usuario;


public class CuentaDAO implements DAOIntefaz {


    // Constructor - cargar driver
    public CuentaDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando driver PostgreSQL: " + e.getMessage());
        }
    }



    /*
     // Obtener empresas de un usuario específico
    public List<Empresa> obtenerPorUsuario(int idUsuario) throws SQLException {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT id_empresa, nombre_empresa, rfc, direccion_fiscal, id_usuario " +
                     "FROM empresas WHERE id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Empresa(
                        rs.getInt("id_empresa"),
                        rs.getString("nombre_empresa"),
                        rs.getString("rfc"),
                        rs.getString("direccion_fiscal"),
                        rs.getInt("id_usuario")
                    ));
                }
            }
        }
        return lista;
    }
    */




    /*****************************************
     AGREGAR CUENTA
     ******************************************/
    public void agregarCuenta(Cuenta cuenta) {


        String sql = "INSERT INTO cuenta(nombre_cuenta, concepto, codigo_cuenta, tipo_cuenta, naturaleza, nivel, id_catalogo, codigo_agrupador_sat) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {

            stmt.setString(1, cuenta.getNombre_cuenta());
            stmt.setString(2, cuenta.getConcepto());
            stmt.setString(3, cuenta.getCodigo_cuenta());
            stmt.setString(4, cuenta.getTipo_cuenta());
            stmt.setString(5, cuenta.getNaturaleza());
            stmt.setInt(6, cuenta.getNivel());
            stmt.setInt(7, cuenta.getId_catalogo());
            stmt.setString(8, cuenta.getCodigo_agrupador_sat());




            //Verificacion
            int resultado = stmt.executeUpdate();  // executeUpdate() para INSERT/UPDATE/DELETE

            if (resultado > 0) {
                System.out.println("========CUENTA CREADA===========");
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /*************************************************************
    * OBTENER CUENTA
     ***********************************************************/
    public static Cuenta obtenerCuenta(Long id) throws SQLException{
        String sql = "SELECT * FROM cuenta WHERE id = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, id);


            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Cuenta cuenta = new Cuenta();

                    cuenta.setId_cuenta(rs.getLong("id_cuenta"));
                    cuenta.setNombre_cuenta(rs.getString("nombre_cuenta"));
                    cuenta.setCodigo_cuenta(rs.getString("codigo_cuenta"));
                    cuenta.setConcepto(rs.getString("concepto"));
                    cuenta.setNaturaleza(rs.getString("naturaleza"));
                    cuenta.setId_catalogo(rs.getInt("id_catalogo"));
                    cuenta.setNivel(rs.getInt("nivel"));
                    cuenta.setCodigo_agrupador_sat("codigo_agrupador_sat");

                    System.out.println("✓ Obtener Cuenta exitoso");
                    return cuenta;


                }
            }



        }catch (SQLException e){
            System.out.println("Error al obtener usuario" + e.getMessage());
            e.printStackTrace();

        }

        return null;
    }


    /*********************************************
     * ELIMINAR CUENTA
     ************************************************/
    public static void eliminarCuenta(String nombre_cuenta){

        String sql = "DELETE FROM cuenta WHERE id_cuenta = ?; ";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, nombre_cuenta);

            stmt.executeUpdate();

        }catch (Exception throwables){
            throwables.printStackTrace();
        }







    }

    /*******************************************
     UPDATE CUENTA
     ******************************************/
    public static void editarCuenta(Catalogo catalogo){

        String sql = "UPDATE cuenta SET nombre_cuenta = ?, concepto = ? WHERE id = ?" +
                "";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {

            stmt.setString(1, catalogo.getNombre_catalogo());
            stmt.setInt(2, catalogo.getAnio_fiscal());

            stmt.executeUpdate();


        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }
}
