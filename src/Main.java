package src;

public class Main {
    private static InfoSistema infoSistema;
    public static void main(String[] args) {
        try {
            Sistema.getInstance().carregarDados();
            pressEnterKey();
             // Carregar informações do sistema
            infoSistema = InfoSistema.carregarDados();
            Main.pressEnterKey();
            mainLoop();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    private static void mainLoop() {
        boolean running = true;
        while (running) {
            clearConsole();
            System.out.println("|---------------------------|");
            System.out.println("|Menu:                      |");
            System.out.println("|1. Login                   |");
            System.out.println("|2. Register                |");
            System.out.println("|3. Informações do sistema  |");
            System.out.println("|0. Exit                    |");
            System.out.println("|---------------------------|");
            System.out.print("\nOption: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    Utilizador user = loginUser();
                    pressEnterKey();
                    if (user != null) {
                        Utilizador.loggedUserLoop(user);
                    }
                    break;
                case "2":
                    registerUser();
                    pressEnterKey();
                    break;
                case "3":
                    clearConsole();
                    InfoSistema.listarInfoSistema();
                    pressEnterKey();
                    break;
                case "0":
                    Sistema.getInstance().save();
                    clearConsole();
                    Ficheiros.salvarInfoSistema(infoSistema);
                    pressEnterKey();
                    System.out.println("A sair...");
                    running = false;
                    break;
                default:
                    clearConsole();
                    System.out.println("Invalid option. Please try again.");
                    pressEnterKey();
                    break;
            }
        }
    }

    public static Utilizador loginUser() {
        clearConsole();
        System.out.print("Login: ");
        String login = Input.readLine();
        System.out.print("Password: ");
        String password = Validator.encryptPassword(Input.readLine());
        Utilizador user = Sistema.getInstance().autenticarUtilizador(login, password);
        if (user != null) {
            infoSistema.setUltimoUtilizador(user.getLogin());
            if(user.getEstado()){
                clearConsole();
                System.out.println("Bem-vindo " + user.getNome());
                return user;
            }
            else{
                System.out.println(user.getNome() + " está inativo, ainda não pode aceder a aplicação.");
                return null;
            }
        }
        return null;
    }

    public static void registerUser() {
        boolean running = true;
        while (running) {
            clearConsole();
            System.out.println("|-----------------|");
            System.out.println("|Register         |");
            System.out.println("|1. Administrator |");
            System.out.println("|2. Tecnico       |");
            System.out.println("|3. Cliente       |");
            System.out.println("|4. Sair          |");
            System.out.println("|-----------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    Utilizador admin = Utilizador.registerNewUser(false, "administrador");
                    Sistema.getInstance().adicionarutilizador(admin);
                    break;
                case "2":
                    Utilizador tecnico = Utilizador.registerNewUser(false, "tecnico");
                    Sistema.getInstance().adicionarutilizador(tecnico);
                    break;
                case "3":
                    Utilizador cliente = Utilizador.registerNewUser(false, "cliente");
                    Sistema.getInstance().adicionarutilizador(cliente);
                    break;
                case "4":
                    System.out.println("A voltar ao menu principal.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    pressEnterKey();
                    break;
            }
        }
    }

    // Press any key to continue
    public static void pressEnterKey() {
        System.out.print("Press Enter to continue...");
        Input.readLine();
    }

    // Clear the console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}