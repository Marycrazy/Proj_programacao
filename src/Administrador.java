package src;

import java.util.Comparator;
import java.util.List;

public class Administrador extends Utilizador {

    // Constructor
    public Administrador(String login, String password, String nome, boolean estado, String email, String tipo) {
        super(login, password, nome, estado, email, tipo);
    }

    public static Administrador registerNewUser(boolean estado, String type) {
        Main.clearConsole();
        String login = Validator.validateInput("Login");
        String password = Validator.validatePassword("Password");
        String name = Validator.validateInput("Name");
        String email = Validator.validateInput("Email");
        return new Administrador(login, password, name, estado, email, type);
    }

    private static List<Utilizador> getUtilizadoresInativos() {
        List<Utilizador> inativos = Sistema.getInstance().getUtilizadores().stream().filter(u -> !u.getEstado()).toList();
        if (inativos.isEmpty()) {
            return null;
        }
        else {
            return inativos;
        }
    }

    private static void perfilUtilizador(Utilizador user, boolean mostrar) {
        if (mostrar) {
            System.out.println("*******************");
            System.out.println("Perfil           ");
            System.out.println("Login: " + user.getLogin());
            System.out.println("Nome: " + user.getNome());
            System.out.println("Email: " + user.getEmail());
            System.out.println("******************* \n");
        }
        else {
            System.out.println("Login: " + user.getLogin() + "\nNome:" + 
                user.getNome() + "\nEmail: " + user.getEmail() + "\nTipo: " + 
                user.getTipo() + "\n");
        }
    }

    private static void utilizadoresRegeditados(List<Utilizador> inativos) {
        if (inativos == null) {
            System.out.println("Nenhum utilizador inativo encontrado.");
            return;
        }
        else {
            System.out.println("\nUtilizadores inativos:");
            for (int i = 0; i < inativos.size(); i++) {
                System.out.println((i + 1) + ".");
                perfilUtilizador(inativos.get(i), false);
            }
        }
    }

    private static void aprovarRegisto() {
        List<Utilizador> inativos = getUtilizadoresInativos();
        utilizadoresRegeditados(inativos);
        boolean running = true;
        do {
            System.out.print("Selecione o utilizador a aprovar (ou 0 para cancelar): ");
            int escolha = Input.readInt();
            if (escolha==0) {
                System.out.println("A voltar ao menu principal.");
                running = false;
                break;
            }
            else {
                inativos.get(escolha - 1).setEstado(true);
                System.out.println("Utilizador " + inativos.get(escolha - 1).getLogin() + " aprovado com sucesso!");
                Input.clearBuffer();
                running = false;
            }
        } while (running);
        Main.pressEnterKey();
    }

