import java.util.ArrayList;

public class Gestion {

    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static ArrayList<Mensaje> listaMensajes = new ArrayList<>();
    
    public static void crearUsuario(){

        String nombreNuevo;
        String apellidoNuevo;
        String correoElectronicoNuevo;
        String contraseñaNueva;
        boolean error = true;

        do {
        System.out.println(" -- Crear usuario --");
        System.out.println("Escriba el nombre del nuevo usuario: ");
        nombreNuevo = App.lector.nextLine();
        System.out.println("Escriba el apellido del nuevo usuario: ");
        apellidoNuevo = App.lector.nextLine();
        System.out.println("Escriba el correo electrónico del nuevo usuario: ");
        correoElectronicoNuevo = App.lector.nextLine();
        System.out.println("Escriba la contraseña del nuevo usuario: ");
        contraseñaNueva = App.lector.nextLine();

        if(!existeUsuario(correoElectronicoNuevo)){
            error = false;
            Usuario usuario = new Usuario(nombreNuevo, apellidoNuevo, correoElectronicoNuevo, contraseñaNueva);
            listaUsuarios.add(usuario);
            System.out.println("Usuario creado correctamente.");
        } else {
            error = true;
            System.out.println("Ese usuario ya existe");
        }
        } while (error); 
    }

    private static void menuSecundario(Usuario usuario){

        boolean fallo2 = true;
        String opcionLeida2;
        int opcion2 = -1;

        do {
            try {
            System.out.println("-- Menú secundario --");
            System.out.println("1. Leer mensajes");
            System.out.println("2. Enviar mensajes");
            System.out.println("3. Agregar amigo");
            System.out.println("4. Eliminar amigo");
            System.out.println("5. Ver lista amigos");
            System.out.println("6. Borrar cuenta");
            System.out.println("7. Cerrar sesión");
            System.out.println("Escriba una de las opciones anteriores: ");

            opcionLeida2 = App.lector.nextLine();
            opcion2 = Integer.parseInt(opcionLeida2);

            if (opcion2 >=1 && opcion2 <=7){
                fallo2 = false;
            } else {
                fallo2 = true;
                System.out.println("Esa opción no existe");
            }
            } catch (Exception e) {
                fallo2 = true;
                System.out.println("Valor inválido");
            }
            switch (opcion2) {
                case 1:
                   leerMensaje(usuario);; 
                    break;
                case 2:
                   enviarMensaje(usuario); 
                    break;
                case 3:
                    agregarAmigo(usuario);
                    break;
                case 4:
                    eliminarAmigo(usuario);
                    break;
                case 5:
                    listaAmigos(usuario);
                    break;

                case 6:
                    borrarCuenta(usuario);
                    break;

                case 7:
                    cerrarSesion(usuario); 
                    break;
            }
        } while (fallo2 || opcion2 !=7);
    }

    public static void borrarCuenta(Usuario usuario){

        String confirmacionBorrado;
        boolean error2 = true;

        System.out.println("-- Borrar usuario --");
        System.out.println("Escriba BORRAR para eliminar su cuenta: ");
        confirmacionBorrado = App.lector.nextLine();

        do {
            if (confirmacionBorrado.equals("BORRAR")){
                error2 = false;
                listaUsuarios.remove(usuario);
                System.out.println("Cuenta eliminada correctamente.");
                    for (Usuario usuariosAmigosBorrar : listaUsuarios){
                        if (usuariosAmigosBorrar.getListaAmigos().contains(usuario)){
                            usuariosAmigosBorrar.getListaAmigos().remove(usuario); 
                        }
                    }
                App.menuPrincipal();
            } else {
                error2 = true;
            System.out.println("Valor inválido ");
         }
        } while (error2);
    }

