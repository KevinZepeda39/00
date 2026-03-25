package dao;

import conexion.Conexion;

import java.sql.*;

public class AlumnoDAO {

    // INSERTAR
    public boolean insertar(String nombre, String apellido, int edad, String direccion) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO alumno (Nombre, Apellido, Edad, Direccion) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, direccion);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // LISTAR
    public boolean listar() {
        try {
            Connection con = Conexion.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM alumno");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("Cod_alumno") + " - " +
                                rs.getString("Nombre") + " " +
                                rs.getString("Apellido")
                );
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ACTUALIZAR
    public boolean actualizar(int id, String nombre) {
        try {
            Connection con = Conexion.conectar();
            String sql = "UPDATE alumno SET Nombre=? WHERE Cod_alumno=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setInt(2, id);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        try {
            Connection con = Conexion.conectar();
            String sql = "DELETE FROM alumno WHERE Cod_alumno=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
