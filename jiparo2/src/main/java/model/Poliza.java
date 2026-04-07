package model;

import java.time.LocalDate;
import java.util.List;

public class Poliza {

    private Long id_poliza;
    private Long id_cuenta;

    private int numero_poliza;
    private LocalDate fecha;
    private String tipo;
    private String concepto;

    private double cargo;
    private double abono;
    private double diferencia;

    private boolean revisada;

    private List <MovimientoCuenta> movimientosCuentas;
}
