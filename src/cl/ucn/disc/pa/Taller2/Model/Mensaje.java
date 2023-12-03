package cl.ucn.disc.pa.Taller2.Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Mensaje {
    private String codigo;
    private String destinatario;
    private String remitente;
    private LocalTime horaDeEnvio;
    private String mensaje;

    public Mensaje(String codigo, String destinatario, String remitente, LocalTime horaDeEnvio, String mensaje) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.horaDeEnvio = LocalTime.parse(horaDeEnvio.format(DateTimeFormatter.ofPattern("HH:mm")));
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDestinatario() {
        return this.destinatario;
    }

    public String getRemitente() {
        return this.remitente;
    }

    public LocalTime getHoraDeEnvio() {
        return this.horaDeEnvio;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
