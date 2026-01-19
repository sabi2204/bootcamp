package org.example.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class profesor_curso {
    private int id_profesor;
    private int id_curso;

    public profesor_curso (int id_profesor, int id_curso){
        this.id_profesor = id_profesor;
        this.id_curso = id_curso;
    }

    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.profesor_curso (id_profesor, id_curso) VALUES (?,?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_profesor);
            ps.setInt(2, id_curso);
            ps.executeUpdate();

        }
    }

    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.profesor_curso";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.profesor_curso  where id_profesor = ? and id_curso = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_profesor);
            ps.setInt(2, id_curso);
            ps.executeUpdate();

        }
    }
}
