package src;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Logs implements Serializable {
    private static final long serialVersionUID = 1L;
    private Utilizador utilizador;
    private Date data;
    private String acao;

    private static List<Logs> logs = new ArrayList<>();


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
        Collections.sort(logs, Comparator.comparing(Logs::getDate).reversed());
        Ficheiros.insertLogs(logs);
        Main.pressEnterKey();
    }

    public static void carregarLogs() {
        List <Logs> log = Ficheiros.carregarLogs();
        if (log != null && !log.isEmpty()) {
            Collections.sort(logs, Comparator.comparing(Logs::getDate).reversed());
            Logs.setInstance(log);
            System.out.println("Logs carregados com sucesso.");
        }
        else {
            System.out.println("Não foi possível carregar os logs.");
        }
    }

    public static void listarLogs() {
        for (Logs log : logs) {
            System.out.println("\nUtilizador: \nLogin: " + log.getUtilizador().getLogin() + "\tNome: " + log.getUtilizador().getNome());
            System.out.println("Data: " + log.getDate());
            System.out.println("Ação: " + log.getAcao());
        }
    }
}
