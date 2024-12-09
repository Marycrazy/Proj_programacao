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
    private double voltagem;
    private int quantidadeStock;
    private double precoVenda;
    private String observacoes;
    private boolean isOEM;
    private List<Fornecedor> fornecedores; // Até 6 fornecedores
    private List<Categoria> categorias; // Até 4 categorias

    public Equipamentos(String marca, String modelo, String codigoInterno, Serie serie, Versao versao, double voltagem, int quantidadeStock, double precoVenda, String observacoes, boolean isOEM) {
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setVersao(Versao versao) {
        this.versao = versao;
    }

    public void setVoltagem(double voltagem) {
        this.voltagem = voltagem;
    }

    public void setQuantidadeStock(int quantidadeStock) {
        this.quantidadeStock = quantidadeStock;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setOEM(boolean OEM) {
        isOEM = OEM;
    }

    private static final Map<String, Function<Equipamentos, String>> getters = Map.of(
        "marca", Equipamentos::getMarca,
        "modelo", Equipamentos::getModelo,
        "codigoInterno", Equipamentos::getCodigoInterno,
        "serie", e -> e.getSerie() != null ? e.getSerie().toString() : null,
        "versao", e -> e.getVersao() != null ? e.getVersao().toString() : null,
        "voltagem", e -> String.valueOf(e.getVoltagem()),
        "quantidadeStock", e -> String.valueOf(e.getQuantidadeStock()),
        "precoVenda", e -> String.valueOf(e.getPrecoVenda()),
        "observacoes", Equipamentos::getObservacoes,
        "isOEM", e -> String.valueOf(e.isOEM())
    );


    public boolean adicionarFornecedor(Fornecedor fornecedor) {
        if (fornecedores.size() >= 6) {
            System.out.println("Já existem 6 fornecedores associados a este equipamento.");
            return false;
        }
        fornecedores.add(fornecedor);
        return true;
    }

    public boolean adicionarCategoria(Categoria categoria) {
        if (categorias.size() >= 4) {
            System.out.println("Já existem 4 categorias associadas a este equipamento.");
            return false;
        }
        categorias.add(categoria);
        return true;

    }

    public String toString() {
        return "Equipamento: " + marca + " " + modelo + ", Código: " + codigoInterno + ", Preço: " + precoVenda +
               ", OEM: " + (isOEM ? "Sim" : "Não") + ", Quantidade: " + quantidadeStock;
    }
}
