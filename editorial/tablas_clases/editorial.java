package org.example.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editorial {
    private int id_editorial;
    private String nombre_editorial;


    public editorial(int id_editorial, String nombre_editorial){
        this.id_editorial = id_editorial;
        this.nombre_editorial = nombre_editorial;
    }
    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.editorial (nombre_editorial) VALUES (?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre_editorial);
            ps.executeUpdate();

        }
    }
    public void actualizar() throws SQLException {
        String sql = "UPDATE ejercicio5.editorial SET nombre_libro = ? WHERE id_editorial = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre_editorial);
            ps.setInt(2,id_editorial);
            ps.executeUpdate();
        }
    }
    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.editorial";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.editorial  where id_editorial = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id_editorial);
            ps.executeUpdate();

        }
    }

}


