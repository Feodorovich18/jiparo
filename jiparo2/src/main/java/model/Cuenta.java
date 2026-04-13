package model;

import java.util.List;

public class Cuenta {

    private Long id_cuenta;

    private String codigo_completo_cuenta;
    private String naturaleza;

    private boolean estatus;
    private int id_catalogo;

    private String codigo_agrupador_sat;


    private String codigo_rubro;
    private String denominacion_rubro;

    private String codigo_cuenta;
    private String denominacion_cuenta;

    private String codigo_subcuenta;
    private String denominacion_subcuenta;

    private String codigo_subsubcuenta;
    private String denominacion_subsubcuenta;

    private List<Poliza> polizas;





    /**********************************************
    GETTERS/SETTERS
     **********************************************/
    public Long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }



    public String getCodigo_completo_cuenta() {
        return codigo_completo_cuenta;
    }

    public void setCodigo_completo_cuenta(String codigo_completo_cuenta) {
        this.codigo_completo_cuenta = codigo_completo_cuenta;
    }



    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
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


    public boolean isEstatus() {
        return estatus;
    }

    public String getCodigo_rubro() {
        return codigo_rubro;
    }

    public void setCodigo_rubro(String codigo_rubro) {
        this.codigo_rubro = codigo_rubro;
    }

    public String getDenominacion_rubro() {
        return denominacion_rubro;
    }

    public void setDenominacion_rubro(String denominacion_rubro) {
        this.denominacion_rubro = denominacion_rubro;
    }

    public String getCodigo_cuenta() {
        return codigo_cuenta;
    }

    public void setCodigo_cuenta(String codigo_cuenta) {
        this.codigo_cuenta = codigo_cuenta;
    }

    public String getDenominacion_cuenta() {
        return denominacion_cuenta;
    }

    public void setDenominacion_cuenta(String denominacion_cuenta) {
        this.denominacion_cuenta = denominacion_cuenta;
    }

    public String getCodigo_subcuenta() {
        return codigo_subcuenta;
    }

    public void setCodigo_subcuenta(String codigo_subcuenta) {
        this.codigo_subcuenta = codigo_subcuenta;
    }

    public String getDenominacion_subcuenta() {
        return denominacion_subcuenta;
    }

    public void setDenominacion_subcuenta(String denominacion_subcuenta) {
        this.denominacion_subcuenta = denominacion_subcuenta;
    }

    public String getCodigo_subsubcuenta() {
        return codigo_subsubcuenta;
    }

    public void setCodigo_subsubcuenta(String codigo_subsubcuenta) {
        this.codigo_subsubcuenta = codigo_subsubcuenta;
    }

    public String getDenominacion_subsubcuenta() {
        return denominacion_subsubcuenta;
    }

    public void setDenominacion_subsubcuenta(String denominacion_subsubcuenta) {
        this.denominacion_subsubcuenta = denominacion_subsubcuenta;
    }

    public List<Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<Poliza> polizas) {
        this.polizas = polizas;
    }
}
