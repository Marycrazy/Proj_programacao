package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;

    // Listas para armazenar utilizadores, serviços e equipamentos
    private List<Utilizador> utilizadores;
    /*private List<Servico> servicos;
    private List<Equipamento> equipamentos;*/
    private static Sistema sistema;

    public static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    public static void setInstance(Sistema sistemaCarregado) {
        sistema = sistemaCarregado;
    }

    // Construtor
    public Sistema() {
        this.utilizadores = new ArrayList<>();
        /*this.servicos = new ArrayList<>();
        this.equipamentos = new ArrayList<>();*/
    }

    public void save() {
        Ficheiros.insertUserFicheiro(sistema);
        Main.pressEnterKey();
        Ficheiros.insertObjectFicheiro(sistema);
        Main.pressEnterKey();
    }

    public void adicionarutilizador(Utilizador utilizador) {
        if (utilizador == null) {
            System.out.println("Tentativa de adicionar um utilizador nulo!");
            return;
        }
        this.utilizadores.add(utilizador);
        System.out.println("utilizador adicionado: " + utilizador.getLogin());
    }

    /*public void adicionarServico(Servico servico) {
        this.servicos.add(servico);
    }

    public void adicionarEquipamento(Equipamento equipamento) {
        this.equipamentos.add(equipamento);
    }*/

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void carregarDados() {
        Sistema sistemaCarregado = Ficheiros.carregarDadosSistema();
        if (sistemaCarregado != null) {
            Sistema.setInstance(sistemaCarregado);
        }
        else if (getInstance().getUtilizadores().isEmpty()) {
            System.out.println("Nenhum utilizador encontrado no sistema.");
        }
    }

    public Utilizador buscarUtilizadorPorLogin(String login) {
        return utilizadores.stream()
            .filter(u -> u.getLogin().equalsIgnoreCase(login))
            .findFirst()
            .orElse(null); // Retorna null se não encontrar
    }

    public List<Utilizador> getUtilizadoresInativos() {
        List<Utilizador> inativos = utilizadores.stream().filter(u -> !u.getEstado()).toList();
        if (inativos.isEmpty()) {
            return null;
        }
        else {
            return inativos;
        }
    }

    public Utilizador autenticarUtilizador(String login, String password) {
        boolean authenticated = Ficheiros.authenticateUser(login, password);
        if (authenticated) {
            System.out.println("Utilizador autenticado com sucesso.");
            Main.pressEnterKey();
            return buscarUtilizadorPorLogin(login);
        } else {
            System.out.println("Falha na autenticação do utilizador.");
            return null;
        }
    }

//eliminar no final esta função
    public void exibirDados() {
        System.out.println("Utilizadores cadastrados:");
        for (Utilizador utilizador : utilizadores) {
            System.out.println("Login: " + utilizador.getLogin() +
                               ", Nome: " + utilizador.getNome() +
                               ", Email: " + utilizador.getEmail() +
                               ", Tipo: " + utilizador.getTipo() +
                               ", Estado: " + utilizador.getEstado());
            if (utilizador instanceof Tecnicos) {
                Tecnicos tecnico = (Tecnicos) utilizador;
                System.out.println("Nif: " + tecnico.getNIF());
                System.out.println("Morada: " + tecnico.getMorada());
                System.out.println("Telefone: " + tecnico.getTelefone());
            }
            else if (utilizador instanceof Cliente) {
                Cliente clien = (Cliente) utilizador;
                System.out.println("Nif: " + clien.getNIF());
                System.out.println("Morada: " + clien.getMorada());
                System.out.println("Telefone: " + clien.getTelefone());
            }
        }
    }

}
