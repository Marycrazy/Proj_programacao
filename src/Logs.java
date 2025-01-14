package src;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Logs implements Serializable {
    private static final long serialVersionUID = 1L;
    private Utilizador utilizador;
    private Date data;
    private String acao;

    private static List<Logs> logs;

    public static List<Logs> getInstance() {
        if (logs == null) {
            logs = new ArrayList<>();
        }
        return logs;
    }

    public static void setInstance(List<Logs> logsCarregados) {
        logs = logsCarregados;
    }

    public Logs(Utilizador utilizador, Date data, String acao) {
        this.utilizador = utilizador;
        this.data = data;
        this.acao = acao;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public Date getDate() {
        return data;
    }

    public String getAcao() {
        return acao;
    }

    public static void adicionarLog(Logs log) {
        if (log == null) {
            System.out.println("Tentativa de adicionar um log nulo!");
            return;
        }
        logs.add(log);
        System.out.println("Log adicionado: " + log.getAcao());
    }

    public static void save() {
        Ficheiros.insertLogs(logs);
        Main.pressEnterKey();
    }

    public static void carregarLogs() {
        List <Logs> log = Ficheiros.carregarLogs();
        if (log != null) {
            Logs.setInstance(log);
        }
        else {
            System.out.println("Não foi possível carregar os logs.");
        }
    }

    public void listarLogs() {
        for (Logs log : logs) {
            System.out.println("Utilizador: " + log.getUtilizador().getLogin());
            System.out.println("Data: " + log.getDate());
            System.out.println("Ação: " + log.getAcao());
        }
    }
}
