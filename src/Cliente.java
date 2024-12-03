package src;

public class Cliente extends Utilizador {
    private String NIF;
    private String morada;
    private String telefone;

    //construtor
    public Cliente(String login, String password, String nome, boolean estado, String email, String tipo, String NIF, String morada, String telefone) {
        super(login, password, nome, estado, email, tipo);
        this.NIF = NIF;
        this.morada = morada;
        this.telefone = telefone;
    }

    //getters
    public String getNIF() {
        return NIF;
    }

    public String getMorada() {
        return morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setMorada(){
        this.morada = Validator.validateInput("Morada");
    }

    public static void loggedUserLoop(Utilizador user) {
        Main.clearConsole();
        System.out.println("hi " + user.getNome());
        System.out.println("tipo " + user.getTipo());
        Main.pressEnterKey();
    }

    public static Cliente registerNewUser(boolean estado, String type) {
        Main.clearConsole();
        String login = Validator.validateInput("Login");
        String password = Validator.validatePassword("Password");
        String name = Validator.validateInput("Name");
        String email = Validator.validateInput("Email");
        String NIF = Validator.validateInput("NIF");
        String morada = Validator.validateInput("Morada");
        String telefone = Validator.validateInput("Telefone");
        return new Cliente(login, password, name, estado, email, type, NIF, morada, telefone);
    }

}
