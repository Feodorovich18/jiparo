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

import model.Empresa;
import model.Usuario;


public class EmpresaDAO implements DAOIntefaz{

    // Constructor - cargar driver
    public EmpresaDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando driver PostgreSQL: " + e.getMessage());
        }
    }



    /***************************************************************************
    * OBTENER LISTA DE EMPRESAS
    **************************************************************************/
     // Obtener empresas de un usuario específico
    public List<Empresa> obtenerListaEmpresas(Long id_usuario) throws SQLException {

        List<Empresa> lista_empresas = new ArrayList<>();


        String sql = "SELECT id_empresa, numero_empresa, nombre_empresa, rfc, direccion_fiscal, id_usuario FROM empresa WHERE id_usuario = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id_usuario);


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    Empresa empresa = new Empresa();

                    empresa.setId_empresa(rs.getLong("id_empresa"));
                    empresa.setNumero_empresa(rs.getInt("numero_empresa"));
                    empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                    empresa.setRfc(rs.getString("rfc"));
                    empresa.setDireccion_fiscal(rs.getString("direccion_fiscal"));
                    empresa.setId_usuario(rs.getInt("id_usuario"));


                    lista_empresas.add(empresa);
                }
                return lista_empresas;
            }

        }catch (SQLException e){
            System.out.println("ERROR AL OBTENER EMPRESAS");
            e.printStackTrace();
        }
        return null;
    }




    /*****************************************
     AGREGAR EMPRESA
     ******************************************/
    public void agregarEmpresa(Empresa empresa, Usuario usuario) {


        String sql = "INSERT INTO empresa(numero_empresa, nombre_empresa, rfc, direccion_fiscal, descripcion, id_usuario) VALUES (?, ?, ?, ?, ?, ?);";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {

            stmt.setInt(1, empresa.getNumero_empresa());
            stmt.setString(2, empresa.getNombre_empresa());
            stmt.setString(3, empresa.getRfc());
            stmt.setString(4, empresa.getDireccion_fiscal());
            stmt.setString(5, empresa.getDescripcion());
            stmt.setLong(6,usuario.getId_usuario());

            //Verificacion
            stmt.executeUpdate();  // executeUpdate() para INSERT/UPDATE/DELETE



        } catch (SQLException e) {
            System.out.println("Error al crear Empresa: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /*********************************************
    * ELIMINAR EMPRESA
     ************************************************/
    public static void eliminarEmpresa(String nombre_empresa){

        String sql = "DELETE FROM empresa WHERE nombre_empresa = ?; ";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, nombre_empresa);

            stmt.executeUpdate();

        }catch (Exception throwables){
            throwables.printStackTrace();
        }



    }
}