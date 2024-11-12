package src;
public class Tecnicos extends Utilizador {
    private String NIF;
    private String morada;
    private String telefone;

    public Tecnicos(String login, String password, String nome, boolean estado, String email, String tipo, String NIF, String morada, String telefone) {
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

    private String getTelefone() {
        return telefone;
    }

    public static Tecnicos registerNewUser(boolean estado, String type) {
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