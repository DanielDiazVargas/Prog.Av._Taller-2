package cl.ucn.disc.pa.Taller2.Model;

public class NodoDoble {
    private Perfil perfil;
    private ContenedorListaNexoSimple mensajes;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    public NodoDoble(Perfil perfil, ContenedorListaNexoSimple mensajes) {
        this.perfil = perfil;
        this.mensajes = mensajes;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public ContenedorListaNexoSimple getListaMensajes() {
        return mensajes;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
}
