package src;

public class SubServico {
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
}
