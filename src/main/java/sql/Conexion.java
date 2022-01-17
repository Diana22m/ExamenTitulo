package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
    private static Connection conn = null;
    private static String uri = "jdbc:mysql://db4free.net:3306/todolist_bd?user=todolist_bd&password=todolist";
    public static Connection getConexion(){
        try {
            conn = DriverManager.getConnection(uri);
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
        return conn;
    }

}
