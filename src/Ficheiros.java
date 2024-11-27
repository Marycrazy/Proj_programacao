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

    public static Sistema carregarDadosSistema() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("docs/dados_apl.dat"))) {
            Sistema sistemaCarregado = (Sistema) ois.readObject();
            System.out.println("Dados carregados com sucesso de dados_apl.dat:");
            for (Utilizador utilizador : sistemaCarregado.getUtilizadores()) {
                System.out.println("Login: " + utilizador.getLogin() +
                                   ", Nome: " + utilizador.getNome() +
                                   ", Email: " + utilizador.getEmail() +
                                   ", Tipo: " + utilizador.getTipo());
            }
            return sistemaCarregado;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Inicializando um novo sistema.");
        } catch (EOFException e) {
            System.out.println("Arquivo vazio. Inicializando um novo sistema.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
        return new Sistema(); // Retorna um novo sistema vazio em caso de erro
    }

}
