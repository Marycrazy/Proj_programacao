package src;

import java.util.List;

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
                    ((Tecnicos) user).setNIF();
                    Main.pressEnterKey();
                    break;
                case "6":
                    ((Tecnicos) user).setMorada();
                    Main.pressEnterKey();
                    break;
                case "7":
                    ((Tecnicos) user).setTelefone();
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

    private static List<Servicos> getServicosTecnico(Tecnicos tecnico) {
        List<Servicos> servicosTecnico = Sistema.getInstance().getServicos().stream().filter(s -> s.getTecnicoResponsavel().equals(tecnico)).toList();
        if (servicosTecnico.isEmpty()) {
            return null;
        }
        else {
            return servicosTecnico;
        }
    }

    private static void listarServicosTecnico(List<Servicos> servicosTecnico) {
        if (servicosTecnico == null) {
            System.out.println("Não existem serviços associados a ti.");
            Main.pressEnterKey();
        }
        else {
            Servicos.listarServicos(servicosTecnico, true);
        }
    }

    private static SubServico criarSubServico(List<Servicos> servicosTecnico) {
        listarServicosTecnico(servicosTecnico);
        System.out.print("Selecione o serviço a aprovar (ou 0 para cancelar): ");
        // guarda a escola do utilizador o serviço
        int service = Input.readInt();
        if (service == 0) {
            System.out.println("A voltar ao menu principal.");
            return null;
        }
        else {
            Input.clearBuffer();
            String designacao = Validator.validateInput("Designação");
            // lista os tecnicos disponiveis incluindo o próprio
            List<Utilizador> tecnicos = Sistema.getInstance().getUtilizadores().stream()
            .filter(u -> u.getTipo().equalsIgnoreCase("tecnico") && u.getEstado())
            .toList();
            System.out.println("Tecnicos:");
                        for (int i = 0; i < tecnicos.size(); i++) {
                            System.out.println((i + 1) + ".");
                            perfilUtilizador(tecnicos.get(i));
                        }
            System.out.print("Selecione o tecnico a associar ao serviço:");
            // guarda a escolha do utilizador para ser o tecnico responsavel da sub tarefa
            int id = Input.readInt();
            if (id <= 0 || id > tecnicos.size()) {
                System.out.println("Tecnico não encontrado.");
                Main.pressEnterKey();
                return null;
            }
            else {
                // cria o subserviço
                SubServico subServico = new SubServico(designacao, (Tecnicos) tecnicos.get(id - 1));
                // adiciona o subserviço ao serviço escolhido
                servicosTecnico.get(service - 1).adicionarSubServico(subServico);
                return subServico;
            }
        }
    }

    private static void gestaoServicos(List<Servicos> servicosTecnico) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|-------------------------------------------------|");
            System.out.println("|1. Ver serviços a que estou associado            |");
            System.out.println("|2. Criar sub tarefa de um serviço                |");
            System.out.println("|3. Encerrar serviço e indicar o tempo gasto      |");
            System.out.println("|4. Sair                                          |");
            System.out.println("|-------------------------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    listarServicosTecnico(servicosTecnico);
                    Main.pressEnterKey();
                    break;
                case "2":
                    boolean check = true;
                    do {
                        SubServico subsevico = criarSubServico(servicosTecnico);
                        if (subsevico != null) {
                            System.out.println("Subserviço adicionado com sucesso.");
                        }
                        else{
                            System.out.println("Subserviço não adicionado.");
                            Main.pressEnterKey();
                            check = false;
                        }
                        Input.clearBuffer();
                        System.out.print("Deseja adicionar mais um subserviço? (s/n): ");
                        String resposta = Input.readLine();
                        if (resposta.equalsIgnoreCase("n")) {
                            check = false;
                        }
                    } while (check);
                    Main.pressEnterKey();
                    break;
                case "3":
                    listarServicosTecnico(servicosTecnico);
                    encerrarServico(servicosTecnico);
                    break;
                case "4":
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

    public static void loggedUserLoop(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|--------------------------------|");
            System.out.println("|Tens "+ (getServicosTecnico((Tecnicos) user) != null ? getServicosTecnico((Tecnicos) user).size() : 0) + " serviços associados a ti |");
            System.out.println("|--------------------------------|");
            System.out.println("|1. Editar perfil                |");
            System.out.println("|2. Gerir equipamento            |");
            System.out.println("|3. Adicionar fornecedor         |");
            System.out.println("|4. Adicionar categoria          |");
            System.out.println("|5. Ver serviços                 |");
            System.out.println("|6. Sair                         |");
            System.out.println("|--------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    editperfil(user);
                    break;
                case "2":
                    Equipamentos.equipamentosLoop();
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
                    List<Servicos> servicosTecnico = getServicosTecnico((Tecnicos) user);
                    gestaoServicos(servicosTecnico);
                    Main.pressEnterKey();
                    break;
                case "6":
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
