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
import model.Empresa;
import model.Usuario;


public class CatalogoDAO implements DAOIntefaz{

    // Constructor - cargar driver
    public CatalogoDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error del driver PostgreSQL" + e.getMessage());
        }
    }




    /*****************************************
     AGREGAR CATALOGO
     ******************************************
    public void agregarCatalogo(Catalogo catalogo) {


        String sql = "INSERT INTO catalogo(estructura_rubro, denominacion_rubro, estructura_cuenta, denominacion_cuenta, estructura_subcuenta, denominacion_subcuenta" +
                ", estructura_subsubcuenta, denominacion_subsubcuenta, id_empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {


            // Insertar en BD
            stmt.setString(1, catalogo.getEstructura_rubro());
            stmt.setString(2, catalogo.getDenominacion_rubro());

            stmt.setString(3, catalogo.getEstructura_cuenta());
            stmt.setString(4, catalogo.getDenominacion_cuenta());

            stmt.setString(5, catalogo.getEstructura_subcuenta());
            stmt.setString(6, catalogo.getDenominacion_subcuenta());

            stmt.setString(7, catalogo.getEstructura_subsubcuenta());
            stmt.setString(8, catalogo.getDenominacion_subsubcuenta());

            stmt.setLong(9, catalogo.getId_empresa());


            //Verificacion
            stmt.executeUpdate();  // executeUpdate() para INSERT/UPDATE/DELETE



        } catch (SQLException e) {
            System.out.println("Error al crear Catalogo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    */




    /*******************************************
    UPDATE CATALOGO
     ******************************************
    public static void editarCatalogo(Catalogo catalogo){

        String sql = "UPDATE catalogo SET nombre_catalogo = ?, anio_fiscal = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {

            stmt.setString(1, catalogo.getNombre_catalogo());
            stmt.setInt(2, catalogo.getAnio_fiscal());

            stmt.executeUpdate();


        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }*/


    /***************************************
    OBTENER CATALOGO
     ***************************************
    public static Catalogo obtenerCatalogo(Long id_catalogo){

        String sql = "SELECT id_catalogo, id_empresa, nombre_catalogo, anio_fiscal, fecha_creacion, estructura, estatus FROM catalogo WHERE id_catalogo = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, id_catalogo);


            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Catalogo catalogo = new Catalogo();

                    catalogo.setId_catalogo(rs.getLong("id_catalogo"));
                    catalogo.setId_empresa(rs.getLong("id_empresa"));
                    catalogo.setNombre_catalogo(rs.getString("nombre_catalogo"));
                    catalogo.setAnio_fiscal(rs.getInt("anio_fiscal"));
                    //catalogo.setFecha_creacion(rs.getDate("ds"));
                    catalogo.setEstructura(rs.getString("estructura"));
                    catalogo.setEstatus(rs.getBoolean("estatus"));

                    return catalogo;


                }
            }



        }catch (SQLException e){
            System.out.println("Error al obtener catalogo" + e.getMessage());
            e.printStackTrace();

        }

        // No se encontró el id_catalogo
        return null;
    }*/




    /********************************************
    ELIMINAR CATALOGO
     ********************************************/
    public static void eliminarCatalogo(Long id_catalogo){

        String sql = "DELETE FROM catalago WHERE id_catalogo = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, id_catalogo);

            stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error al eliminar catalogo " + e.getMessage());
        }

    }




}