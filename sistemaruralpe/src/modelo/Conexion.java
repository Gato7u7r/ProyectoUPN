package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://acela.proxy.rlwy.net:50215/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String usuario = "root";
            String password = "BqPVaxfodgPUHjmHObeyvpwerbpwnfXW";
            
            return DriverManager.getConnection(url, usuario, password);
            
        } catch (Exception e) {
            System.out.println("Error de base de datos: " + e.getMessage());
            return null;
        }
    }
}