package cl.ucn.disc.pa.Taller2.Service;

import cl.ucn.disc.pa.Taller2.Model.ListaNexoDoble;
import cl.ucn.disc.pa.Taller2.Model.Perfil;

import java.io.IOException;

public interface Sistema {
    ListaNexoDoble registrarse(ListaNexoDoble perfiles, String nombreUsuario, String correo, String contrasenia, String tipoDePerfil) throws IOException;
    Perfil iniciarSesion(ListaNexoDoble perfiles, String nombreUsuario, String contrasenia);
    void aceptarConexcion();
    void rechazarConexion();
    void enviarMensaje();

}