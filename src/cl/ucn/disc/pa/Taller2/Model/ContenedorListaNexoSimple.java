package cl.ucn.disc.pa.Taller2.Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ContenedorListaNexoSimple {
    private ListaNexoSimple[] vectorMensajes;
    private String[][] contactos;
    private int cantidadListas = 0;
    private int cantMaxima;

    public ContenedorListaNexoSimple(int cantMaxima) {
        this.cantMaxima = cantMaxima;
        this.vectorMensajes = new ListaNexoSimple[this.cantMaxima];
        this.contactos = new String[this.cantMaxima][3];
    }

    public void agregarContacto(String nombreContacto, String estado,String codigo, ListaNexoSimple mensajes) {
        this.contactos[cantidadListas][0] = nombreContacto;
        this.contactos[cantidadListas][1] = estado;
        this.contactos[cantidadListas][2] = codigo;
        this.vectorMensajes[cantidadListas] = mensajes;
        this.cantidadListas++;
    }

    public void agregarMensaje(int posicion, Perfil perfil, String mensaje) {
        String codigo = contactos[posicion][2];
        String destinatario = contactos[posicion][0];
        String remitente = perfil.getNombreDeUsuario();
        LocalTime horaDeEnvio = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));

        Mensaje nuevoMensaje = new Mensaje(codigo, destinatario, remitente, horaDeEnvio, mensaje);
        this.vectorMensajes[posicion].agregar(nuevoMensaje);
    }

    public ListaNexoSimple[] getListaNexoSimple() {
        return vectorMensajes;
    }

    public String[][] getContactos() {
        return this.contactos;
    }
}
