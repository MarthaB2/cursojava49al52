package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    protected Connection conexion;
    //constantes que son las credenciales para entrar a nuestra base de d
    // JDBC driver nombre y base de datos URL;
    private final String JDBC_DRIVER = "org.postgresql.Driver";//com.mysql.jdbc.Driver ( si no conectamos a otra tipo  es este por ejemplo)  // con este vamos a poder distinguir a que tipo de base de dato nos vamos a conectar
    private final String DB_URL = "jdbc:postgresql://localhost:5432/ejemplo";//"jdbc:mysql://localhost/ejemplo";

    //base de datos credenciales
    private final String USER = "postgres";
    private final String PASS = "password";

    //definiremos 2 metodo importantes
    //uno es para abrir la conexion a la base de datos
    public void conectar() throws Exception {
        try {
            conexion = (Connection) DriverManager.getConnection(DB_URL, USER, PASS); //es para la conexion a la bd
            Class.forName(JDBC_DRIVER);


        } catch (Exception e) {
            throw e;
        }
    }

    //para cerrar la conexion
    public void cerrar() throws SQLException {
        if (conexion != null) {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        }

    }
}