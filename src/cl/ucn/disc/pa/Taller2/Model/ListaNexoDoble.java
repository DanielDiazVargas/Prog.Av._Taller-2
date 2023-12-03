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
        ContenedorListaNexoSimple contenedor1 = obtenerContenedor(perfil);
        ContenedorListaNexoSimple contenedor2 = obtenerContenedor(obtenerPerfilNombre(destinatario));

        int posicion1 = -1;
        int posicion2 = -1;
        String[][] contactos1 = contenedor1.getContactos();
        String[][] contactos2 = contenedor2.getContactos();
        boolean boolean1 = false;
        boolean boolean2 = false;

        for (int i = 0; i < tamanioPerfiles(); i++) {
            if (Objects.equals(contactos1[i][0], destinatario)) {
                posicion1 = i;
                boolean1 = true;
            }

            if (Objects.equals(contactos2[i][0], perfil.getNombreDeUsuario())) {
                posicion2 = i;
                boolean2 = true;
            }

            if (boolean1 && boolean2) {
                boolean1 = false;
                boolean2 = false;
                break;
            }
        }

        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            if (Objects.equals(aux.getPerfil().getNombreDeUsuario(), perfil.getNombreDeUsuario())) {
                contenedor1.agregarMensaje(posicion1, perfil, mensaje);
                boolean1 = true;
            }

            if (Objects.equals(aux.getPerfil().getNombreDeUsuario(), destinatario)) {
                contenedor2.agregarMensaje(posicion2, obtenerPerfilNombre(destinatario), mensaje);
                boolean2 = true;
            }

            if (boolean1 && boolean2) {
                boolean1 = false;
                boolean2 = false;
                break;
            }
        }

        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            if (Objects.equals(aux.getPerfil().getNombreDeUsuario(), perfil.getNombreDeUsuario())) {
                aux.setListaMensajes(contenedor1);
                boolean1 = true;
            }

            if (Objects.equals(aux.getPerfil().getNombreDeUsuario(), destinatario)) {
                aux.setListaMensajes(contenedor2);
                boolean2 = true;
            }

            if (boolean1 && boolean2) {
                return;
            }
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
