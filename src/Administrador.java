package src;

public class Administrador extends Utilizador {

    // Constructor
    public Administrador(String login, String password, String nome, boolean estado, String email, String tipo) {
        super(login, password, nome, estado, email, tipo);
    }



    public static Administrador registerNewUser(boolean estado, String type) {
        Main.clearConsole();
        String login = Validator.validateInput("Login");
        String password = Validator.validatePassword("Password");
        String name = Validator.validateInput("Name");
        String email = Validator.validateInput("Email");
        return new Administrador(login, password, name, estado, email, type);
    }

    private static void perfilUtilizador(Utilizador user) {
        System.out.println("*******************");
        System.out.println("Perfil           *");
        System.out.println("Login: " + user.getLogin());
        System.out.println("Nome: " + user.getNome());
        System.out.println("Email: " + user.getEmail());
        System.out.println("******************* \n");
    }

    private static void editperfil(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            perfilUtilizador(user);
            Main.pressEnterKey();
            System.out.println("|-----------------|");
            System.out.println("|Editar perfil    |");
            System.out.println("|1. Editar Login   |");
            System.out.println("|2. Editar password|");
            System.out.println("|3. Editar nome    |");
            System.out.println("|4. Editar email   |");
            System.out.println("|5. Sair          |");
            System.out.println("|-----------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    user.setLogin();
                    break;
                case "2":
                    user.setPassword();
                    break;
                case "3":
                    user.setNome();
                    break;
                case "4":
                    user.setEmail();
                    break;
                case "5":
                    System.out.println("A voltar ao menu principal.");
                    running = false;
                    loggedUserLoop(user);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    Main.pressEnterKey();
                    break;
            }
        }
    }

    public static void loggedUserLoop(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|-----------------|");
            System.out.println("|1. Editar perfil |");
            System.out.println("|4. Sair          |");
            System.out.println("|-----------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    editperfil(user);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    Main.clearConsole();
                    System.out.println("Adeus " + user.getNome());
                    Main.pressEnterKey();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    Main.pressEnterKey();
                    break;
            }
        }
    }
}