package src;

public class Utilizador {
    private String login;
    private String password;
    private String nome;
    private boolean estado;
    private String email;
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

    public boolean getEstado() {
        return estado;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
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
