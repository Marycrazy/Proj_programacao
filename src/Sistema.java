package src;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    // Listas para armazenar usuários, serviços e equipamentos
    private List<Utilizador> utilizadores;
    // private List<Servico> servicos;
    // private List<Equipamento> equipamentos;

    // Construtor
    public Sistema() {
        this.utilizadores = new ArrayList<>();
        //this.servicos = new ArrayList<>();
        //this.equipamentos = new ArrayList<>();
    }

    public void adicionarUsuario(Utilizador utilizador) {
        utilizadores.add(utilizador);
        Ficheiros.insertObjectFicheiro(utilizadores);
        Ficheiros.insertUserFicehiro(utilizadores);
        System.out.println("utilizador adicionado.");
    }
}

