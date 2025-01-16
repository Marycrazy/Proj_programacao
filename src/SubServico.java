package src;

import java.io.Serializable;
import java.util.List;

public class SubServico implements Serializable {
    private static final long serialVersionUID = 1L;
    private String designacao;
    private Tecnicos tecnicoresponsavel;

    public SubServico(String designacao, Tecnicos tecnicoresponsavel) {
        this.designacao = designacao;
        this.tecnicoresponsavel = tecnicoresponsavel;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public Tecnicos getTecnicoresponsavel() {
        return tecnicoresponsavel;
    }

    public void setTecnicoresponsavel(Tecnicos tecnicoresponsavel) {
        this.tecnicoresponsavel = tecnicoresponsavel;
    }

    public static void listarSubServico(List<SubServico> subServicos) {
        if (subServicos.isEmpty()) {
            System.out.println("Nao existem sub-servicos. \n");
            return;
        }
        else{
            for (int i = 0; i < subServicos.size(); i++) {
                System.out.println("Designacao: " + subServicos.get(i).getDesignacao());
                System.out.println("Tecnico Responsavel: " + subServicos.get(i).getTecnicoresponsavel().getNome());
            }
        }
    }
}
