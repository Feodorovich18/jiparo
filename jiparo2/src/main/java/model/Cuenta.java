package model;

import java.util.List;

public class Cuenta {

    private Long id_cuenta;
    private String nombre_cuenta;
    private String concepto;
    private String codigo_cuenta;
    private String tipo_cuenta;
    private String naturaleza;
    private int nivel;
    private boolean estatus;
    private int id_catalogo;

    private String codigo_agrupador_sat;

    private List<Poliza> polizas;

    public Cuenta(){

    }


    public Cuenta(Long id_cuenta, String nombre_cuenta, String concepto, String codigo_cuenta, String tipo_cuenta, String naturaleza, int nivel, int id_catalogo) {
        this.id_cuenta = id_cuenta;
        this.nombre_cuenta = nombre_cuenta;
        this.concepto = concepto;
        this.codigo_cuenta = codigo_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.naturaleza = naturaleza;
        this.nivel = nivel;
        this.id_catalogo = id_catalogo;
    }

    /**********************************************
    GETTERS/SETTERS
     **********************************************/
    public Long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

    public String getCodigo_cuenta() {
        return codigo_cuenta;
    }

    public void setCodigo_cuenta(String codigo_cuenta) {
        this.codigo_cuenta = codigo_cuenta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean getEstatus(){
        return this.estatus;
    }
    public void setEstatus(boolean estatus){
        this.estatus = estatus;
    }

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getCodigo_agrupador_sat(){
        return this.codigo_agrupador_sat;
    }
    public void setCodigo_agrupador_sat(String codigo_agrupador_sat){
        this.codigo_agrupador_sat = codigo_agrupador_sat;
    }
}
