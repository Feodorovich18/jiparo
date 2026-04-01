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


public class EmpresaDAO implements DAOIntefaz{

    // Constructor - cargar driver
    public EmpresaDAO() {
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
     AGREGAR EMPRESA
     ******************************************/
    public void agregarEmpresa(Empresa empresa) {


        String sql = "INSERT INTO empresa(nombre_empresa, rfc) VALUES (?, ?);";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {

            stmt.setString(1, empresa.getNombre_empresa());
            stmt.setString(2, empresa.getRfc());




            //Verificacion
            int resultado = stmt.executeUpdate();  // executeUpdate() para INSERT/UPDATE/DELETE

            if (resultado > 0) {
                System.out.println("Empresa eliminada");
            }


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