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


public class CatalogoDAO {

    // Configuración de conexión a PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/jiparo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Noexiste18";

    // Constructor - cargar driver
    public CatalogoDAO() {
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
    public void agregarEmpresa(String nombre_empresa, String rfc) {


        String sql = "INSERT INTO catalogo(nombre_empresa, rfc) VALUES (?, ?);";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql))   {



            // Insertar en BD
            stmt.setString(1, nombre_empresa);
            stmt.setString(2, rfc);




            //Verificacion
            int resultado = stmt.executeUpdate();  // executeUpdate() para INSERT/UPDATE/DELETE

            if (resultado > 0) {
                System.out.println("Empresa creada exitosamente");
            }

            // Cerrar recursos
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al crear Empresa: " + e.getMessage());
            e.printStackTrace();
        }
    }

}