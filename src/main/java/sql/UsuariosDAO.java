package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuariosDAO {
    private static final String table="usuarios";
    public static boolean registrarUsuario(Usuarios u){
        boolean resultado=false;
        try{
            String query =  " insert into "+table+" (idusuarios, nombres, apellidos, correo, clave, edad)"
                            + " values (?, ?, ?, ?, ?, ?)";
		
            PreparedStatement preparedStmt = Conexion.getConexion().prepareStatement(query);
            preparedStmt.setInt(1, 0);
            preparedStmt.setString(2, u.getNombres());
            preparedStmt.setString(3, u.getApellidos());
            preparedStmt.setString(4, u.getCorreo());
            preparedStmt.setString(5, u.getClave());
            preparedStmt.setInt(6, u.getEdad());
            preparedStmt.execute();
            System.out.println("Usuario creado.");
            resultado=true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    public static Usuarios getUsuariosByCorreoAndClave(String correo, String clave){
        Statement statement;
        Usuarios usuario = null;
        try{
            statement = Conexion.getConexion().createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where correo='"
            +correo+"' and clave='"+clave+"';");
            if(rs.next()){
                usuario = new Usuarios(rs.getInt("idusuarios"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("clave"), rs.getInt("edad"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return usuario;
    }
    public static Usuarios getUsuariosByCorreo(String correo){
        Statement statement;
        Usuarios usuario = null;
        try{
            statement = Conexion.getConexion().createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where correo='"
            +correo+"';");
            if(rs.next()){
                usuario = new Usuarios(rs.getInt("idusuarios"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("clave"), rs.getInt("edad"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return usuario;
    }
}
