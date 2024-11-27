package src;
import java.util.List;

public class Equipamentos {
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
    private List<Fornecedor> fornecedores;
    private List<Categoria> categorias;
}
