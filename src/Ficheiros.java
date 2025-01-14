package src;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Ficheiros {

    public static boolean doUsersExist() {
        try{
            File arquivo = new File("docs/credenciais_acesso.txt");
            File arquivo2 = new File("docs/dados_apl.dat");
            if (arquivo.length() > 0 && arquivo.exists() && arquivo2.exists() && arquivo2.length() > 0) {
                System.out.println("O ficheiro existe");
                return true;
            } else {
                System.out.println("O ficheiro não existe");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se o ficheiro existe: " + e.getMessage());
            return false;
        }
    }

    public static void insertUserFicheiro(Sistema sistema) {
        System.out.println("Inserting user into file...");
        try {
            Input.openFileWrite("docs/credenciais_acesso.txt", false);
            List<Utilizador> utilizadores = sistema.getUtilizadores();
            if (!utilizadores.isEmpty()) {
                for (Utilizador utilizador : utilizadores) {
                    Input.writeFileLine(utilizador.getLogin() + ";" + utilizador.getPassword());
                }
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

    public static boolean authenticateUser(String login, String password) {
        System.out.println("Authenticating user...");
        try {
            Input.openFIleReade("docs/credenciais_acesso.txt");
            String line;
            while ((line = Input.readFileLine()) != null) {
                String[] credentials = line.split(";");
                if (credentials[0].equalsIgnoreCase(login) && credentials[1].equals(password)) {
                    Input.closeFile();
                    return true;
                }
            }
            Input.closeFile();
            System.out.println("Login ou password incorretos.");
        } catch (Exception e) {
            System.out.println("Erro ao autenticar o utilizador: " + e.getMessage());
        }
        return false;
    }

    public static Sistema carregarDadosSistema() {
        if (doUsersExist()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("docs/dados_apl.dat"))) {
                Sistema sistemaCarregado = (Sistema) ois.readObject();
                System.out.println("Dados carregados com sucesso de dados_apl.dat:");
                return sistemaCarregado;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado. Inicializando um novo sistema.");
            } catch (EOFException e) {
                System.out.println("Arquivo vazio. Inicializando um novo sistema.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar os dados: " + e.getMessage());
            }
            return new Sistema(); // Retorna um novo sistema vazio em caso de err
        }
        else{
            Utilizador admin = Utilizador.registerNewUser(true,"administrador");
            Main.pressEnterKey();
            Sistema.getInstance().adicionarutilizador(admin);
        }
        return null;
    }

    public static void salvarInfoSistema(InfoSistema info) {
        try (ObjectOutputStream oos = Input.openObjectWrite("docs/info_sistema.dat", false)) {
            oos.writeObject(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InfoSistema carregarInfoSistema() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("docs/info_sistema.dat"))) {
            InfoSistema info = (InfoSistema) ois.readObject();
            System.out.println("Informações do sistema carregadas com sucesso.");
            return info;
        } catch (FileNotFoundException e) {
            return new InfoSistema();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new InfoSistema();
        }
    }

    public static void insertLogs(List<Logs> logs) {
        System.out.println("Inserting logs into file...");
        try {
            Input.openFileWrite("docs/log.txt", false);
            if (!logs.isEmpty()) {
                for (int i = 0; i < logs.size(); i++) {
                    Input.writeFileLine(logs.get(i).getUtilizador().getLogin() + ";" + logs.get(i).getDate() + ";" + logs.get(i).getAcao());
                }
                System.out.println("Logs escritos com sucesso.");
            } else {
                System.out.println("Nenhum log encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir os logs no ficheiro: " + e.getMessage());
        }
    }

    public static List<Logs> carregarLogs() {
        List<Logs> registros = new ArrayList<>();
        try {
            Input.openFIleReade("docs/log.txt");
            String line;
            while ((line = Input.readFileLine()) != null) {
                String[] dados = line.split(";");
                //obter utilizador por login
                Utilizador utilizador = Sistema.getInstance().buscarUtilizadorPorLogin(dados[0]);
                // Converter a string da data para Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date data = dateFormat.parse(dados[1]);
                String acao = dados[2];
                if (utilizador != null) {
                    Logs log = new Logs(utilizador, data, acao);
                    registros.add(log);
                }
            }
            Input.closeFile();
            return registros;
        } catch (Exception e) {
            System.out.println("Erro ao carregar os logs: " + e.getMessage());
        }
        return null;
    }

}