package src;

public class Main {
    public static void main(String[] args) {
        try {
            Ficheiros.showObjectFileContents();
            Ficheiros.doUsersExist();
            mainLoop();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    private static void mainLoop() {
        boolean running = true;
        while (running) {
            clearConsole();
            System.out.println("|------------|");
            System.out.println("|Menu:       |");
            System.out.println("|1. Login    |");
            System.out.println("|2. Register |");
            System.out.println("|0. Exit     |");
            System.out.println("|------------|");
            System.out.print("\nOption: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    Utilizador user = loginUser();
                    if (user != null) {
                        Utilizador.loggedUserLoop(user);
                    }
                    pressEnterKey();
                    break;
                case "2":
                    registerUser();
                    pressEnterKey();
                    break;
                case "0":
                    clearConsole();
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

    public static void registerUser() {
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
                Utilizador admin = Utilizador.registerNewUser("inativo", "administrador");
                Ficheiros.insertUserFicehiro(admin);
                Ficheiros.insertObjectFicheiro(admin);
                break;
            case "2":
                Utilizador tecnico = Utilizador.registerNewUser("inativo", "tecnico");
                Ficheiros.insertUserFicehiro(tecnico);
                Ficheiros.insertObjectFicheiro(tecnico);
                break;
            case "3":
                Utilizador cliente = Utilizador.registerNewUser("inativo", "cliente");
                Ficheiros.insertUserFicehiro(cliente);
                Ficheiros.insertObjectFicheiro(cliente);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                pressEnterKey();
                break;
        }
    }

    public static Utilizador loginUser() {
        clearConsole();
        System.out.print("Login: ");
        String login = Input.readLine();
        System.out.print("Password: ");
        String password = Validator.encryptPassword(Input.readLine());
        return Ficheiros.authenticateUser(login, password);
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