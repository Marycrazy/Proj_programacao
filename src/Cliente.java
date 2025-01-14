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

    private static List<Servicos> getServicosCliente(Cliente cliente) {
        List<Servicos> servicosCliente = Sistema.getInstance().getServicos().stream().filter(s -> s.getCliente().equals(cliente)).toList();
        if (servicosCliente.isEmpty()) {
            return null;
        }
        else {
            return servicosCliente;
        }
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
                    Logs.adicionarLog(new Logs(user, new Date(), "Editou o login"));
                    Main.pressEnterKey();
                    break;
                case "2":
                    user.setPassword();
                    Logs.adicionarLog(new Logs(user, new Date(), "Editou a password"));
                    Main.pressEnterKey();
                    break;
                case "3":
                    user.setNome();
                    Logs.adicionarLog(new Logs(user, new Date(), "Editou o nome"));
                    Main.pressEnterKey();
                    break;
                case "4":
                    user.setEmail();
                    Logs.adicionarLog(new Logs(user, new Date(), "Editou o email"));
                    Main.pressEnterKey();
                    break;
                case "5":
                    ((Cliente) user).setNIF();
                    Logs.adicionarLog(new Logs(user, new Date(), "Editou o NIF"));
                    Main.pressEnterKey();
                    break;
                case "6":
                    ((Cliente) user).setMorada();
                    Logs.adicionarLog(new Logs(user, new Date(), "Editou a morada"));
                    Main.pressEnterKey();
                    break;
                case "7":
                    ((Cliente) user).setTelefone();
                    Logs.adicionarLog(new Logs(user, new Date(), "Editou o telefone"));
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
            System.out.println(i + 1 + ".");
            Equipamentos.listarEquipamentos(equipamentos.get(i));
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
            int quantidade = Servicos.quantidadeEquipamentoDisponivel(equipamentos.get(id-1));
            pedido.adicionarEquipamento(equipamentos.get(id-1), quantidade);
            Logs.adicionarLog(new Logs(pedido.getCliente(), new Date(), "Realizou um pedido de compra"));
            System.out.println("Deseja adicionar mais algum equipamento ao pedido? (S/N)");
            String addEquipamento = Input.readLine();
            if (addEquipamento.equalsIgnoreCase("S")) {
                equipamentos.get(id-1).setQuantidadeStock(equipamentos.get(id-1).getQuantidadeStock() - quantidade);
                return true;
            }
            else {
                String descricao = handleDescricao(pedido);
                pedido.setValorTotal(pedido.calcularValorTotal());
                pedido.setDescricao(descricao);
                equipamentos.get(id-1).setQuantidadeStock(equipamentos.get(id-1).getQuantidadeStock() - quantidade);
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

    private static void listagemServiçosLoop(List<Servicos> servicosCliente) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|-------------------------------------------------------------------------|");
            System.out.println("|Listar Serviços                                                          |");
            System.out.println("|1. Listar todos os serviços                                              |");
            System.out.println("|2. Listar serviços aceites                                               |");
            System.out.println("|3. Listar serviços concluídos                                            |");
            System.out.println("|4. Listar serviços submetidos                                            |");
            System.out.println("|5. Listar serviços por cliente                                           |");
            System.out.println("|6. Listar serviços por tempo despendido superior a um determinado limite |");
            System.out.println("|7. Pesquisar serviços por código ou uma palavra que esteja na descrição  |");
            System.out.println("|8. Sair                                                                  |");
            System.out.println("|-------------------------------------------------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    Main.clearConsole();
                    if (servicosCliente.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado.");
                    }
                    else {
                        System.out.println("Todos os serviços:");
                        Servicos.listarServicos(servicosCliente, true);
                    }
                    Logs.adicionarLog(new Logs(servicosCliente.get(0).getCliente(), new Date(), "Listou todos os serviços"));
                    Main.pressEnterKey();
                    break;
                case "2":
                    List<Servicos> servicosAceites = servicosCliente.stream().filter(s -> s.getEstado().equals(Servicos.EstadoServico.ACEITE)).toList();
                    Main.clearConsole();
                    if (servicosAceites.isEmpty()) {
                        System.out.println("Nenhum serviço aceite encontrado.");
                    }
                    else {
                        System.out.println("Serviços aceites:");
                        Servicos.listarServicos(servicosAceites, true);
                    }
                    Logs.adicionarLog(new Logs(servicosCliente.get(0).getCliente(), new Date(), "Listou os serviços aceites"));
                    Main.pressEnterKey();
                    break;
                case "3":
                    List<Servicos> servicosConcluidos = servicosCliente.stream().filter(s -> s.getEstado().equals(Servicos.EstadoServico.CONCLUIDO)).toList();
                    Main.clearConsole();
                    if (servicosConcluidos.isEmpty()) {
                        System.out.println("Nenhum serviço concluído encontrado.");
                    }
                    else {
                        System.out.println("Serviços concluídos:");
                        Servicos.listarServicos(servicosConcluidos, true);
                    }
                    Logs.adicionarLog(new Logs(servicosCliente.get(0).getCliente(), new Date(), "Listou os serviços concluídos"));
                    Main.pressEnterKey();
                    break;
                case "4":
                    List<Servicos> submetidos = servicosCliente.stream().filter(s -> s.getEstado().equals(Servicos.EstadoServico.SUBMETIDO)).toList();
                    Main.clearConsole();
                    if (submetidos.isEmpty()) {
                        System.out.println("Nenhum serviço submetido encontrado.");
                    }
                    else {
                        System.out.println("Serviços submetidos:");
                        Servicos.listarServicos(submetidos, true);
                    }
                    Logs.adicionarLog(new Logs(servicosCliente.get(0).getCliente(), new Date(), "Listou os serviços submetidos"));
                    Main.pressEnterKey();
                    break;
                case "5":
                    System.out.print("Nome do cliente a pesquisar: ");
                    String cliente = Input.readLine();
                    List<Servicos> servicosPorCliente = servicosCliente.stream().filter(s -> s.getCliente().getNome().toLowerCase().contains(cliente.toLowerCase())).toList();
                    Main.clearConsole();
                    if (servicosPorCliente.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado para o cliente " + cliente + ".");
                    }
                    else {
                        System.out.println("Serviços do cliente " + cliente + ":");
                        Servicos.listarServicos(servicosPorCliente, true);
                    }
                    Logs.adicionarLog(new Logs(servicosCliente.get(0).getCliente(), new Date(), "Listou os serviços do cliente " + cliente));
                    Main.pressEnterKey();
                    break;
                case "6":
                    System.out.println("Vão ser pedidas as semanas, os dias, as horas e os minutos, caso algum deles seja nulo, ou seja, <po exemplo não demorou semanas deve ser 0.");
                    System.out.println("Tempo despendido superior a: ");
                    int tempo = Servicos.tempoGasto();
                    List<Servicos> servicosPorTempo = servicosCliente.stream().filter(s -> s.getTempoProcessamento() >= tempo).toList();
                    Main.clearConsole();
                    if (servicosPorTempo.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado com tempo despendido superior a " + Servicos.formatarTempo(tempo) + ".");
                    }
                    else {
                        System.out.println("Serviços com tempo despendido superior a " + Servicos.formatarTempo(tempo) + ":");
                        Servicos.listarServicos(servicosPorTempo, true);
                    }
                    Logs.adicionarLog(new Logs(servicosCliente.get(0).getCliente(), new Date(), "Listou os serviços com tempo despendido superior a " + Servicos.formatarTempo(tempo)));
                    Main.pressEnterKey();
                    break;
                case "7":
                    System.out.print("Código ou palavra na descrição a pesquisar: ");
                    String pesquisa = Input.readLine();
                    List<Servicos> servicosPesquisados = servicosCliente.stream().filter(s -> String.valueOf(s.getCodigo()).contains(pesquisa) || s.getDescricao().contains(pesquisa)).toList();
                    Main.clearConsole();
                    if (servicosPesquisados.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado com o código ou palavra na descrição " + pesquisa + ".");
                    }
                    else {
                        System.out.println("Serviços encontrados com o código ou palavra na descrição " + pesquisa + ":");
                        Servicos.listarServicos(servicosPesquisados, true);
                    }
                    Logs.adicionarLog(new Logs(servicosCliente.get(0).getCliente(), new Date(), "Listou os serviços com o código ou palavra na descrição " + pesquisa));
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

    public static void loggedUserLoop(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|------------------------------------|");
            System.out.println("|1. Editar perfil                    |");
            System.out.println("|2. Listar equipamentos              |");
            System.out.println("|3. Realizar um pedido de compra     |");
            System.out.println("|4. Listar pedidos de serviço         |");
            System.out.println("|5. Sair                             |");
            System.out.println("|------------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    editperfil(user);
                    break;
                case "2":
                    Equipamentos.listarEquipamentosLoop(user);
                    break;
                case "3":
                    realizarPedido(user);
                    break;
                case "4":
                    List<Servicos> servicosCliente = getServicosCliente((Cliente) user);
                    listagemServiçosLoop(servicosCliente);
                    Main.pressEnterKey();
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
