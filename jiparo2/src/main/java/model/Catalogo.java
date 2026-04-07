package model;

import java.time.LocalDate;
import java.util.List;

public class Catalogo {
    private Long id_catalogo;
    private String nombre_catalogo;
    private LocalDate fecha_creacion;
    private boolean estatus;
    private int anio_fiscal;

    private Long id_empresa;

    private String estructura;

    private List<Cuenta> cuentas;


    /***************************************
    GETTERS/SETTERS
     *************************************/
    public Long getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(Long id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getNombre_catalogo() {
        return nombre_catalogo;
    }

    public void setNombre_catalogo(String nombre_catalogo) {
        this.nombre_catalogo = nombre_catalogo;
    }

    public boolean getEstatus() {
        return this.estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
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

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
