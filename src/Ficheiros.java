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
        Sistema sistema = new Sistema();
        try{
            File arquivo = new File("docs/credenciais_acesso.txt");
            //File arquivo2 = new File("docs/dados_apl.dat");
            if (arquivo.length() > 0 && arquivo.exists()) {
                System.out.println("O ficheiro existe");
            } else {
                Utilizador admin = Utilizador.registerNewUser("ativado","administrador");
                Main.pressEnterKey();
                sistema.adicionarUsuario(admin);
            }
        } catch (Exception e) {
                    System.out.println("Erro ao verificar se o ficheiro existe: " + e.getMessage());
                }
    }

    public static void insertUserFicehiro(List<Utilizador> utilizadores) {
        System.out.println("Inserting user into file...");
        try{
            Input.openFileWrite("docs/credenciais_acesso.txt", true);
            Input.writeFileLine(utilizadores.get(0).getLogin() + ";" + utilizadores.get(0).getPassword() );
            System.out.println("Dados escritos com sucesso.");
            Input.closeFile();
        } catch (Exception e) {
            System.out.println("Erro ao inserir o utilizador no ficheiro: " + e.getMessage());
        }
    }

    //inserir dados num ficheiro de objetos
    public static void insertObjectFicheiro (List<Utilizador> utilizadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("docs/dados_apl.dat"))) {
            oos.writeObject(utilizadores);
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
            System.err.println("nome: " + users.get(0).getNome());
            System.err.println("email: " + users.get(0).getEmail());
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
