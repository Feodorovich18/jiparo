package model;

public class Empresa {

    private Long id_empresa = null;
    private String nombre_empresa;
    private String rfc;
    private String direccion_fiscal;
    private int id_usuario;


    //**********************************************************************************************************
    public Empresa(Long id_empresa, String nombre_empresa, String rfc, String direccion_fiscal, int id_usuario) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
        this.rfc = rfc;
        this.direccion_fiscal = direccion_fiscal;
        this.id_usuario = id_usuario;
    }

    /****************
    * Getters y Setters
     ***************/
    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getDireccion_fiscal() {
        return direccion_fiscal;
    }

    public void setDireccion_fiscal(String direccion_fiscal) {
        this.direccion_fiscal = direccion_fiscal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    //**************************************************************
}
