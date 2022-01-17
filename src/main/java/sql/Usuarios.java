package sql;

public class Usuarios {
    private int id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String clave;
    public Usuarios(int id, String nombres, String apellidos, String correo, String clave, int edad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.clave = clave;
        this.edad = edad;
    }
    private int edad;
    public int getId() {
        return id;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setId(int id) {
        this.id = id;
    }
    
}
