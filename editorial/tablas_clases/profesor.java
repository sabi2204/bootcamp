package org.example.clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class profesor {
    private int id_profesor;
    private String  nombre_profesor;
    public profesor ( int id_profesor, String nombre_profesor){
        this.id_profesor = id_profesor;
        this.nombre_profesor = nombre_profesor;
    }
    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.profesor (nombre_profesor) VALUES (?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre_profesor);
            ps.executeUpdate();

        }
    }
    public void actualizar() throws SQLException {
        String sql = "UPDATE ejercicio5.profesor SET nombre_profesor = ? WHERE id_profesor = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre_profesor);
            ps.setInt(2,id_profesor);
            ps.executeUpdate();
        }
    }
    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.profesor ";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.profesor where id_profesor = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_profesor);
            ps.executeUpdate();

        }
    }

}
