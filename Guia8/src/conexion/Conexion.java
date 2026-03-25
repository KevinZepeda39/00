package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection conectar() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/colegio",
                    "root",
                    "root" // tu contraseña si tienes
            );
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return con;
    }
}