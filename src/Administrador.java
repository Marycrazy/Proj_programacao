package src;

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

    private static void perfilUtilizador(Utilizador user) {
        System.out.println("*******************");
        System.out.println("Perfil           ");
        System.out.println("Login: " + user.getLogin());
        System.out.println("Nome: " + user.getNome());
        System.out.println("Email: " + user.getEmail());
        System.out.println("******************* \n");
    }

    private static void utilizadoresRegeditados(List<Utilizador> inativos) {
        if (inativos == null) {
            System.out.println("Nenhum utilizador inativo encontrado.");
            return;
        }
        else {
            System.out.println("\nUtilizadores inativos:");
            for (int i = 0; i < inativos.size(); i++) {
                System.out.println((i + 1) + ". \nLogin: " + inativos.get(i).getLogin() + "\nNome:" + 
                inativos.get(i).getNome() + "\nEmail: " + inativos.get(i).getEmail() + "\nTipo: " + 
                inativos.get(i).getTipo() + "\n");
            }
        }
    }

    private static void aprovarRegisto() {
        List<Utilizador> inativos = Sistema.getInstance().getUtilizadoresInativos();
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
                Utilizador utilizador = inativos.get(escolha - 1);
                utilizador.setEstado(true);
                System.out.println("Utilizador " + utilizador.getLogin() + " aprovado com sucesso!");
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
            perfilUtilizador(user);
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

    public static void loggedUserLoop(Utilizador user) {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|-------------------------------------|");
            System.out.println("|Tens "+ (Sistema.getInstance().getUtilizadoresInativos() != null ? Sistema.getInstance().getUtilizadoresInativos().size() : 0) + " pedidos de registo por aprovar|");
            System.out.println("|-------------------------------------|");
            System.out.println("|1. Editar perfil                     |");
            System.out.println("|2. Aprovar Registo                   |");
            System.out.println("|4. Sair                              |");
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
}