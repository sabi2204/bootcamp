package org.example.clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class main {

    public static void main(String[] args) {

        String sql = """
            SELECT
                pr.id_prestamo,
                c.nom_colegio,
                p.nombre_profesor,
                ah.nom_asignatura_habilidad,
                a.nombre_aula,
                cu.nombre_curso,
                l.nombre_libro,
                e.nombre_editorial,
                pr.fecha_prestamo
            FROM ejercicio5.prestamo pr
            JOIN ejercicio5.profesor p ON pr.id_profesor = p.id_profesor
            JOIN ejercicio5.colegio_profesor cp ON p.id_profesor = cp.id_profesor
            JOIN ejercicio5.colegio c ON cp.id_colegio = c.id_colegio
            JOIN ejercicio5.profesor_curso pc ON p.id_profesor = pc.id_profesor
            JOIN ejercicio5.curso cu ON pc.id_curso = cu.id_curso
            JOIN ejercicio5.aula a ON c.id_colegio = a.id_colegio
            JOIN ejercicio5.habilidad_profesor hp ON p.id_profesor = hp.id_profesor
            JOIN ejercicio5.asignatura_habilidad ah ON hp.id_asignatura_habilidad = ah.id_asignatura_habilidad
            JOIN ejercicio5.prestamo_libros pl ON pr.id_prestamo = pl.id_prestamo
            JOIN ejercicio5.libro l ON pl.id_libro = l.id_libro
            JOIN ejercicio5.editorial e ON l.id_editorial = e.id_editorial
            ORDER BY pr.fecha_prestamo
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("--------------------------------------");
                System.out.println("Préstamo: " + rs.getInt("id_prestamo"));
                System.out.println("--------------------------------------");
                System.out.println("Colegio: " + rs.getString("nom_colegio"));
                System.out.println("Profesor: " + rs.getString("nombre_profesor"));
                System.out.println("Asignatura/Habilidad: " + rs.getString("nom_asignatura_habilidad"));
                System.out.println("Aula: " + rs.getString("nombre_aula"));
                System.out.println("Curso: " + rs.getString("nombre_curso"));
                System.out.println("Libro: " + rs.getString("nombre_libro"));
                System.out.println("Editorial: " + rs.getString("nombre_editorial"));
                System.out.println("Fecha préstamo: " + rs.getDate("fecha_prestamo"));

            }

        } catch (Exception e) {
            System.out.println("Error al mostrar datos");
            e.printStackTrace();
        }
    }
}
