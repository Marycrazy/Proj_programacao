package src;
import java.io.Serializable;

public class Categoria implements Serializable {
    private String designacao;
    private String familia;

    public Categoria(String designacao, String familia) {
        this.designacao = designacao;
        this.familia = familia;
    }

    public String getDesignacao() {
        return designacao;
    }
    public String getFamilia() {
        return familia;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public static Categoria adicionarCategoria() {
        System.out.print("Adicionar categoria: \n");
        System.out.print("Designação: ");
        String designacao = Input.readLine();
        System.out.print("Família: ");
        String familia = Input.readLine();

        return new Categoria(designacao, familia);
    }

    public String toString() {
        return "Categoria: " + designacao + " (Família: " + familia + ")";
    }
}
