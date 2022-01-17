package sql;



public class Tareas {
    private int idTarea;
    private String titulo;
    private String contenido;
    private String imagen;
    private int idUsuario;
    
    public Tareas(int idTarea, String titulo, String contenido, String imagen, int idUsuario) {
        this.idTarea = idTarea;
        this.titulo = titulo;
        this.contenido = contenido;
        this.imagen = imagen;
        this.idUsuario = idUsuario;
    }
    public int getIdTarea() {
        return idTarea;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }
    
    

}
