package src;

import java.util.List;
import java.util.Date;

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
                    Logs.adicionarLog(new Logs(user, new Date(), "Alterou o login"));
                    Main.pressEnterKey();
                    break;
                case "2":
                    user.setPassword();
                    Logs.adicionarLog(new Logs(user, new Date(), "Alterou a password"));
                    Main.pressEnterKey();
                    break;
                case "3":
                    user.setNome();
                    Logs.adicionarLog(new Logs(user, new Date(), "Alterou o nome"));
                    Main.pressEnterKey();
                    break;
                case "4":
                    user.setEmail();
                    Logs.adicionarLog(new Logs(user, new Date(), "Alterou o email"));
                    Main.pressEnterKey();
                    break;
                case "5":
                    ((Tecnicos) user).setNIF();
                    Logs.adicionarLog(new Logs(user, new Date(), "Alterou o NIF"));
                    Main.pressEnterKey();
                    break;
                case "6":
                    ((Tecnicos) user).setMorada();
                    Logs.adicionarLog(new Logs(user, new Date(), "Alterou a morada"));
                    Main.pressEnterKey();
                    break;
                case "7":
                    ((Tecnicos) user).setTelefone();
                    Logs.adicionarLog(new Logs(user, new Date(), "Alterou o telefone"));
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

    private static void encerrarServico(List<Servicos> servicosTecnico) {
        System.out.print("Selecione o serviço a encerrar (ou 0 para cancelar): ");
        int service = Input.readInt();
        if (service == 0) {
            System.out.println("A voltar ao menu principal.");
        }
        else {
            System.out.println("Vão ser pedidas as semanas, os dias, as horas e os minutos, caso algum deles seja nulo, ou seja, não demorou semanas deve ser 0.");
            System.out.println("Indique o tempo gasto no serviço:.");
            servicosTecnico.get(service - 1).setTempoProcessamento(Servicos.tempoGasto());
            servicosTecnico.get(service - 1).setEstado(Servicos.EstadoServico.CONCLUIDO);
            Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Encerrou um serviço"));
        }
    }

    private static void listagemServiçosLoop(List<Servicos> servicosTecnico) {
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
                    Main.clearConsole();
                    if (servicosTecnico.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado.");
                    }
                    else {
                        System.out.println("Todos os serviços:");
                        Servicos.listarServicos(servicosTecnico, true);
                    }
                    Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Listou todos os serviços"));
                    Main.pressEnterKey();
                    break;
                case "2":
                    Main.clearConsole();
                    List<Servicos> servicosAceites = servicosTecnico.stream().filter(s -> s.getEstado().equals(Servicos.EstadoServico.ACEITE)).toList();
                    Main.clearConsole();
                    if (servicosAceites.isEmpty()) {
                        System.out.println("Nenhum serviço aceite encontrado.");
                    }
                    else {
                        System.out.println("Serviços aceites:");
                        Servicos.listarServicos(servicosAceites, true);
                    }
                    Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Listou os serviços aceites"));
                    Main.pressEnterKey();
                    break;
                case "3":
                    Main.clearConsole();
                    List<Servicos> servicosConcluidos = servicosTecnico.stream().filter(s -> s.getEstado().equals(Servicos.EstadoServico.CONCLUIDO)).toList();
                    Main.clearConsole();
                    if (servicosConcluidos.isEmpty()) {
                        System.out.println("Nenhum serviço concluído encontrado.");
                    }
                    else {
                        System.out.println("Serviços concluídos:");
                        Servicos.listarServicos(servicosConcluidos, true);
                    }
                    Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Listou os serviços concluídos"));
                    Main.pressEnterKey();
                    break;
                case "4":
                    Main.clearConsole();
                    List<Servicos> submetidos = servicosTecnico.stream().filter(s -> s.getEstado().equals(Servicos.EstadoServico.SUBMETIDO)).toList();
                    Main.clearConsole();
                    if (submetidos.isEmpty()) {
                        System.out.println("Nenhum serviço submetido encontrado.");
                    }
                    else {
                        System.out.println("Serviços submetidos:");
                        Servicos.listarServicos(submetidos, true);
                    }
                    Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Listou os serviços"));
                    Main.pressEnterKey();
                    break;
                case "5":
                    Main.clearConsole();
                    System.out.print("Nome do cliente a pesquisar: ");
                    String cliente = Input.readLine();
                    List<Servicos> servicosPorCliente = servicosTecnico.stream().filter(s -> s.getCliente().getNome().toLowerCase().contains(cliente.toLowerCase())).toList();
                    Main.clearConsole();
                    if (servicosPorCliente.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado para o cliente " + cliente + ".");
                    }
                    else {
                        System.out.println("Serviços do cliente " + cliente + ":");
                        Servicos.listarServicos(servicosPorCliente, true);
                    }
                    Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Listou os serviços do cliente " + cliente));
                    Main.pressEnterKey();
                    break;
                case "6":
                    Main.clearConsole();
                    System.out.println("Vão ser pedidas as semanas, os dias, as horas e os minutos, caso algum deles seja nulo, ou seja, <po exemplo não demorou semanas deve ser 0.");
                    System.out.println("Tempo despendido superior a: ");
                    int tempo = Servicos.tempoGasto();
                    List<Servicos> servicosPorTempo = servicosTecnico.stream().filter(s -> s.getTempoProcessamento() >= tempo).toList();
                    Main.clearConsole();
                    if (servicosPorTempo.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado com tempo despendido superior a " + Servicos.formatarTempo(tempo) + ".");
                    }
                    else {
                        System.out.println("Serviços com tempo despendido superior a " + Servicos.formatarTempo(tempo) + ":");
                        Servicos.listarServicos(servicosPorTempo, true);
                    }
                    Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Listou os serviços com tempo despendido superior a " + Servicos.formatarTempo(tempo)));
                    Main.pressEnterKey();
                    break;
                case "7":
                    Main.clearConsole();
                    System.out.print("Código ou palavra na descrição a pesquisar: ");
                    String pesquisa = Input.readLine();
                    List<Servicos> servicosPesquisados = servicosTecnico.stream().filter(s -> String.valueOf(s.getCodigo()).contains(pesquisa) || s.getDescricao().contains(pesquisa)).toList();
                    if (servicosPesquisados.isEmpty()) {
                        System.out.println("Nenhum serviço encontrado com o código ou palavra na descrição " + pesquisa + ".");
                    }
                    else {
                        System.out.println("Serviços encontrados com o código ou palavra na descrição " + pesquisa + ":");
                        Servicos.listarServicos(servicosPesquisados, true);
                    }
                    Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Pesquisou serviços com o código ou palavra na descrição " + pesquisa));
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
                    listagemServiçosLoop(servicosTecnico);
                    Main.pressEnterKey();
                    break;
                case "2":
                    boolean check = true;
                    do {
                        SubServico subsevico = criarSubServico(servicosTecnico);
                        if (subsevico != null) {
                            System.out.println("Subserviço adicionado com sucesso.");
                            Logs.adicionarLog(new Logs(servicosTecnico.get(0).getTecnicoResponsavel(), new Date(), "Adicionou um subserviço"));
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
                    Main.pressEnterKey();
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
                    Equipamentos.equipamentosLoop(user);
                    break;
                case "3":
                    Fornecedor forne = Fornecedor.adicionarFornecedor();
                    if (forne != null) {
                        Sistema.getInstance().adicionarFornecedor(forne);
                        Logs.adicionarLog(new Logs(user, new Date(), "Adicionou um fornecedor"));
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
                        Logs.adicionarLog(new Logs(user, new Date(), "Adicionou uma categoria"));
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
