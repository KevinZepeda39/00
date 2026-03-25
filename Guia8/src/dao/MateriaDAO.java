package dao;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MateriaDAO {
    public boolean insertar(String nombre, String descripcion) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO materia (Nombre, Descripcion) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, descripcion);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
