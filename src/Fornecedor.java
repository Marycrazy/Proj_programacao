package src;
import java.io.Serializable;
import java.util.List;

public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String morada;
    private String telefone;

    public Fornecedor(String nome, String morada, String contacto) {
        this.nome = nome;
        this.morada = morada;
        this.telefone = contacto;
    }

    public String getNome() {
        return nome;
    }
    public String getMorada() {
        return morada;
    }
    public String getContacto() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setMorada(String morada) {
        this.morada = morada;
    }
    public void setContacto(String contacto) {
        this.telefone = contacto;
    }

    public static void listarfornecedores(List<Fornecedor> fornecedor) {
        System.out.println("\nFornecedores:");
        for (int i = 0; i < fornecedor.size(); i++) {
            System.out.println((i + 1) + ". \n Nome: " + fornecedor.get(i).getNome() + "\n Morada: " + fornecedor.get(i).getMorada() + "\n Contacto: " + fornecedor.get(i).getContacto() + "\n");
        }
    }

    //adicionar fornecedor
    public static Fornecedor adicionarFornecedor() {
        Main.clearConsole();
        System.out.print("Adicionar fornecedor: \n");
        String nome = Validator.validateInput("Name");
        String morada = Validator.validateInput("Morada");
        String telefone = Validator.validateInput("Telefone");

        return new Fornecedor(nome, morada, telefone);
    }

    public String toString() {
        return "Fornecedor: " + nome + ", Morada: " + morada + ", Contacto: " + telefone;
    }
}
