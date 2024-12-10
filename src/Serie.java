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
        System.out.print("Geração: ");
        int geracao = Input.readInt();
        System.out.print("Sequência: ");
        int sequencia = Input.readInt();

        return new Serie(geracao, sequencia);
    }

    public String toString() {
        return "Geração " + geracao + ", Sequência " + sequencia;
    }
}
