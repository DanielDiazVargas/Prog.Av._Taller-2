package cl.ucn.disc.pa.Taller2.Util;

import java.util.Random;

public class GeneradorCodigo {
    private String caracteres;
    private StringBuilder cadenaAleatoria;

    public GeneradorCodigo() {
        this.caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        this.cadenaAleatoria = new StringBuilder();
    }

    public String generarCodigo(int largo) {
        Random codigo = new Random();

        for (int i = 0; i < largo; i++) {
            int indice = codigo.nextInt(caracteres.length());
            char caracterAleatorio = caracteres.charAt(indice);
            cadenaAleatoria.append(caracterAleatorio);
        }

        return cadenaAleatoria.toString();
    }
}
