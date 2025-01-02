package src;

import java.util.Date;
import java.util.List;


public class Cliente extends Utilizador {
    private static final long serialVersionUID = 1L;
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
        System.out.println("NIF: " + ((Cliente) user).getNIF());
        System.out.println("Morada: " + ((Cliente) user).getMorada());
        System.out.println("Telefone: " + ((Cliente) user).getTelefone());
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
                    Main.pressEnterKey();
                    break;
                case "2":
                    user.setPassword();
                    Main.pressEnterKey();
                    break;
                case "3":
                    user.setNome();
                    Main.pressEnterKey();
                    break;
                case "4":
                    user.setEmail();
                    Main.pressEnterKey();
                    break;
                case "5":
                    ((Cliente) user).setNIF();
                    Main.pressEnterKey();
                    break;
                case "6":
                    ((Cliente) user).setMorada();
                    Main.pressEnterKey();
                    break;
                case "7":
                    ((Cliente) user).setTelefone();
                    Main.pressEnterKey();
                    break;
                case "8":
                    System.out.println("A voltar ao menu principal.");
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

    private static void realizarPedido(Utilizador user) {
        Main.clearConsole();
        List<Equipamentos> equipamentos = Sistema.getInstance().getEquipamentos();
        System.out.println("Realizar Pedido");
        System.out.println("*******************");
        System.out.println("Lista de equipamentos");
        for (int i = 0; i < equipamentos.size(); i++){
            Equipamentos.listarEquipamentos(equipamentos.get(i), i);
        }
        System.out.println("*******************");
        boolean running = true;
        Servicos pedido = new Servicos((Cliente)user, new Date(), "", 0);
        do {
            running = processPedido(equipamentos, pedido);
        } while (running);
    }

    private static boolean processPedido(List<Equipamentos> equipamentos, Servicos pedido) {
        System.out.println("Escolha o equipamento que deseja comprar ou 0 para sair.");
        System.out.print("ID: ");
        int id = Input.readInt();
        Input.clearBuffer();
        if (id == 0) {
            System.out.println("A voltar ao menu principal.");
            Main.pressEnterKey();
            return false;
        }
        else if (id <= 0 || id > equipamentos.size()) {
            System.out.println("ID inválido. Por favor tente novamente.");
            Main.pressEnterKey();
        }
        else {
            int quantidade = Servicos.quantidadeequipamento(equipamentos.get(id-1));
            pedido.adicionarEquipamento(equipamentos.get(id-1), quantidade);
            System.out.println("Deseja adicionar mais algum equipamento ao pedido? (S/N)");
            String addEquipamento = Input.readLine();
            if (addEquipamento.equalsIgnoreCase("S")) {
                return true;
            }
            else {
                String descricao = handleDescricao(pedido);
                pedido.setValorTotal(pedido.calcularValorTotal());
                pedido.setDescricao(descricao);
                Sistema.getInstance().adicionarServico(pedido);
                System.out.println("Pedido realizado com sucesso. Valor total: " + pedido.getValorTotal());
                Main.pressEnterKey();
                return false;
            }
        }
        return true;
    }

    private static String handleDescricao(Servicos pedido) {
        System.out.println("Deseja adicionar alguma descrição ao pedido (ex: Montagem, instalação de algo)? (S/N)");
        String descricao = Input.readLine();
        if (descricao.equalsIgnoreCase("S")) {
            System.out.println("Descrição deve ter no maximo 500 caracteres.");
            descricao = Validator.validateInput("Descrição");
        }
        else {
            descricao = "Sem descrição.";
        }
        return descricao;
    }

    public static void loggedUserLoop(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|------------------------------------|");
            System.out.println("|1. Editar perfil                    |");
            System.out.println("|2. Listar equipamentos              |");
            System.out.println("|3. Realizar um pedido de compra     |");
            System.out.println("|4. Sair                             |");
            System.out.println("|------------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    editperfil(user);
                    break;
                case "2":
                    Equipamentos.listarEquipamentosLoop();
                    break;
                case "3":
                    realizarPedido(user);
                    break;
                case "4":
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
