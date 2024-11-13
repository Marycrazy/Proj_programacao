package src;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;


public class Ficheiros {

    public static void doUsersExist() {
        try{
            File arquivo = new File("docs/credenciais_acesso.txt");
            if (arquivo.length() > 0 && arquivo.exists()) {
                System.out.println("O ficheiro existe");
            } else {
                Utilizador admin = Utilizador.registerNewUser("ativado","administrador");
                insertUserFicehiro(admin);
                Main.pressEnterKey();
                insertObjectFicheiro(admin);
            }
        } catch (Exception e) {
                    System.out.println("Erro ao verificar se o ficheiro existe: " + e.getMessage());
                }
            }

            public static void insertUserFicehiro(Utilizador user) {
                System.out.println("Inserting user into file...");
                try (PrintWriter writer = new PrintWriter(new FileWriter("docs/credenciais_acesso.txt", true))) {
                    writer.println(user.getLogin() + ", " + user.getPassword());
                    System.out.println("Dados escritos com sucesso.");
                } catch (IOException e) {
                    System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
                }
            }


    //inserir dados num ficheiro de objetos
    public static void insertObjectFicheiro(Utilizador user) {
        System.out.println("Inserting user into file...");
        try (PrintWriter writer = new PrintWriter(new FileWriter("docs/dados_apl.dat", true))) {
            switch (user.getTipo()) {
                case "administrador":
                    writer.println(user.getLogin() + ", " + user.getPassword() + ", " + user.getNome() + ", " + user.getEstado() + ", " + user.getEmail() + ", " + user.getTipo());
                    break;
                case "tecnico":
                    writer.println(user.getLogin() + ", " + user.getPassword() + ", " + user.getNome() + ", " + user.getEstado() + ", " + user.getEmail() + ", " + user.getTipo() + ", " + ((Tecnicos) user).getNIF() + ", " + ((Tecnicos) user).getMorada() + ", " + ((Tecnicos) user).getTelefone());
                    break;
                case "cliente":
                    writer.println(user.getLogin() + ", " + user.getPassword() + ", " + user.getNome() + ", " + user.getEstado() + ", " + user.getEmail() + ", " + user.getTipo() + ", " + ((Cliente) user).getNIF() + ", " + ((Cliente) user).getMorada() + ", " + ((Cliente) user).getTelefone());
                    break;
                default:
                    break;
            }
            System.out.println("Dados escritos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }

    //mostrar dados de um ficheiro de objetos
    public static void showObjectFileContents() {
        System.out.println("Showing contents of object file...");
        try {
            File arquivo = new File("docs/dados_apl.dat");
            if (arquivo.exists()) {
                Scanner scanner = new Scanner(arquivo);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
                scanner.close();
            } else {
                System.out.println("O ficheiro não existe.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    public static Utilizador authenticateUser(String login, String password) {
        System.out.println("Authenticating user...");
        try {
            Input.openFile("docs/credenciais_acesso.txt");
            String line;
            while ((line = Input.readFileLine()) != null) {
                String[] credentials = line.split(", ");
                if (credentials[0].equals(login) && credentials[1].equals(password)) {
                    Input.closeFile();
                    return getUserDetails(login);
                }
            }
            Input.closeFile();
            System.out.println("Login ou password incorretos.");
        } catch (Exception e) {
            System.out.println("Erro ao autenticar o utilizador: " + e.getMessage());
        }
        return null;
    }

    private static Utilizador getUserDetails(String login) {
        System.out.println("Fetching user details...");
        try {
            Input.openFile("docs/dados_apl.dat");
            String line;
            while ((line = Input.readFileLine()) != null) {
                String[] userDetails = line.split(", ");
                    if (userDetails[0].equals(login)) {
                        switch (userDetails[5]) {
                            case "administrador":
                                return new Administrador(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5]);
                            case "tecnico":
                                return new Tecnicos(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5], userDetails[6], userDetails[7], userDetails[8]);
                            case "cliente":
                                return new Cliente(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5], userDetails[6], userDetails[7], userDetails[8]);
                            default:
                                break;
                        }
                    }
            }
            Input.closeFile();
        } catch (Exception e) {
            System.out.println("Erro ao obter detalhes do utilizador: " + e.getMessage());
        }
        return null;
    }

}
