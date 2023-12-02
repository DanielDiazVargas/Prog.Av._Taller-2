package cl.ucn.disc.pa.Taller2.Util;

import cl.ucn.disc.pa.Taller2.Service.Sistema;
import cl.ucn.disc.pa.Taller2.Service.SistemaImpl;

public class Instalador {
    private Sistema sistemaApp;

    public Instalador() {
        this.sistemaApp = new SistemaImpl();
    }

    public Sistema instalarSistema() {
        return this.sistemaApp;
    }
}
