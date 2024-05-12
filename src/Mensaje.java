import java.util.ArrayList;

public class Mensaje {
    
    private String mensaje;
    private String remitente;
    private String destinatario;
    private boolean leido;
    private static ArrayList<Mensaje> listaMensajes = new ArrayList<>();

    public Mensaje(String mensaje, String remitente, String destinatario) {
        this.mensaje = mensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        leido = false;
        
    }

    public static void enviarMensaje(Usuario usuario){

        String mensajeEnviar;
        String remitente = usuario.getCorreoElectronico();
        String destinatario;

        System.out.println("Escriba su mensaje: ");
        mensajeEnviar = App.lector.nextLine();
        System.out.println("Escriba el correo electrónico del destinatario: ");
        destinatario = App.lector.nextLine();

            if (Usuario.usuarioEsAmigo(usuario, destinatario)){ 
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
                System.out.println("- " + listaMensajes.indexOf(mensaje) + ": " + mensaje.getMensaje() + " - de: " + mensaje.getRemitente());
                mensaje.setLeido();
            }
        }
    }

    public static void listaNoLeidos (Usuario usuario){

        System.out.println("Mensajes no leídos: ");
        for (Mensaje mensaje : listaMensajes){
            if (mensaje.getDestinatario().equals(usuario.getCorreoElectronico()) && mensaje.getLeido()== false){
                System.out.println("- '" + mensaje.getMensaje() + "' de: " + mensaje.getRemitente());
                mensaje.setLeido();
            }
        }
    }

    public static void responderMensaje (Usuario usuario){
        String idLeido;
        int idMensaje = -1;
        String mensajeRespuesta;
        boolean error = true;
        
        leerMensaje(usuario);
        
        do {
            try {
                System.out.println("Escriba el id del mensaje que desea responder");
                idLeido = App.lector.nextLine();
                idMensaje = Integer.parseInt(idLeido);

                    if (existeMensaje(idMensaje)){
                        for (Mensaje mensajeEnLista : listaMensajes){
                            if (listaMensajes.indexOf(mensajeEnLista)==(idMensaje) && mensajeEnLista.getDestinatario().equals(usuario.getCorreoElectronico())){
                                error = false;
                                System.out.println("Escriba su respuesta: ");
                                mensajeRespuesta = App.lector.nextLine();
                                Mensaje mensaje = new Mensaje(mensajeRespuesta, usuario.getCorreoElectronico(), mensajeEnLista.getRemitente());
                                listaMensajes.add(mensaje);
                                System.out.println("Respuesta enviada correctamente");
                                return;
                            }
                        }
                    } else {
                        System.out.println("No existe ese mensaje.");
                    }
                } catch (Exception e){
                    error = true;
                    System.out.println("Valor inválido");
                    App.lector.nextLine();
                }
        } while (error);
    }

    public static boolean existeMensaje(int id){
        
        for (Mensaje mensaje : listaMensajes) {
            if (listaMensajes.indexOf(mensaje)==(id)){
                return true;
            }
        }
        return false;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public boolean getLeido(){
        return leido;
    }

    public void setLeido(){
        leido = true;
    }
    
}
