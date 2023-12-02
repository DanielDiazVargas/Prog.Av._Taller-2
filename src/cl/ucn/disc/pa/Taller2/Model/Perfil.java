package cl.ucn.disc.pa.Taller2.Model;

public class Perfil {
    private String nombreDeUsuario;
    private String correoElectronico;
    private String contrasenia;
    private String tipoDePerfil;

    public Perfil(String nombreDeUsuario, String correoElectronico, String contrasenia, String tipoDePerfil) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.tipoDePerfil = tipoDePerfil;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTipoDePerfil() {
        return tipoDePerfil;
    }
}
