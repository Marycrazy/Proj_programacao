package src;
public class Administrador extends Utilizador {

    // Constructor
    public Administrador(String login, String password, String nome, String estado, String email, String tipo) {
        super(login, password, nome, estado, email, tipo);
    }

    public static Administrador registerNewUser(String estado, String type) {
        Main.clearConsole();
        String login = Validator.validateInput("Login");
        String password = Validator.validatePassword("Password");
        String name = Validator.validateInput("Name");
        String email = Validator.validateInput("Email");
        return new Administrador(login, password, name, estado, email, type);
    }

    public static void loggedUserLoop(Utilizador user) {
        Main.clearConsole();
        System.out.println("Bem-vindo " + user.getNome());
    }
}