package cl.ucn.disc.pa.Taller2.Model;

public class ListaNexoSimple {
    private NodoSimple cabeza;

    public ListaNexoSimple() {
        this.cabeza = null;
    }

    public void agregar(Mensaje mensaje){
        NodoSimple nuevo = new NodoSimple(mensaje);

        if (this.cabeza == null) {
            this.cabeza = nuevo;
            return;
        }

        NodoSimple ultimoNodo = this.cabeza;
        while (ultimoNodo.getSiguiente() != null) {
            ultimoNodo = ultimoNodo.getSiguiente();
        }

        ultimoNodo.setSiguiente(nuevo);
    }

    public Mensaje obtenerMensaje(int posicion) {
        NodoSimple aux = this.cabeza;

        for (int i = 0; i < posicion; i++) {
            aux = aux.getSiguiente();
        }

        return aux.getMensaje();
    }

    public int tamanioMensajes() {
        int contador = 0;
        for (NodoSimple aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            contador ++;
        }
        return contador;
    }
}
