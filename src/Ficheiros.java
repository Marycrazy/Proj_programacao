package src;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;


public class Ficheiros {

    public static void doUsersExist() {
        try{
            File arquivo = new File("credenciais_acesso.txt");
            if (arquivo.length() > 0 && arquivo.exists()) {
                System.out.println("O ficheiro existe");
            } else {
                Utilizador admin = Utilizador.registerNewUser(true,"administrador");
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
                try (PrintWriter writer = new PrintWriter(new FileWriter("credenciais_acesso.txt", true))) {
                    writer.println(user.getLogin() + ", " + user.getPassword());
                    System.out.println("Dados escritos com sucesso.");
                } catch (IOException e) {
                    System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
                }
            }


    //inserir dados num ficheiro de objetos
    public static void insertObjectFicheiro(Utilizador user) {
        System.out.println("Inserting user into file...");
        try (PrintWriter writer = new PrintWriter(new FileWriter("dados_apl.dat", true))) {
            writer.println(user.getLogin() + ", " + user.getPassword() + ", " + user.getNome() + ", " + user.getEstado() + ", " + user.getEmail() + ", " + user.getTipo());
            System.out.println("Dados escritos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }

    //mostrar dados de um ficheiro de objetos
    public static void showObjectFileContents() {
        System.out.println("Showing contents of object file...");
        try {
            File arquivo = new File("dados_apl.dat");
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
}
