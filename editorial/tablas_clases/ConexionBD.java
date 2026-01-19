package org.example.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/normalizacion";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void main(String[] args) {
        try {
            Connection con = conectar();
            System.out.println("Conectado a PostgreSQL correctamente 😎");
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

    }
}
