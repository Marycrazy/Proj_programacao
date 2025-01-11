package src;
import java.io.Serializable;

public class InfoSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int numeroExecucoes;
    private static String ultimoUtilizador;

    public void incrementarExecucoes() {
        numeroExecucoes++;
    }

    public void setUltimoUtilizador(String login) {
        ultimoUtilizador = login;
    }

    public int getNumeroExecucoes() {
        return numeroExecucoes;
    }

    public String getUltimoUtilizador() {
        return ultimoUtilizador;
    }

    public static InfoSistema carregarDados() {
        InfoSistema infoSistema = Ficheiros.carregarInfoSistema();
        System.out.println("Carregando informações do sistema... " + infoSistema);
        infoSistema.incrementarExecucoes();
        return infoSistema;
    }

    public static void save(InfoSistema infoSistema) {
       Ficheiros.salvarInfoSistema(infoSistema);
    }

    public static void listarInfoSistema() {
        System.out.println("\n=== Informações do Sistema ===");
        System.out.println("Número de execuções: " + numeroExecucoes);
        if (!ultimoUtilizador.isEmpty()) {
            System.out.println("Último utilizador: " + ultimoUtilizador);
        }
        System.out.println("============================\n");
    }
}
