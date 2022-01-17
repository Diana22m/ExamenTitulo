package mx.uv.examenTitulo;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import sql.Tareas;
import sql.TareasDAO;
import sql.Usuarios;
import sql.UsuariosDAO;

import com.google.gson.Gson;



public class App 
{
    public static void main( String[] args )
    {
        port(getHerokuAssignedPort());
        staticFiles.location("/");
        init();
        
        get("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/login.vm");
        }, new VelocityTemplateEngine());

        get("/registrarme", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/registrarme.vm");
        }, new VelocityTemplateEngine());

        get("/main", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            
           
            return new ModelAndView(model, "/main.vm");
        }, new VelocityTemplateEngine());

        get("/nuevaTarea", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            
           
            return new ModelAndView(model, "/nuevaTarea.vm");
        }, new VelocityTemplateEngine());

        get("/usuarioByCorreo", (req, res) -> {
            Usuarios u = null;
            Gson gson=new Gson();
            String correo = req.queryParams("correo");
            u=UsuariosDAO.getUsuariosByCorreo(correo);
            String jsonU=gson.toJson(u);
            return jsonU;
        });
        
        get("/tareasByUsuario", (req, res) -> {
            Gson gson=new Gson();
            int idUsuario = Integer.parseInt(req.queryParams("idUsuario"));
            List <Tareas> tareas=TareasDAO.getTareasByUsuario(idUsuario);
            String jsonTareas=gson.toJson(tareas);
            return jsonTareas;
        });

        post("/usuarioNuevo", (req, res) -> {
            Gson gson=new Gson();
            Usuarios usuarioNew = gson.fromJson(req.body(), Usuarios.class);

            if (UsuariosDAO.registrarUsuario(usuarioNew)) {
                return "SI";
            } else {
                return "NO";
            }
            
        });
        post("/tareaNueva", (req, res) -> {
            Gson gson=new Gson();
            Tareas tareaNew = gson.fromJson(req.body(), Tareas.class);
            if (TareasDAO.registrarTarea(tareaNew)) {
                return "SI";
            } else {
                return "NO";
            }
            
        });
        post("/acceso", (req, res) -> {
            Gson gson=new Gson();
            Usuarios usuarioNew = gson.fromJson(req.body(), Usuarios.class);
            Usuarios u = UsuariosDAO.getUsuariosByCorreoAndClave(usuarioNew.getCorreo(), usuarioNew.getClave());
            if (u!=null) {
                return "SI";
            } else {
                return "NO";
            }
            
        });

    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
