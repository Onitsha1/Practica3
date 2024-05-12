import java.util.Scanner;

public class App {
    static Scanner lector = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        menuPrincipal();
    }

    public static void menuPrincipal(){
        String opcionLeida;
        int opcion =-1;
        boolean error = true;

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
                error = false;    
            } else {
                error = true;
                System.out.println("Esa opción no existe");
            }
            } catch (Exception e) {
                error = true;
                System.out.println("Valor inválido");
                lector.nextLine();
            }
            switch (opcion) {
                case 1:
                   Usuario.crearUsuario(); 
                    break;
                case 2:
                   Usuario.iniciarSesion(); 
                    break;
                case 3:
                    break;
            }
        } while (error || opcion !=3);
        lector.close();
    }
}
