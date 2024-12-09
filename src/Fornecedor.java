package src;
import java.io.Serializable;

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

    //adicionar fornecedor
    public static Fornecedor adicionarFornecedor() {

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
