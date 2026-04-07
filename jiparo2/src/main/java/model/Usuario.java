package model;

import java.util.*;


public class Usuario {
    private Long id_usuario = null;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String telefono;

    private String username;
    private String password;

    private List<Empresa> empresas;


    private String email;
    private String nombreCompleto;
    private String rol;
    private boolean estatus;

    // Activaciones
    private boolean activacion_contabilidad;
    private boolean activacion_nomina;
    private boolean activacion_inventario;
    private boolean activacion_facturacion;
    private boolean activacion_reportes;
    private boolean activacion_administracion;


    private int cantidad_licencias;

    //**********************************************
    public Usuario() {}

    public Usuario(Long idUsuario, String nombre, String apellidoPaterno,
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



    public boolean isActivacion_contabilidad() {
        return activacion_contabilidad;
    }

    public void setActivacion_contabilidad(boolean activacion_contabilidad) {
        this.activacion_contabilidad = activacion_contabilidad;
    }

    public boolean isActivacion_nomina() {
        return activacion_nomina;
    }

    public void setActivacion_nomina(boolean activacion_nomina) {
        this.activacion_nomina = activacion_nomina;
    }

    public boolean isActivacion_inventario() {
        return activacion_inventario;
    }

    public void setActivacion_inventario(boolean activacion_inventario) {
        this.activacion_inventario = activacion_inventario;
    }

    public boolean isActivacion_facturacion() {
        return activacion_facturacion;
    }

    public void setActivacion_facturacion(boolean activacion_facturacion) {
        this.activacion_facturacion = activacion_facturacion;
    }

    public boolean isActivacion_reportes() {
        return activacion_reportes;
    }

    public void setActivacion_reportes(boolean activacion_reportes) {
        this.activacion_reportes = activacion_reportes;
    }

    public boolean isActivacion_administracion() {
        return activacion_administracion;
    }

    public void setActivacion_administracion(boolean activacion_administracion) {
        this.activacion_administracion = activacion_administracion;
    }


    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }


    public String getTelefono(){
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public Long getId_usuario(){
        return this.id_usuario;
    }
    public void setId_usuario(Long id_usuario){
        this.id_usuario = id_usuario;
    }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isActivo() { return estatus; }
    public void setEstatus(boolean estatus) { this.estatus = estatus; }


    public int getCantidad_licencias() {
        return cantidad_licencias;
    }

    public void setCantidad_licencias(int cantidad_licencias) {
        this.cantidad_licencias = cantidad_licencias;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<Empresa> getEmpresas() { return empresas; }
    public void setEmpresas(List<Empresa> empresas) { this.empresas = empresas; }
}

