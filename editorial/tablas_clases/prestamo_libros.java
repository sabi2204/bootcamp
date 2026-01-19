package org.example.clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class prestamo_libros {
    private int id_prestamo;
    private int id_libro;

    public prestamo_libros(int id_prestamo, int id_libro){
        this.id_prestamo = id_prestamo;
        this.id_libro = id_libro;
    }

    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.prestamo_libros (id_prestamo, id_libro) VALUES (?,?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_prestamo);
            ps.setInt(2, id_libro);
            ps.executeUpdate();

        }
    }

    public static ResultSet mostrar () throws SQLException{
        String sql = "SELECT * FROM ejercicio5.prestamo_libros";
        Connection con = ConexionBD.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }

    public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.prestamo_libros  where id_prestamo = ? and id_libro= ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_prestamo);
            ps.setInt(2, id_libro);
            ps.executeUpdate();

        }
    }
}
