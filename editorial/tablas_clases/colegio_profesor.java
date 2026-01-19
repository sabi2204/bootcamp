package org.example.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class colegio_profesor {
    private int id_colegio;
    private int id_profesor;

    public colegio_profesor(int id_colegio, int id_profesor) {
        this.id_colegio = id_colegio;
        this.id_profesor = id_profesor;
    }

    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.colegio_profesor (id_colegio, id_profesor) VALUES (?,?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_colegio);
            ps.setInt(2, id_profesor);
            ps.executeUpdate();

        }
    }

    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.colegio_profesor";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.colegio_profesor  where id_colegio = ? and id_profesor = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_colegio);
            ps.setInt(2, id_profesor);
            ps.executeUpdate();

        }
    }
}
