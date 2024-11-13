package src;

public class Utilizador {
    private String login;
    private String password;
    private String nome;
    private String estado;
    private String email;
    private String tipo;

    //construtor
    public Utilizador(String login, String password, String nome, String estado, String email, String tipo) {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.estado = estado;
        this.email = email;
        this.tipo = tipo;
    }

    //getters
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public static void loggedUserLoop(Utilizador user) {
        // nao esquecer de tirar esta parte foi so para testar
        System.out.println("olá, " + user.getNome());
        System.out.println("Tipo de utilizador: " + user.getTipo());
        System.out.println("Estado: " + user.getEstado());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Login: " + user.getLogin());
        Main.pressEnterKey();
        switch (user.getTipo()) {
            case "administrador":
                Administrador.loggedUserLoop(user);
                break;
            case "tecnico":
                Tecnicos.loggedUserLoop(user);
                break;
            case "cliente":
                Cliente.loggedUserLoop(user);
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + user.getTipo());
        }
    }

    public static Utilizador registerNewUser(String estado, String type) {
        switch (type) {
            case "administrador":
               return Administrador.registerNewUser(estado, type);
            case "tecnico":
                return Tecnicos.registerNewUser(estado, type);
            case "cliente":
                return Cliente.registerNewUser(estado, type);
            default:
                throw new IllegalArgumentException("Invalid user type: " + type);
        }
    }
}
