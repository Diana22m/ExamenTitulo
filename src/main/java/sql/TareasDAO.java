package sql;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TareasDAO {
    private static final String table="tareas";
    public static boolean registrarTarea(Tareas t){
        boolean resultado=false;
        try{
            String query =  " insert into "+table+" (idTareas, titulo, contenido, imagen, idUsuario)"+ " values (?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStmt = Conexion.getConexion().prepareStatement(query);
            Blob blob = Conexion.getConexion().createBlob();
            if(t.getImagen()!=null){
                blob.setBytes(1, t.getImagen().getBytes());
            }else{
                blob=null;
            }

            preparedStmt.setInt(1, 0);
            preparedStmt.setString(2, t.getTitulo());
            preparedStmt.setString(3, t.getContenido());
            preparedStmt.setBlob(4, blob);
            preparedStmt.setInt(5, t.getIdUsuario());
            preparedStmt.execute();
            System.out.println("Tarea creada.");
            resultado=true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    public static List <Tareas> getTareasByUsuario(int idUsuario){

        List <Tareas> tareas=new ArrayList<>();

        Statement statement;

        try{
            statement = Conexion.getConexion().createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idUsuario = " + idUsuario);

            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Blob blob = rs.getBlob(4);
                String b64 = "no hay imagen cargada";
                if(blob!=null){
                    byte[] bdata = blob.getBytes(1, (int) blob.length());
                    b64 = new String(bdata);
                    
                }
                Tareas aux= new Tareas(rs.getInt(1), rs.getString(2), rs.getString(3), b64, rs.getInt(5));
                tareas.add(aux);
                System.out.println(aux.getTitulo());
                while(rs.next()){
                    blob = rs.getBlob(4);
                    b64 = "no hay imagen cargada";
                    if(blob!=null){
                        byte[] bdata = blob.getBytes(1, (int) blob.length());
                        b64 = new String(bdata);
                        
                    }
                    aux= new Tareas(rs.getInt(1), rs.getString(2), rs.getString(3), b64, rs.getInt(5));
                    tareas.add(aux);
                    System.out.println(aux.getTitulo());
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return tareas;
        
    }
}
