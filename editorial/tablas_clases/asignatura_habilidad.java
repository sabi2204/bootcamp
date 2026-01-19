package org.example.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class asignatura_habilidad {
    private int id_asignatura_habilidad;
    private String nom_asignatura_habilidad;


    public asignatura_habilidad (int id_asignatura_habilidad, String nom_asignatura_habilidad){
        this.id_asignatura_habilidad = id_asignatura_habilidad;
        this.nom_asignatura_habilidad = nom_asignatura_habilidad;
    }
    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.asignatura_habilidad (nom_asignatura_habilidad) VALUES (?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nom_asignatura_habilidad);
            ps.executeUpdate();

        }
    }
    public void actualizar() throws SQLException {
        String sql = "UPDATE ejercicio5.asignatura_habilidad SET nom_asignatura_habilidad = ? WHERE id_asignatura_habiliodad = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nom_asignatura_habilidad);
            ps.setInt(2,id_asignatura_habilidad);
            ps.executeUpdate();
        }
    }
    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.asignatura_habilidad";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.asignatura_habilidad  where id_asignatura_habilidad = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_asignatura_habilidad);
            ps.executeUpdate();

        }
    }
}


