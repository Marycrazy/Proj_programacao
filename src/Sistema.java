package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;

    // Listas para armazenar usuários, serviços e equipamentos
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

    public void adicionarUsuario(Utilizador utilizador) {
        this.utilizadores.add(utilizador);
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



    public void exibirDados() {
        System.out.println("Utilizadores cadastrados:");
        for (Utilizador utilizador : utilizadores) {
            System.out.println("Login: " + utilizador.getLogin() +
                               ", Nome: " + utilizador.getNome() +
                               ", Email: " + utilizador.getEmail() +
                               ", Tipo: " + utilizador.getTipo());
        }
    }

}

