package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Sistema implements Serializable {
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
}

