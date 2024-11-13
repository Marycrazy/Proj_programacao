package src;
public class Tecnicos extends Utilizador {
    private String NIF;
    private String morada;
    private String telefone;

    public Tecnicos(String login, String password, String nome, String estado, String email, String tipo, String NIF, String morada, String telefone) {
        super(login, password, nome, estado, email, tipo);
        this.NIF = NIF;
        this.morada = morada;
        this.telefone = telefone;
    }

    // Getters
    public String getNIF() {
        return NIF;
    }

    public String getMorada() {
        return morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public static void loggedUserLoop(Utilizador user) {
        Main.clearConsole();
        System.out.println("Bem-vindo " + user.getNome());
    }

    public static Tecnicos registerNewUser(String estado, String type) {
        Main.clearConsole();
        String login = Validator.validateInput("Login");
        String password = Validator.validatePassword("Password");
        String name = Validator.validateInput("Name");
        String email = Validator.validateInput("Email");
        String NIF = Validator.validateInput("NIF");
        String morada = Validator.validateInput("Morada");
        String telefone = Validator.validateInput("Telefone");
        return new Tecnicos(login, password, name, estado, email, type, NIF, morada, telefone);
    }
}
/*cliente, 15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225, Cliente, false, dfsh@ksdf.com, cliente
oi_my, 15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225, HI Hello, true, hi@gn.com, administrador */