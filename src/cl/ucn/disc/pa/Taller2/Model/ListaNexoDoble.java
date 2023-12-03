package cl.ucn.disc.pa.Taller2.Model;

import java.util.Objects;

public class ListaNexoDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;

    public ListaNexoDoble() {
        this.cabeza = null;
        this.cola = null;
    }

    public void agregar(Perfil perfil, ContenedorListaNexoSimple mensajes){
        NodoDoble nuevo = new NodoDoble(perfil, mensajes);

        if (this.cabeza == null) {
            this.cabeza = nuevo;
            this.cola = nuevo;
            return;
        }

        this.cola.setSiguiente(nuevo);
        nuevo.setAnterior(this.cola);

        this.cola = nuevo;
    }

    public void agregarMensaje(Perfil perfil, String mensaje, String destinatario) {
        ContenedorListaNexoSimple contenedor = obtenerContenedor(perfil);

        int posicion = -1;
        String[][] contactosAxu = contenedor.getContactos();

        for (int i = 0; i < tamanioPerfiles(); i++) {
            if (Objects.equals(contactosAxu[i][0], destinatario)) {
                posicion = i;
                break;
            }
        }

        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            if (Objects.equals(aux.getPerfil().getNombreDeUsuario(), perfil.getNombreDeUsuario())) {
                contenedor.agregarMensaje(posicion, perfil, mensaje);
            }
        }

        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            aux.setListaMensajes(contenedor);
        }
    }

    public int obtenerPosicion(String nombre) {
        int contador = 0;
        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            if (Objects.equals(aux.getPerfil().getNombreDeUsuario(), nombre)) {
                return contador;
            }

            contador++;
        }

        return -1;
    }

    public Perfil obtenerPerfilPosicion(int posicion){
        NodoDoble aux = this.cabeza;

        for (int i = 0; i < posicion; i++) {
            aux = aux.getSiguiente();
        }

        return aux.getPerfil();
    }

    public Perfil obtenerPerfilNombre(String nombre) {
        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            if (Objects.equals(aux.getPerfil().getNombreDeUsuario(), nombre)) {
                return aux.getPerfil();
            }
        }

        return null;
    }

    public ContenedorListaNexoSimple obtenerContenedor(Perfil perfil){
        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            if (aux.getPerfil().getNombreDeUsuario().equals(perfil.getNombreDeUsuario())) {
                return aux.getListaMensajes();
            }
        }

        return null;
    }

    public int tamanioPerfiles(){
        int contador = 0;
        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            contador ++;
        }
        return contador;
    }
}
