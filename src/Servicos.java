package src;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
public class Servicos implements Serializable {
    private static final long serialVersionUID = 1L;
    private int codigo;
    private Date data;
    private String descricao;
    private EstadoServico estado;
    private double tempoProcessamento;
    private double valorTotal;
    private Tecnicos tecnicoResponsavel;
    private List<Equipamentos> equipamento;
    private List<Integer> quantidades;
    private List<SubServico> subServicos;

    public Servicos(Date data, String descricao, double valorTotal) {
        this.codigo = Sistema.getInstance().getServicos().size() + 1;
        this.data = data;
        this.descricao = descricao;
        this.estado = EstadoServico.SUBMETIDO;
        this.valorTotal = valorTotal;
        this.equipamento = new ArrayList<>();
        this.quantidades = new ArrayList<>();
        this.subServicos = new ArrayList<>();
    }
    public int getCodigo() {
        return codigo;
    }
    public Date getData() {
        return data;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public EstadoServico getEstado() {
        return estado;
    }
    public EstadoServico setEstado(EstadoServico estado) {
        this.estado = estado;
        return this.estado;
    }
    public double getTempoProcessamento() {
        return tempoProcessamento;
    }
    public void setTempoProcessamento(double tempoProcessamento) {
        this.tempoProcessamento = tempoProcessamento;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Tecnicos getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }
    public void setTecnicoResponsavel(Tecnicos tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }
    public List<Equipamentos> getEquipamento() {
        return equipamento;
    }

    public List<Integer> getQuantidades() {
        return quantidades;
    }
    public List<SubServico> getSubServicos() {
        return subServicos;
    }

    public void adicionarSubServico(SubServico subServico) {
        this.subServicos.add(subServico);
    }

    public void adicionarEquipamento(Equipamentos equipamentos, int quantidade) {
        this.equipamento.add(equipamentos);
        this.quantidades.add(quantidade);
    }

    public enum EstadoServico {
        SUBMETIDO,
        ACEITE,
        CONCLUIDO
    }

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (int i = 0; i < this.equipamento.size(); i++) {
            valorTotal += this.equipamento.get(i).getPrecoVenda() * this.quantidades.get(i);
        }
        return valorTotal;
    }
}