    private static void editperfil(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            perfilUtilizador(user, true);
            Main.pressEnterKey();
            System.out.println("|-------------------|");
            System.out.println("|Editar perfil      |");
            System.out.println("|1. Editar Login    |");
            System.out.println("|2. Editar password |");
            System.out.println("|3. Editar nome     |");
            System.out.println("|4. Editar email    |");
            System.out.println("|5. Sair            |");
            System.out.println("|-------------------|");
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

    private static void listagemUtilizadores(){
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|-----------------------------------|");
            System.out.println("|Listar Utilizadores                |");
            System.out.println("|1. ordem alfabética do nome        |");
            System.out.println("|2. listar todos os utilizadores    |");
            System.out.println("|3. listar utilizadores por tipo.   |");
            System.out.println("|4. Pesquisar por nome ou login     |");
            System.out.println("|5. Sair                            |");
            System.out.println("|-----------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    List<Utilizador> oredemAlfabetica = Sistema.getInstance().getUtilizadores().stream().sorted(Comparator.comparing(Utilizador::getNome)).toList();
                    Main.clearConsole();
                    System.out.println("Utilizadores por ordem alfabética:");
                    for (int i = 0; i < oredemAlfabetica.size(); i++) {
                        perfilUtilizador(oredemAlfabetica.get(i), false);
                    }
                    Main.pressEnterKey();
                    break;
                case "2":
                    Main.clearConsole();
                    System.out.println("Todos os utilizadores:");
                    for (int i = 0; i < Sistema.getInstance().getUtilizadores().size(); i++) {
                        perfilUtilizador(Sistema.getInstance().getUtilizadores().get(i), false);
                    }
                    Main.pressEnterKey();
                    break;
                case "3":
                    System.out.println("Tipo de utilizador a listar: ");
                    String tipo = Input.readLine();
                    List<Utilizador> utilizadoresPorTipo = Sistema.getInstance().getUtilizadores().stream().filter(u -> u.getTipo().equalsIgnoreCase(tipo)).toList();
                    Main.clearConsole();
                    System.out.println("Utilizadores do tipo " + tipo + ":");
                    for (int i = 0; i < utilizadoresPorTipo.size(); i++) {
                        perfilUtilizador(utilizadoresPorTipo.get(i), false);
                    }
                    Main.pressEnterKey();
                    break;
                case "4":
                    System.out.print("Nome ou login a pesquisar: ");
                    String pesquisa = Input.readLine();
                    List<Utilizador> utilizadoresPesquisados = Sistema.getInstance().getUtilizadores().stream().filter(u -> u.getNome().contains(pesquisa) || u.getLogin().contains(pesquisa)).toList();
                    Main.clearConsole();
                    System.out.println("Utilizadores encontrados:");
                    for (int i = 0; i < utilizadoresPesquisados.size(); i++) {
                        perfilUtilizador(utilizadoresPesquisados.get(i), false);
                    }
                    Main.pressEnterKey();
                    break;
                case "5":
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

    private static List<Servicos> getServicosSubmetidos() {
        List<Servicos> submetidos = Sistema.getInstance().getServicos().stream().filter(s -> s.getEstado().equals(Servicos.EstadoServico.SUBMETIDO)).toList();
        if (submetidos.isEmpty()) {
            return null;
        }
        else {
            return submetidos;
        }
    }

    private static void aprovadoServico() {
        List<Servicos> submetidos = getServicosSubmetidos();
        Servicos.listarServicos(submetidos, false);
        System.out.print("Selecione o serviço a aprovar (ou 0 para cancelar): ");
        int escolha = Input.readInt();
        if (escolha == 0) {
            System.out.println("A voltar ao menu principal.");
            Main.pressEnterKey();
        }
        else {
            submetidos.get(escolha - 1).setEstado(Servicos.EstadoServico.ACEITE);
            System.out.println("Serviço " + submetidos.get(escolha - 1).getCliente().getNome() + " aprovado com sucesso!");
            System.out.println("Associar um tecnico responsável ao serviço.");
            associarTecnico(submetidos.get(escolha - 1));
            System.out.println("Tecnico associado com sucesso!");
            Input.clearBuffer();
            Main.pressEnterKey();
        }
    }

    private static void associarTecnico(Servicos servicos){
        List<Utilizador> utilizadoresPorTipo = Sistema.getInstance().getUtilizadores().stream()
        .filter(u -> u.getTipo().equalsIgnoreCase("tecnico") && u.getEstado())
        .toList();
        System.out.println("Tecnicos:");
                    for (int i = 0; i < utilizadoresPorTipo.size(); i++) {
                        System.out.println((i + 1) + ".");
                        perfilUtilizador(utilizadoresPorTipo.get(i), false);
                    }
        System.out.print("Selecione o tecnico a associar ao serviço:");
        int escolha = Input.readInt();
        if (utilizadoresPorTipo.get(escolha - 1).getEstado()) {
            Tecnicos tecnicoSelecionado = (Tecnicos) utilizadoresPorTipo.get(escolha - 1);
            servicos.setTecnicoResponsavel(tecnicoSelecionado);
            System.out.println("Técnico " + tecnicoSelecionado.getNome() + " associado com sucesso ao serviço.");
            Input.clearBuffer();
            Main.pressEnterKey();
        }
        else {
            System.out.println("Tecnico inativo. Por favor selecione outro.");
            Input.clearBuffer();
            Main.pressEnterKey();
            associarTecnico(servicos);
        }
    }

    private static void listagemServiços(){
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|-----------------------------------|");
            System.out.println("|Listar Serviços                    |");
            System.out.println("|1. Listar todos  os serviços       |");
            System.out.println("|2. Listar serviços aceites         |");
            System.out.println("|3. Listar serviços concluídos      |");
            System.out.println("|4. Sair                            |");
            System.out.println("|-----------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    List<Servicos> todosServicos = Sistema.getInstance().getServicos();
                    Servicos.listarServicos(todosServicos, true);
                    Main.pressEnterKey();
                    break;
                case "2":
                    
                    break;
                case "3":
                    
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
            System.out.println("|-------------------------------------|");
            System.out.println("|Tens "+ (getUtilizadoresInativos() != null ? getUtilizadoresInativos().size() : 0) + " pedidos de registo por aprovar|");
            System.out.println("|Tens "+ (getServicosSubmetidos() != null ? getServicosSubmetidos().size() : 0) + " pedidos de serviço por aprovar|");
            System.out.println("|-------------------------------------|");
            System.out.println("|1. Editar perfil                     |");
            System.out.println("|2. Aprovar Registo                   |");
            System.out.println("|3. Aprovar Serviço                   |");
            System.out.println("|4. Listagem de utilizadores          |");
            System.out.println("|5. Listagem de serviços              |");
            System.out.println("|6. Sair                              |");
            System.out.println("|-------------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    editperfil(user);
                    break;
                case "2":
                    aprovarRegisto();
                    break;
                case "3":
                    System.out.println("Aprovar Serviço");
                    aprovadoServico();
                    break;
                case "4":
                    listagemUtilizadores();
                    break;
                case "5":
                    listagemServiços();
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
}