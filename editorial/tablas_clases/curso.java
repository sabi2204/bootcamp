package org.example.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class curso {
    private int id_curso;
    private String nombre_curso;
public curso(int id_curso, String nombre_curso){
    this.id_curso = id_curso;
    this.nombre_curso = nombre_curso;
}
    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.curso (nombre_curso) VALUES (?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre_curso);
            ps.executeUpdate();

        }
    }
    public void actualizar() throws SQLException {
        String sql = "UPDATE ejercicio5.colegio SET nombre_curso = ? WHERE id_curso = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {ps.setInt(1,id_curso);
            ps.setString(1, nombre_curso);
            ps.setInt(2,id_curso);
            ps.executeUpdate();
        }
    }
    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.curso";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.colegio  where id_curso = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_curso);
            ps.executeUpdate();

        }
    }

}



