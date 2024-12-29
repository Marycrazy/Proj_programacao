package src;
import java.sql.Date;
import java.util.List;
public class Servicos {
    private int codigo;
    private Date data;
    private String descricao;
    private String estado;
    private double tempoProcessamento;
    private double valorTotal;
    private Tecnicos tecnicoResponsavel;
    private List<Equipamentos> equipamento;
    private List<Integer> quantidades;
    private List<SubServico> subServicos;
}
