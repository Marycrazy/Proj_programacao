package src;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Ficheiros {

    public static void doUsersExist() {
        try{
            File arquivo = new File("docs/credenciais_acesso.txt");
            File arquivo2 = new File("docs/dados_apl.dat");
            if (arquivo.length() > 0 && arquivo.exists() && arquivo2.exists() && arquivo2.length() > 0) {
                System.out.println("O ficheiro existe");
            } else {
                Utilizador admin = Utilizador.registerNewUser("ativado","administrador");
                Main.pressEnterKey();
                Sistema.getInstance().adicionarUsuario(admin);
            }
        } catch (Exception e) {
                    System.out.println("Erro ao verificar se o ficheiro existe: " + e.getMessage());
                }
    }

    public static void insertUserFicheiro(Sistema sistema) {
        System.out.println("Inserting user into file...");
        try {
            Input.openFileWrite("docs/credenciais_acesso.txt", false);
            List<Utilizador> utilizadores = sistema.getUtilizadores();
            System.out.println("Utilizadores: " + utilizadores.get(0).getLogin());
            if (!utilizadores.isEmpty()) {
                Input.writeFileLine(utilizadores.get(0).getLogin() + ";" + utilizadores.get(0).getPassword());
                System.out.println("Dados escritos com sucesso.");
                Main.pressEnterKey();
            } else {
                System.out.println("Nenhum utilizador encontrado.");
            }
            Input.closeFile();
        } catch (Exception e) {
            System.out.println("Erro ao inserir o utilizador no ficheiro: " + e.getMessage());
        }
    }

    //inserir dados num ficheiro de objetos
    public static void insertObjectFicheiro (Sistema sistema) {
        try (ObjectOutputStream oos = Input.openObjectWrite("docs/dados_apl.dat", false)) {
            oos.writeObject(sistema);
            System.out.println("Dados da aplicação salvos com sucesso em dados_apl.dat.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados da aplicação: " + e.getMessage());
        }
    }

    // Método de leitura de objetos do arquivo
    public static List<Utilizador> readObjectsFicheiro() {
        System.out.println("Reading users from file...");
        List<Utilizador> users = new ArrayList<>();
        String filePath = "docs/dados_apl.dat";

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filePath))) {
            users = (List<Utilizador>) reader.readObject();
            for (int i= 0; i< users.size(); i++) {
                System.err.println("nome: " + users.get(i).getNome());
                System.err.println("email: " + users.get(i).getEmail());
                System.err.println("type: " + users.get(i).getTipo());
            }
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
}
