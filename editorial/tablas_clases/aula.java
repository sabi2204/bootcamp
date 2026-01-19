package org.example.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aula {
    private int id_aula;
    private String nombre_aula;

    public aula(int id_aula, String nombre_aula){
        this.id_aula = id_aula;
        this.nombre_aula = nombre_aula;
    }
    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.aula (nombre_aula) VALUES (?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre_aula);
            ps.executeUpdate();

        }
    }
    public void actualizar() throws SQLException {
        String sql = "UPDATE ejercicio5.aula SET nombre_aula = ? WHERE id_aula = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre_aula);
            ps.setInt(2,id_aula);
            ps.executeUpdate();
        }
    }
    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.aula ";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.colegio  where id_aula= ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_aula);
            ps.executeUpdate();

        }
    }

}



