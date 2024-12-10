package src;

public class Tecnicos extends Utilizador{
    private static final long serialVersionUID = 1L;
    private String NIF;
    private String morada;
    private String telefone;

    public Tecnicos(String login, String password, String nome, boolean estado, String email, String tipo, String NIF, String morada, String telefone) {
        super(login, password, nome, estado, email, tipo);
        this.NIF = NIF;
        this.morada = morada;
        this.telefone = telefone;
    }

    // Getters e setters
    public String getNIF() {
        return NIF;
    }

    public String getMorada() {
        return morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNIF(){
        this.NIF = Validator.validateInput("NIF");
    }

    public void setMorada(){
        this.morada = Validator.validateInput("Morada");
    }

    public void setTelefone(){
        this.telefone = Validator.validateInput("Telefone");
    }

    private static void perfilUtilizador(Utilizador user) {
        System.out.println("*******************");
        System.out.println("Perfil           ");
        System.out.println("Login: " + user.getLogin());
        System.out.println("Password: **********");
        System.out.println("Nome: " + user.getNome());
        System.out.println("Email: " + user.getEmail());
        System.out.println("NIF: " + ((Tecnicos) user).getNIF());
        System.out.println("Morada: " + ((Tecnicos) user).getMorada());
        System.out.println("Telefone: " + ((Tecnicos) user).getTelefone());
        System.out.println("******************* \n");
    }

    private static void editperfil(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            perfilUtilizador(user);
            Main.pressEnterKey();
            System.out.println("|------------------|");
            System.out.println("|Editar perfil     |");
            System.out.println("|1. Editar Login   |");
            System.out.println("|2. Editar password|");
            System.out.println("|3. Editar nome    |");
            System.out.println("|4. Editar email   |");
            System.out.println("|5. Editar NIF     |");
            System.out.println("|6. Editar morada  |");
            System.out.println("|7. Editar telefone|");
            System.out.println("|8. Sair           |");
            System.out.println("|------------------|");
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
                    ((Tecnicos) user).setNIF();
                    break;
                case "6":
                    ((Tecnicos) user).setMorada();
                    break;
                case "7":
                    ((Tecnicos) user).setTelefone();
                    break;
                case "8":
                    System.out.println("A voltar ao menu principal.");
                    running = false;
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
            System.out.println("|-----------------------------|");
            System.out.println("|1. Editar perfil             |");
            System.out.println("|2. Adicionar equipamento     |");
            System.out.println("|3. Adicionar fornecedor      |");
            System.out.println("|4. Adicionar categoria       |");
            System.out.println("|5. Sair                      |");
            System.out.println("|-----------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    editperfil(user);
                    break;
                case "2":
                    Equipamentos equip = Equipamentos.adEquipamentos();
                    if (equip != null) {
                        Sistema.getInstance().adicionarEquipamento(equip);
                        Main.pressEnterKey();
                    }
                    else{
                        System.out.println("Equipamento não adicionado.");
                    }
                    break;
                case "3":
                    Fornecedor forne = Fornecedor.adicionarFornecedor();
                    if (forne != null) {
                        Sistema.getInstance().adicionarFornecedor(forne);
                        Main.pressEnterKey();
                    }
                    else{
                        System.out.println("Fornecedor não adicionado.");
                    }
                    break;
                case "4":
                    Categoria cat = Categoria.adicionarCategoria();
                    if(cat != null){
                        Sistema.getInstance().adicionarCategoria(cat);
                        Main.pressEnterKey();
                    }
                    else{
                        System.out.println("Categoria não adicionada.");
                    }
                    break;
                case "5":
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
