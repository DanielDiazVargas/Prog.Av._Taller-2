package cl.ucn.disc.pa.Taller2.Model;

public class NodoSimple {
    private Mensaje mensaje;
    private NodoSimple siguiente;

    public NodoSimple(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }
}
