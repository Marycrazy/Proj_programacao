package src;
import java.io.Serializable;

public class Utilizador implements Serializable{
    private static final long serialVersionUID = 1L;
    private static Utilizador loggedUser; // Utilizador logado
    private String login; //unico
    private String password;
    private String nome;
    private boolean estado;
    private String email; //unico
    private String tipo;

    //construtor
    public Utilizador(String login, String password, String nome, boolean estado, String email, String tipo) {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.estado = estado;
        this.email = email;
        this.tipo = tipo;
    }

    //getters e setters
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public boolean getEstado() {
        return estado;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPassword() {
        this.password = Validator.validatePassword("Password");
    }

    public void setNome() {
        this.nome = Validator.validateInput("Name");
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public static Utilizador getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(Utilizador utilizador) {
        loggedUser = utilizador;
    }

    public static void loggedUserLoop(Utilizador user) {
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

    public static Utilizador registerNewUser(boolean estado, String type) {
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
