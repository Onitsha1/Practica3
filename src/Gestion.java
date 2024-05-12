public class Gestion {

    public static void menuSecundario(Usuario usuario){

        boolean error = true;
        String opcionLeida2;
        int opcion2 = -1;

        do {
            try {
            System.out.println("-- Menú secundario --");
            System.out.println("1. Leer mensajes no leídos");
            System.out.println("2. Enviar mensaje");
            System.out.println("3. Responder mensajes");
            System.out.println("4. Agregar amigo");
            System.out.println("5. Eliminar amigo");
            System.out.println("6. Ver lista amigos");
            System.out.println("7. Borrar cuenta");
            System.out.println("8. Cerrar sesión");
            System.out.println("Escriba una de las opciones anteriores: ");

            opcionLeida2 = App.lector.nextLine();
            opcion2 = Integer.parseInt(opcionLeida2);

            if (opcion2 >=1 && opcion2 <= 8){
                error = false;
            } else {
                error = true;
                System.out.println("Esa opción no existe");
            }
            } catch (Exception e) {
                error = true;
                System.out.println("Valor inválido");
            }
            switch (opcion2) {
                case 1:
                   Mensaje.listaNoLeidos(usuario);
                    break;
                
                case 2:
                Mensaje.enviarMensaje(usuario);
                    break;

                case 3:
                Mensaje.responderMensaje(usuario);
                    break;

                case 4:
                    Usuario.agregarAmigo(usuario);
                    break;

                case 5:
                Usuario.eliminarAmigo(usuario);
                    break;

                case 6:
                Usuario.listaAmigos(usuario);
                    break;

                case 7:
                Usuario.borrarCuenta(usuario);
                    break;

                case 8:
                Usuario.cerrarSesion(usuario); 
                    break;
            }
        } while (error || opcion2 != 8);
    }
}
