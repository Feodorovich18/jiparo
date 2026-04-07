package model;

public class Empresa {

    private Long id_empresa = null;
    private int numero_empresa;
    private String nombre_empresa;
    private String rfc;
    private String direccion_fiscal;
    private int id_usuario;
    private String descripcion;

    //**********************************************************************************************************
    public Empresa(){
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

    public int getNumero_empresa(){return this.numero_empresa;}

    public void setNumero_empresa(int numero_empresa){
        this.numero_empresa = numero_empresa;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    //**************************************************************
}
