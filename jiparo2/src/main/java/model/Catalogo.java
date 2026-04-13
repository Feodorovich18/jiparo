package model;

import java.time.LocalDate;
import java.util.List;

public class Catalogo {

    private Long id_catalogo;
    private Long id_empresa;

    private LocalDate fecha_creacion;
    private int anio_fiscal;

    private boolean estatus;


    private String estructura_rubro;
    private String estructura_cuenta;
    private String estructura_subcuenta;
    private String estructura_subsubcuenta;


    private List<Cuenta> cuentas;


    public Catalogo(){

    }


    /*********************
    * GETTERS/SETTERS
     *********************/
    public Long getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(Long id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getAnio_fiscal() {
        return anio_fiscal;
    }

    public void setAnio_fiscal(int anio_fiscal) {
        this.anio_fiscal = anio_fiscal;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }





    public String getEstructura_rubro() {
        return estructura_rubro;
    }
    public void setEstructura_rubro(String estructura_rubro) {
        this.estructura_rubro = estructura_rubro;
    }

    public String getEstructura_cuenta() {
        return estructura_cuenta;
    }
    public void setEstructura_cuenta(String estructura_cuenta) {
        this.estructura_cuenta = estructura_cuenta;
    }

    public String getEstructura_subcuenta() {
        return estructura_subcuenta;
    }
    public void setEstructura_subcuenta(String estructura_subcuenta) {
        this.estructura_subcuenta = estructura_subcuenta;
    }

    public String getEstructura_subsubcuenta() {
        return estructura_subsubcuenta;
    }
    public void setEstructura_subsubcuenta(String estructura_subsubcuenta) {
        this.estructura_subsubcuenta = estructura_subsubcuenta;
    }



    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
