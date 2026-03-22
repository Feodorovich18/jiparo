package model;

import java.util.List;

public class Catalogo {
    private int id_catalogo;
    private String nombre_catalogo;
    private int id_empresa;
    private int anio_fiscal;

    private boolean activo;

    private List<Cuenta> cuentas;

}
