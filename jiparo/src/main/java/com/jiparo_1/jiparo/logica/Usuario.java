package com.jiparo_1.jiparo.logica;

public class Usuario {
    private int id_usuario;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
   
    private String username;
    private String password;
    
    
    private String email;
    private String nombreCompleto;
    private String rol;
    private boolean activo;
 
    
    
    public Usuario(){
        
    }
    
    // Constructor de prueba para el metodo GET
    public Usuario(String nombre, String apellido_paterno){
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
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
}



