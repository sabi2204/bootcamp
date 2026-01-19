package org.example.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class colegio {
    private int id_colegio;
    private String nom_colegio;
    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.colegio (nombre_colegio) VALUES (?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nom_colegio);
            ps.executeUpdate();

        }
    }
    public void actualizar() throws SQLException {
        String sql = "UPDATE ejercicio5.colegio SET nom_colegio = ? WHERE id_colegio= ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nom_colegio);
            ps.setInt(2,id_colegio);
            ps.executeUpdate();
        }
    }
    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.colegio";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.colegio  where id_colegio = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_colegio);
            ps.executeUpdate();

        }
    }
}


