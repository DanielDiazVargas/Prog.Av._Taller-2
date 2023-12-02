package cl.ucn.disc.pa.Taller2.Model;

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
    }

    public String[][] getContactos() {
        return this.contactos;
    }
}
