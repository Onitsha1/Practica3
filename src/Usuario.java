import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contraseña; 
    private ArrayList<Usuario> listaAmigos;
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Usuario(String nombre, String apellido, String correoElectronico, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        listaAmigos = new ArrayList<>();
    }

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

    public static void iniciarSesion(){
        String correoElectronicoIntroducido;
        String contraseñaIntroducida;
        
        System.out.println("-- Inicio de sesión --");
        System.out.println("Escriba su correo electrónico: ");
        correoElectronicoIntroducido = App.lector.nextLine();
        System.out.println("Escriba su contraseña: ");
        contraseñaIntroducida = App.lector.nextLine();

        if (existeUsuario(correoElectronicoIntroducido)){ 
            for(Usuario usuario : listaUsuarios){
                if(usuario.getCorreoElectronico().equals(correoElectronicoIntroducido) && usuario.getContraseña().equals(contraseñaIntroducida)){
                System.out.println("Sesión iniciada correctamente");
                Gestion.menuSecundario(usuario);
                }
            }
            System.out.println("Contraseña o correo electrónico incorrecto");
        } else {System.out.println("Esa cuenta no existe");
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
                System.out.println("Ese usuario no se encuentra en su lista de amigos");
    }

    public static void borrarCuenta(Usuario usuario){

        String confirmacionBorrado;
        boolean error = true;

        System.out.println("-- Borrar usuario --");
        System.out.println("Escriba BORRAR para eliminar su cuenta: ");
        confirmacionBorrado = App.lector.nextLine();

        do {
            if (confirmacionBorrado.equals("BORRAR")){
                error = false;
                listaUsuarios.remove(usuario);
                System.out.println("Cuenta eliminada correctamente.");
                    for (Usuario usuariosAmigosBorrar : listaUsuarios){
                        if (usuariosAmigosBorrar.getListaAmigos().contains(usuario)){
                            usuariosAmigosBorrar.getListaAmigos().remove(usuario); 
                        }
                    }
                App.menuPrincipal();
            } else {
                error = true;
            System.out.println("Valor inválido ");
         }
        } while (error);
    }

    public static void cerrarSesion(Usuario usuario){

        String confirmacionSalir;
        boolean error = true;

        do {
            System.out.println("Escriba 'SALIR' para cerrar sesión y volver al menú principal: ");
            confirmacionSalir = App.lector.nextLine();
            if (confirmacionSalir.equals("SALIR")){
                error = false;
                App.menuPrincipal();

            } else {
                error = true;
                System.out.println("Valor inválido.");
            }
        } while (error);

    }

    
    public static boolean existeUsuario(String correoElectronico){
        
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreoElectronico().equals(correoElectronico)){
                return true;
            }
        }
        return false;
    }

    public static boolean usuarioEsAmigo(Usuario usuario, String amigo){
        
        for (Usuario amigoEnLista : usuario.getListaAmigos()) {
            if (amigoEnLista.getCorreoElectronico().equals(amigo)){
                return true;
            }
        }
        return false;
    }

    public static void listaAmigos(Usuario usuario){
        System.out.println("-- Lista de amigos: ");
        for (Usuario amigo : usuario.getListaAmigos())
        System.out.println("- " + amigo.getCorreoElectronico());
    }


    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public String getCorreoElectronico() {
        return correoElectronico;
    }


    public String getContraseña() {
        return contraseña;
    }


    public ArrayList<Usuario> getListaAmigos() {
        return listaAmigos;
    }

}
