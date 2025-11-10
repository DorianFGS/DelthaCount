package Interfaces;

import DatosSecundarios.Cuentas;
public interface PDatos {
    Cuentas[] cargarCuentas();
    void guardarCuentas(Cuentas[] cuentas);
    
}
