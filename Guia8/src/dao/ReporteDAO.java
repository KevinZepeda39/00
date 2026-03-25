package dao;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReporteDAO {
    public boolean materiasDeAlumno(int idAlumno) {
        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT m.Nombre " +
                    "FROM materia m " +
                    "JOIN alumno_materia am ON m.Cod_materia = am.Cod_materia " +
                    "WHERE am.Cod_alumno = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);

            ResultSet rs = ps.executeQuery();

            System.out.println("Materias del alumno:");

            while (rs.next()) {
                System.out.println("- " + rs.getString("Nombre"));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
