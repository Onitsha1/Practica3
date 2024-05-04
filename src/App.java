import java.util.Scanner;

public class App {
    static Scanner lector = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        menuPrincipal();
    }

    public static void menuPrincipal(){
        String opcionLeida;
        int opcion =-1;
        boolean fallo = true;

        do {
            try {
            System.out.println("-- Menú principal --");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.println("Escriba una de las opciones anteriores: ");
            opcionLeida = lector.nextLine();
            opcion = Integer.parseInt(opcionLeida);
            if (opcion >=1 && opcion <=3){
                fallo = false;    
            } else {
                fallo = true;
                System.out.println("Esa opción no existe");
            }
            } catch (Exception e) {
                fallo = true;
                System.out.println("Valor inválido");
                lector.nextLine();
            }
            switch (opcion) {
                case 1:
                   Gestion.crearUsuario(); 
                    break;
                case 2:
                   Gestion.iniciarSesion(); 
                    break;
                case 3:
                    break;
            }
        } while (fallo || opcion !=3);
        lector.close();
    }
}
