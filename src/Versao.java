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

    public static Versao adicionarVersao(){
        System.out.print("Adicionar versão: \n");
        System.out.print("Unidade: ");
        int unidade = Input.readInt();
        System.out.print("Valor Alfa: ");
        int valorAlfa = Input.readInt();
        System.out.print("Valor Beta: ");
        int valorBeta = Input.readInt();
        return new Versao(unidade, valorAlfa, valorBeta);
    }

    public String toString() {
        return unidade + "." + valorAlfa + "." + valorBeta;
    }
}
