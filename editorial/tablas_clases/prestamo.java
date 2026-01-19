package org.example.clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class prestamo {
    private int id_prestamo;
    private LocalDate fecha_prestamo;


    public prestamo(int id_prestamo, LocalDate fecha_prestamo) {
        this.id_prestamo = id_prestamo;
        this.fecha_prestamo = fecha_prestamo;
    }
    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.prestamo (fecha_prestamo) VALUES (?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(fecha_prestamo));
            ps.executeUpdate();
        }
    }
    public void actualizar() throws SQLException {
        String sql = "UPDATE ejercicio5.prestamo SET fecha_prestamo = ? WHERE id_prestamo = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha_prestamo));
            ps.setInt(2, id_prestamo);
            ps.executeUpdate();
        }
    }
    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.prestamo";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.prestamo where id_prestamo = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id_prestamo);
            ps.executeUpdate();

        }
    }

}