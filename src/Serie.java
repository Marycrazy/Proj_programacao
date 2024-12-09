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

    public String toString() {
        return "Geração " + geracao + ", Sequência " + sequencia;
    }
}
