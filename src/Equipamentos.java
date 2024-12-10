package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.io.Serializable;

public class Equipamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    private String marca;
    private String modelo;
    private String codigoInterno;
    private Serie serie;
    private Versao versao;
    private float voltagem;
    private int quantidadeStock;
    private float precoVenda;
    private String observacoes;
    private boolean isOEM;
    private List<Fornecedor> fornecedores; // Até 6 fornecedores
    private List<Categoria> categorias; // Até 4 categorias

    public Equipamentos(String marca, String modelo, String codigoInterno, Serie serie, Versao versao, float voltagem, int quantidadeStock, float precoVenda, String observacoes, boolean isOEM) {
        this.marca = marca;
        this.modelo = modelo;
        this.codigoInterno = codigoInterno;
        this.serie = serie;
        this.versao = versao;
        this.voltagem = voltagem;
        this.quantidadeStock = quantidadeStock;
        this.precoVenda = precoVenda;
        this.observacoes = observacoes;
        this.isOEM = isOEM;
        this.fornecedores = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public Serie getSerie() {
        return serie;
    }

    public Versao getVersao() {
        return versao;
    }

    public double getVoltagem() {
        return voltagem;
    }

    public int getQuantidadeStock() {
        return quantidadeStock;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public boolean isOEM() {
        return isOEM;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setQuantidadeStock(int quantidadeStock) {
        this.quantidadeStock = quantidadeStock;
    }

    private void adicionarFornecedor() {
        if (fornecedores.size() >= 6) {
            System.out.println("Já atingiu o limite de fornecedores.");
            return;
        }
        else{
            List<Fornecedor> forne = Sistema.getInstance().getFornecedores();
            Fornecedor.listarfornecedores(forne);
            boolean running = true;
            do {
                System.out.print("Selecione o fornecedor a aprovar (ou 0 para cancelar): ");
                int escolha = Input.readInt();
                if (escolha==0 && fornecedores.size() > 0) {
                    System.out.println("A voltar ao menu principal.");
                    running = false;
                }
                else {
                    fornecedores.add(forne.get(escolha - 1));
                    System.out.println("Fornecedor " + forne.get(escolha - 1).getNome() + " Adicionado");
                    running = false;
                }
            } while (running);
        }
    }
    private void adicionarCategoria() {
        if (categorias.size() >= 4) {
            System.out.println("Já atingiu o limite de categorias.");
            return;
        }
        else{
            List<Categoria> cat = Sistema.getInstance().getCategorias();
            Categoria.listarCategorias(cat);
            boolean running = true;
            do {
                System.out.print("Selecione o fornecedor a aprovar (ou 0 para cancelar): ");
                int escolha = Input.readInt();
                if (escolha==0 && categorias.size() > 0) {
                    System.out.println("A voltar ao menu principal.");
                    running = false;
                }
                else {
                    categorias.add(cat.get(escolha - 1));
                    System.out.println("Categoria da familia " + cat.get(escolha - 1).getFamilia() + " Adicionado");
                    running = false;
                }
            } while (running);
        }

    }

    public static Equipamentos adEquipamentos(){
        System.out.print("Adicionar equipamento: \n");
        String marca = Validator.validateInput("Marca");
        String modelo = Validator.validateInput("Modelo");
        String codigoInterno = Validator.validateInput("Código Interno");
        Serie serie = Serie.adicionarSerie();
        Versao versao = Versao.adicionarVersao();
        float voltagem = Float.parseFloat(Validator.validateInput("Voltagem"));
        int quantidadeStock = Integer.parseInt(Validator.validateInput("Quantidade de Stock"));
        float precoVenda = Float.parseFloat(Validator.validateInput("Preço de Venda"));
        System.out.print("Observações: ");
        String observacoes = Validator.validateInput("Observações");
        System.out.print("OEM (Sim/Não): ");
        boolean isOEM = Input.readLine().equalsIgnoreCase("sim");
        Equipamentos equipamento = new Equipamentos(marca, modelo, codigoInterno, serie, versao, voltagem, quantidadeStock, precoVenda, observacoes, isOEM);
        equipamento.adicionarFornecedor();
        equipamento.adicionarCategoria();
        return equipamento;
    }

    public String toString() {
        return "Equipamento: " + marca + " " + modelo + ", Código: " + codigoInterno + ", Preço: " + precoVenda +
               ", OEM: " + (isOEM ? "Sim" : "Não") + ", Quantidade: " + quantidadeStock;
    }
}
