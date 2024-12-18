package src;
import java.io.Serializable;
import java.util.List;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
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
        Main.clearConsole();
        System.out.print("Adicionar categoria: \n");
        System.out.print("Designação: ");
        String designacao = Input.readLine();
        System.out.print("Família: ");
        String familia = Input.readLine();

        return new Categoria(designacao, familia);
    }

    public static void listarCategorias(List<Categoria> categoria, boolean mostrar) {
        System.out.println("Categorias:");
        for (int i = 0; i < categoria.size(); i++) {
            if (mostrar) {
                System.out.println((i + 1) + ". \n Designação: " + categoria.get(i).getDesignacao() + "\n Família: " + categoria.get(i).getFamilia() + "\n");
            } else {
                System.out.println("Designação: " + categoria.get(i).getDesignacao() + "\nFamília: " + categoria.get(i).getFamilia() + "\n");
            }
        }
    }

}
