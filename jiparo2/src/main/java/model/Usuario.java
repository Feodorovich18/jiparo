package model;

import java.util.*;


public class Usuario {
    private int id_usuario;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;

    private String username;
    private String password;

    private List<Empresa> empresas;


    private String email;
    private String nombreCompleto;
    private String rol;
    private boolean activo;

    // Activaciones
    private boolean activacion_contabilidad;
    private boolean activacion_nomina;
    private boolean activacion_inventario;
    private boolean activacion_facturacion;
    private boolean activacion_reportes;
    private boolean activacion_administracion;



    public Usuario() {}

    public Usuario(int idUsuario, String nombre, String apellidoPaterno,
                   String apellidoMaterno, String email) {
        this.id_usuario = idUsuario;
        this.nombre = nombre;
        this.apellido_paterno = apellidoPaterno;
        this.apellido_materno = apellidoMaterno;
        this.email = email;
        this.empresas = new ArrayList<>();
    }


    /**********
     * GETTERS/SETTERS
     ***********/
    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombreCompleto() { return nombreCompleto; }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public List<Empresa> getEmpresas() { return empresas; }
    public void setEmpresas(List<Empresa> empresas) { this.empresas = empresas; }
}

