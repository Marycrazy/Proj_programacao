package src;

import java.io.Serializable;

public class Versao implements Serializable {
    private static final long serialVersionUID = 1L;
    private int unidade;
    private int valorAlfa;
    private int valorBeta;

    public Versao(int unidade, int valorAlfa, int valorBeta) {
        this.unidade = unidade;
        this.valorAlfa = valorAlfa;
        this.valorBeta = valorBeta;
    }

    public String toString() {
        return unidade + "." + valorAlfa + "." + valorBeta;
    }
}