    public static void iniciarSesion(){
        String correoElectronicoIntroducido;
        String contraseñaIntroducida;
        
        System.out.println("-- Inicio de sesión --");
        System.out.println("Escriba su correo electrónico: ");
        correoElectronicoIntroducido = App.lector.nextLine();
        System.out.println("Escriba su contraseña: ");
        contraseñaIntroducida = App.lector.nextLine();

        for(Usuario usuario : listaUsuarios){
            if(usuario.getCorreoElectronico().equals(correoElectronicoIntroducido) && usuario.getContraseña().equals(contraseñaIntroducida)){
            System.out.println("Sesión iniciada correctamente");
            menuSecundario(usuario);
            }
        }
            System.out.println("Esa cuenta no existe");
    }

    
    public static void cerrarSesion(Usuario usuario){

        String confirmacionSalir;
        boolean error3 = true;

        do {
            System.out.println("Escriba 'SALIR' para cerrar sesión y volver al menú principal: ");
            confirmacionSalir = App.lector.nextLine();
            if (confirmacionSalir.equals("SALIR")){
                error3 = false;
                App.menuPrincipal();

            } else {
                error3 = true;
                System.out.println("Valor inválido.");
            }
        } while (error3);

    }
    public static void enviarMensaje(Usuario usuario){

        String mensajeEnviar;
        String remitente = usuario.getCorreoElectronico();
        String destinatario;

        System.out.println("Escriba su mensaje: ");
        mensajeEnviar = App.lector.nextLine();
        System.out.println("Escriba el correo electrónico del destinatario: ");
        destinatario = App.lector.nextLine();

            if (usuarioEsAmigo(usuario, destinatario)){ 
                for (Usuario amigosEnLista : usuario.getListaAmigos()){
                    if (amigosEnLista.getCorreoElectronico().equals(destinatario)){
                        Mensaje mensaje = new Mensaje(mensajeEnviar, remitente, destinatario);
                        listaMensajes.add(mensaje);
                        System.out.println("Mensaje enviado correctamente.");
                    }
                }   
            } else { 
                System.out.println("Ese usuario no está en su lista de amigos."); 
            }
    }

    public static void leerMensaje(Usuario usuario){

        System.out.println("Mensajes recibidos: ");
        for (Mensaje mensaje : listaMensajes){
            if (mensaje.getDestinatario().equals(usuario.getCorreoElectronico())){
                System.out.println("- " + mensaje.getMensaje() + " - de: " + mensaje.getRemitente());
            }
        }
    }

    public static void agregarAmigo(Usuario usuario){
        String correoAmigoNuevo;

        System.out.println("Escriba el correo del amigo que quiere agregar: ");
        correoAmigoNuevo = App.lector.nextLine();

        if(existeUsuario(correoAmigoNuevo) && !usuarioEsAmigo(usuario, correoAmigoNuevo)){
            for (Usuario usuarioAmigo : listaUsuarios){
                if (usuarioAmigo.getCorreoElectronico().equals(correoAmigoNuevo)){
                    usuario.getListaAmigos().add(usuarioAmigo);
                    System.out.println("Usuario agregado a lista de amigos");
                }
            }
        } else {
            System.out.println("Ese usuario no existe o ya se encuentra en su lista de amigos.");
        }
    }

    public static void eliminarAmigo(Usuario usuario){

        String amigoBorrar;

        System.out.println("Escriba el correo del amigo que quiere borrar: ");
        amigoBorrar = App.lector.nextLine();

            if (existeUsuario(amigoBorrar) && usuarioEsAmigo(usuario, amigoBorrar)){ 
                for (Usuario usuarioAmigoBorrar : usuario.getListaAmigos()){
                    if (usuarioAmigoBorrar.getCorreoElectronico().equals(amigoBorrar)){
                        usuario.getListaAmigos().remove(usuarioAmigoBorrar);
                        System.out.println("Usuario eliminado de la lista de amigos correctamente.");
                        return;
                    }
                }
            }
                System.out.println("Ese usuario se encuentra en su lista de amigos");
    }
    
    public static void listaAmigos(Usuario usuario){
        System.out.println("-- Lista de amigos: ");
        for (Usuario amigo : usuario.getListaAmigos())
        System.out.println("- " + amigo.getCorreoElectronico());
    }

    private static boolean existeUsuario(String correoElectronico){
        
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreoElectronico().equals(correoElectronico)){
                return true;
            }
        }
        return false;
    }
    private static boolean usuarioEsAmigo(Usuario usuario, String amigo){
        
        for (Usuario amigoEnLista : usuario.getListaAmigos()) {
            if (amigoEnLista.getCorreoElectronico().equals(amigo)){
                return true;
            }
        }
        return false;
    }
}
