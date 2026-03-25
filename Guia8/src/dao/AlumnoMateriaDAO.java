package dao;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AlumnoMateriaDAO {
    public boolean asignar(int alumno, int materia) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO alumno_materia VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, alumno);
            ps.setInt(2, materia);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
