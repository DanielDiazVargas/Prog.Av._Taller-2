package cl.ucn.disc.pa.Taller2.Service;

import cl.ucn.disc.pa.Taller2.Model.ListaNexoDoble;
import cl.ucn.disc.pa.Taller2.Model.Perfil;

public class SistemaImpl implements Sistema {
    @Override
    public ListaNexoDoble registrarse(ListaNexoDoble perfiles, String nombreUsuario, String correo, String contrasenia, String tipoDePerfil) {
        Perfil nuevo = new Perfil(nombreUsuario, correo, contrasenia, tipoDePerfil);
        perfiles.agregar(nuevo, null);
        return perfiles;
    }

    @Override
    public Perfil iniciarSesion(ListaNexoDoble perfiles, String nombreUsuario, String contrasenia) {
        for (int i = 0; i < perfiles.tamanioPerfiles(); i++) {
            String nombre = perfiles.obtenerPerfilPosicion(i).getNombreDeUsuario();
            String contraseniaAux = perfiles.obtenerPerfilPosicion(i).getContrasenia();
            if (perfiles.obtenerPerfilPosicion(i).getNombreDeUsuario().equals(nombreUsuario) && perfiles.obtenerPerfilPosicion(i).getContrasenia().equals(contrasenia)) {
                return perfiles.obtenerPerfilPosicion(i);
            }
        }

        return null;
    }

    @Override
    public void aceptarConexcion() {

    }

    @Override
    public void rechazarConexion() {

    }

    @Override
    public void enviarMensaje() {

    }
}
