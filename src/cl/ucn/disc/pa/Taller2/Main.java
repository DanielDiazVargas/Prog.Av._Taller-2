package cl.ucn.disc.pa.Taller2;

import cl.ucn.disc.pa.Taller2.Model.*;
import cl.ucn.disc.pa.Taller2.Service.Sistema;
import cl.ucn.disc.pa.Taller2.Util.Instalador;
import ucn.*;

import java.io.*;
import java.time.LocalTime;

public class Main {
    public static Sistema sistemaApp;
    public static ListaNexoDoble perfiles = new ListaNexoDoble();

    public static void main(String[] args) throws Exception {
        configuracion();
        perfiles = lecturaArchivos(perfiles);
        menuInicial();
        escrituraArchivos();
    }

    public static void configuracion() throws IOException {
        sistemaApp = instalarSistema();
    }

    public static Sistema instalarSistema() {
        return new Instalador().instalarSistema();
    }

    public static void menuInicial() throws Exception {
        String opcion;
        boolean menu = true;

        while (menu) {
            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *SISTEMA*
                    Elija una opcion:
                    [1] Iniciar Sesion
                    [2] Registrarse
                    [3] Salir
                    ::::::::::::::::::::::::::::::""");
            StdOut.print("=> ");
            opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> {
                    Perfil perfil = iniciarSesion();
                    if (perfil != null) {
                        menu(perfil);
                    }
                }
                case "2" -> registrarse();
                case "3" -> menu = false;
                default -> StdOut.println("¡Opcion no valida!");
            }
        }
    }

    public static void registrarse() throws Exception {
        String nombreUsuario;
        String contrasenia;
        String correo;
        String tipoDePerfil = null;

        while (true) {
            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *REGISTRO*
                    Ingrese un Nombre de Usuario
                    ::::::::::::::::::::::::::::::""");
            StdOut.print("=> ");
            nombreUsuario = StdIn.readString();

            boolean igual = false;
            for (int i = 0; i < perfiles.tamanioPerfiles(); i++) {
                if (nombreUsuario.equals(perfiles.obtenerPerfilPosicion(i).getNombreDeUsuario())) {
                    igual = true;
                }
            }

            if (igual) {
                StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *REGISTRO*
                    Nombre de Usuario ya existente
                    ::::::::::::::::::::::::::::::""");
            }else {
                break;
            }
        }

        StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *REGISTRO*
                    Ingrese una Contrasenia
                    ::::::::::::::::::::::::::::::""");
        StdOut.print("=> ");
        contrasenia = StdIn.readString();

        StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *REGISTRO*
                    Ingrese un Correo Electronico
                    ::::::::::::::::::::::::::::::""");
        StdOut.print("=> ");
        correo = StdIn.readString();

        String opcion;
        boolean menu = true;

        while (menu) {
            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *REGISTRO*
                    Elija el Tipo de Cuenta
                    [1] Comun
                    [2] Empresa
                    ::::::::::::::::::::::::::::::""");
            StdOut.print("=> ");
            opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> {
                    tipoDePerfil = "comun";
                    menu = false;
                }
                case "2" -> {
                    tipoDePerfil = "empresa";
                    menu = false;
                }
                default -> StdOut.println("¡Opcion no valida!");
            }
        }

        perfiles = sistemaApp.registrarse(perfiles, nombreUsuario, correo, contrasenia, tipoDePerfil);

        escrituraArchivos();
        iniciarSesion();
    }

    public static Perfil iniciarSesion(){

        while (true) {
            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                          *INICIO DE SESION*
                    Ingrese un Nombre de Usuario
                    ::::::::::::::::::::::::::::::""");
            StdOut.print("=> ");
            String usuario = StdIn.readString();

            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                          *INICIO DE SESION*
                    Ingrese la Contrasenia
                    ::::::::::::::::::::::::::::::""");
            StdOut.print("=> ");
            String contrasenia = StdIn.readString();

            Perfil perfil = sistemaApp.iniciarSesion(perfiles, usuario, contrasenia);
            if (perfil != null) {
                StdOut.println("""
                    ::::::::::::::::::::::::::::::
                          *INICIO DE SESION*
                    Ingreso exitoso
                    ::::::::::::::::::::::::::::::""");
                return perfil;
            }

            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                          *INICIO DE SESION*
                    Usuario o Contrasenia incorrectos
                    ::::::::::::::::::::::::::::::""");
            return null;
        }
    }

    public static void menu(Perfil perfil) throws IOException {
        String opcion;
        boolean menu = true;

        while (menu) {
            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *SISTEMA*
                    Elija una opcion:
                    [1] Enviar Mensaje
                    [2] Ver Contactos
                    [3] Solicitudes Pendientes
                    [4] Buscar Perfiles
                    [5] Cerrar Sesion
                    ::::::::::::::::::::::::::::::""");
            StdOut.print("=> ");
            opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> enviarMensaje(perfil);
                case "2" -> verContactos(perfil);
                case "3" -> solitudesPendientes();
                case "4" -> buscarPerfiles();
                case "5" -> menu = false;
                default -> StdOut.println("¡Opcion no valida!");
            }
        }
    }

    public static void enviarMensaje(Perfil perfil) throws IOException {
        StdOut.println("""
                    ::::::::::::::::::::::::::::::
                           *ENVIAR MENSAJE*
                    Ingrese el nombre del Destinatario
                    ::::::::::::::::::::::::::::::""");
        StdOut.print("=> ");
        String nombre = StdIn.readString();

        if (nombre == perfil.getNombreDeUsuario()) {
            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                           *ENVIAR MENSAJE*
                    No puede inviar un mensaje a si mismo
                    ::::::::::::::::::::::::::::::""");
            return;
        }

        boolean existe = false;
        for (int i = 0; i < perfiles.tamanioPerfiles(); i++) {
            if (perfiles.obtenerPerfilPosicion(i).getNombreDeUsuario().equals(nombre)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            StdOut.println("""
                    ::::::::::::::::::::::::::::::
                           *ENVIAR MENSAJE*
                    El usuario que ingreso no existe
                    ::::::::::::::::::::::::::::::""");
            return;
        }

        ArchivoEntrada archivoEntrada = null;

        String nombreArchivo = "Perfiles/Conexiones/" + perfil.getNombreDeUsuario() + "-" + nombre + ".txt";
        File file = new File(nombreArchivo);
        if (file.exists()) {
            archivoEntrada = new ArchivoEntrada(nombreArchivo);
        }else {
            nombreArchivo = "Perfiles/Conexiones/" + nombre + "-" + perfil.getNombreDeUsuario() + ".txt";
            file = new File(nombreArchivo);
            archivoEntrada = new ArchivoEntrada(nombreArchivo);
        }

        while (!archivoEntrada.isEndFile()) {
            Registro registro = archivoEntrada.getRegistro();
            String estado = registro.getString();
            if (estado != "aceptada") {
                StdOut.println("""
                    ::::::::::::::::::::::::::::::
                           *ENVIAR MENSAJE*
                    La solicitud de conexion no ha sido aceptada
                    ::::::::::::::::::::::::::::::""");
                return;
            }
        }

        StdOut.println("""
                    ::::::::::::::::::::::::::::::
                           *ENVIAR MENSAJE*
                    Ingrese el mensaje
                    ::::::::::::::::::::::::::::::""");
        StdOut.print("=> ");
        String mensaje = StdIn.readString();
    }

    public static void verContactos(Perfil perfil) {
        String[][] contactos = perfiles.obtenerContenedor(perfil).getContactos();

        StdOut.println("""
                    ::::::::::::::::::::::::::::::
                              *CONSTACTOS*""");

        for (int i = 0; i < contactos.length; i++) {
            if (contactos[i][0] != null) {
                StdOut.println("[" + (i + 1) + "] " + contactos[i][0] + " | Estado: " + contactos[i][1]);
            }else {
                if (i == 0) {
                    StdOut.println("No tiene contactos agregados");
                }
                break;
            }
        }

        StdOut.println("::::::::::::::::::::::::::::::");
    }

    public static void solitudesPendientes() {

    }

    public static void buscarPerfiles() {

    }

    public static ListaNexoDoble lecturaArchivos(ListaNexoDoble perfiles) throws IOException {
        try {
            ArchivoEntrada archivoEntrada = new ArchivoEntrada("Perfiles/Perfiles.txt");
            ListaNexoSimple mensajes = new ListaNexoSimple();

            while (!archivoEntrada.isEndFile()) {
                Registro nuevoPerfil = archivoEntrada.getRegistro();
                String nombreUsuario = nuevoPerfil.getString();
                String correo = nuevoPerfil.getString();
                String contrasenia = nuevoPerfil.getString();
                String tipoDePerfil = nuevoPerfil.getString();

                ContenedorListaNexoSimple contenedor = new ContenedorListaNexoSimple(999);

                while (true) {
                    String codigo = nuevoPerfil.getString();
                    String contacto;
                    String estado;


                    if (codigo != null) {
                        ArchivoEntrada archivoMensaje = new ArchivoEntrada("Perfiles/Conexiones/" + codigo + ".txt");
                        Registro registroMensaje = archivoMensaje.getRegistro();

                        if (registroMensaje.getString().equals("aceptado")) {
                            estado = "aceptado";
                            contacto = registroMensaje.getString();
                            if (contacto.equals(nombreUsuario)) {
                                contacto = registroMensaje.getString();
                            }

                            while (!archivoMensaje.isEndFile()) {
                                registroMensaje = archivoMensaje.getRegistro();
                                String destinatario = registroMensaje.getString();
                                String remitente = registroMensaje.getString();
                                LocalTime horaDeEnvio = LocalTime.parse(registroMensaje.getString());
                                String mensaje = registroMensaje.getString();

                                Mensaje nuevoMensaje = new Mensaje(codigo, destinatario, remitente, horaDeEnvio, mensaje);
                                mensajes.agregar(nuevoMensaje);
                            }
                        }else {
                            estado = "pendiente";
                            contacto = registroMensaje.getString();
                            if (contacto.equals(nombreUsuario)) {
                                contacto = nuevoPerfil.getString();
                            }
                        }

                    }else {
                        break;
                    }

                    contenedor.agregarContacto(contacto, estado, codigo, mensajes);
                }

                perfiles.agregar(new Perfil(nombreUsuario, correo, contrasenia, tipoDePerfil), contenedor);
            }

            archivoEntrada.close();

        }catch (IOException e) {
            File carpeta = new File("Perfiles/Conexiones");
            carpeta.mkdirs();
            carpeta = new File("Perfiles");
            File archivo = new File(carpeta, "Perfiles.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo));
        }



        return perfiles;
    }

    public static void escrituraArchivos() throws Exception {
        /*
        ArchivoSalida archivoSalida = new ArchivoSalida("Perfiles/Perfiles.txt");

        for (int i = 0; i < perfiles.tamanioPerfiles(); i++) {
            Registro linea = new Registro(999);
            linea.agregarCampo(perfiles.obtenerPerfil(i).getNombreDeUsuario());
            linea.agregarCampo(perfiles.obtenerPerfil(i).getCorreoElectronico());
            linea.agregarCampo(perfiles.obtenerPerfil(i).getContrasenia());
            linea.agregarCampo(perfiles.obtenerPerfil(i).getTipoDePerfil());
            int contador = 0;
            while (true) {
                if (contactos[contador] != null) {
                    linea.agregarCampo(contactos[contador].toString());
                }else {
                    break;
                }
                contador ++;
            }

            archivoSalida.writeRegistro(linea);
        }

        archivoSalida.close();

         */
    }
}