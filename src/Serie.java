package src;

import java.io.Serializable;

public class Serie implements Serializable {
    private static final long serialVersionUID = 1L;
    private int geracao;
    private int sequencia;

    public Serie(int geracao, int sequencia) {
        this.geracao = geracao;
        this.sequencia = sequencia;
    }

    public static Serie adicionarSerie(){
        System.out.print("Adicionar série: \n");
        int geracao =Integer.parseInt(Validator.validateInput("Geração"));
        int sequencia = Integer.parseInt(Validator.validateInput("Sequência"));

        return new Serie(geracao, sequencia);
    }

    public String toString() {
        return "Geração " + geracao + ", Sequência " + sequencia;
    }
}
