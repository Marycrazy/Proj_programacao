package src;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


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
        try{
            Input.openFileWrite("docs/credenciais_acesso.txt", true);
            Input.writeFileLine(user.getLogin() + ", " + user.getPassword());
            System.out.println("Dados escritos com sucesso.");
            Input.closeFile();
        } catch (Exception e) {
            System.out.println("Erro ao inserir o utilizador no ficheiro: " + e.getMessage());
        }
    }


    //inserir dados num ficheiro de objetos
    public static void insertObjectFicheiro(Utilizador user) {
        System.out.println("Inserting user into file...");
        String filePath = "docs/dados_apl.dat";
        List<Utilizador> users = new ArrayList<>();

        // Tenta carregar os objetos existentes no arquivo
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filePath))) {
            users = (List<Utilizador>) reader.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando um novo arquivo.");
        } catch (EOFException | ClassNotFoundException e) {
            System.out.println("Arquivo vazio ou sem dados existentes.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Adiciona o novo objeto `user` à lista
        users.add(user);

        // Grava a lista inteira de volta ao arquivo
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filePath))) {
            writer.writeObject(users);
            System.out.println("Dados escritos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }

    // Método de leitura de objetos do arquivo
    public static List<Utilizador> readObjectsFicheiro() {
        System.out.println("Reading users from file...");
        List<Utilizador> users = new ArrayList<>();
        String filePath = "docs/dados_apl.dat";

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filePath))) {
            users = (List<Utilizador>) reader.readObject();
            System.out.println("Dados lidos com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Nenhum dado a carregar.");
        } catch (EOFException e) {
            System.out.println("Arquivo vazio.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return users;
    }


    public static Utilizador authenticateUser(String login, String password) {
        System.out.println("Authenticating user...");
        try {
            Input.openFIleReade("docs/credenciais_acesso.txt");
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
            Main.pressEnterKey();
        } catch (Exception e) {
            System.out.println("Erro ao autenticar o utilizador: " + e.getMessage());
        }
        return null;
    }

    private static Utilizador getUserDetails(String login) {
        System.out.println("Searching for user with login: " + login);
        List<Utilizador> users = readObjectsFicheiro();
        for (Utilizador user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        System.out.println("Utilizador não encontrado com login: " + login);
        return null;
    }

}
