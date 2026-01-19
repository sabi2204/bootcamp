package org.example.clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class libro {
    private int id_libro;
    private String nombre_libro;
    private int id_editorial;

    public libro(String nombre_libro, int id_editorial){
        this.id_libro = id_libro;
        this.nombre_libro = nombre_libro;
        this.id_editorial = id_editorial;
    }

    public void insertar() throws SQLException {
        String sql = "INSERT INTO ejercicio5.libro (nombre_libro, id_editorial) VALUES (?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre_libro);
            ps.setInt(2, id_editorial);
            ps.executeUpdate();

        }
    }
   public void actualizar() throws SQLException {
       String sql = "UPDATE ejercicio5.libro SET nombre_libro = ?, id_editorial = ? WHERE id_libro = ?";

       try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
           ps.setString(1, nombre_libro);
           ps.setInt(2, id_editorial);
           ps.setInt(3,id_libro);
           ps.executeUpdate();
       }
   }
   public static ResultSet mostrar () throws SQLException{
       String sql = "SELECT * FROM ejercicio5.libro";
       Connection con = ConexionBD.conectar();
       PreparedStatement ps = con.prepareStatement(sql);
       return ps.executeQuery();

   }

   public void eliminar () throws SQLException{
        String sql = "DELETE FROM ejercicio5.libro where id_libro = ?";
        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id_libro);
            ps.executeUpdate();

        }
   }
}


