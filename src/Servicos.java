package src;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
public class Servicos implements Serializable {
    private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private int codigo;
    private Date data;
    private String descricao;
    private EstadoServico estado;
    private int tempoProcessamento;
    private double valorTotal;
    private Tecnicos tecnicoResponsavel;
    private List<Equipamentos> equipamento;
    private List<Integer> quantidades;
    private List<SubServico> subServicos;

    public Servicos(Cliente cliente, Date data, String descricao, double valorTotal) {
        this.codigo = Sistema.getInstance().getServicos().size() + 1;
        this.cliente = cliente;
        this.data = data;
        this.descricao = descricao;
        this.estado = EstadoServico.SUBMETIDO;
        this.valorTotal = valorTotal;
        this.equipamento = new ArrayList<>();
        this.quantidades = new ArrayList<>();
        this.subServicos = new ArrayList<>();
    }
    public Cliente getCliente() {
        return cliente;
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

    public int getTempoProcessamento() {
        return tempoProcessamento;
    }

    public void setTempoProcessamento(int tempoProcessamento) {
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

    public static void listarServicos(List<Servicos> submetidos, boolean mostrar) {
        if (submetidos == null) {
            System.out.println("Nenhum serviço submetido encontrado.");
            return;
        }
        else {
            for (int i = 0; i < submetidos.size(); i++) {
                System.out.println((i + 1) + ".");
                System.out.println("Cliente: " + submetidos.get(i).getCliente().getNome());
                System.out.println("Data: " + submetidos.get(i).getData());
                System.out.println("Descrição: " + submetidos.get(i).getDescricao());
                System.out.println("Estado: " + submetidos.get(i).getEstado());
                for (int j = 0; j < submetidos.get(i).getEquipamento().size(); j++) {
                    System.out.println("\nEquipamento:");
                    Equipamentos.listarEquipamentos(submetidos.get(i).getEquipamento().get(j));
                }
                System.out.println("Valor total: " + submetidos.get(i).getValorTotal());
                if(mostrar){
                    System.out.println("Técnico responsável: " + submetidos.get(i).getTecnicoResponsavel().getNome());
                    System.out.println("Tempo de processamento: " + formatarTempo(submetidos.get(i).getTempoProcessamento()));
                    System.out.println("Subserviços:");
                    SubServico.listarSubServico(submetidos.get(i).getSubServicos());
                }
            }
        }
    }

    public static int quantidadeEquipamentoDisponivel(Equipamentos equipamento) {
        int quantidade;
        boolean check = true;
        do {
            quantidade = Integer.parseInt(Validator.validateInput("Quantidade"));
            if (quantidade > equipamento.getQuantidadeStock() || quantidade <= 0) {
                System.out.println("Quantidade indisponível. Por favor tente novamente.");
                continue;
            }
            else {
                check = false;
            }
            } while (check);
        return quantidade;
    }

    public static String formatarTempo(int minutos) {
        if (minutos < 60) {
            return String.format("%d minutos", minutos);
        } else if (minutos < 1440) { // menos de 24 horas
            int horas = minutos / 60;
            int mins = minutos % 60;
            return String.format("%dh%02dm", horas, mins);
        } else if (minutos < 10080) { // menos de 1 semana
            int dias = minutos / 1440;
            int horas = (minutos % 1440) / 60;
            return String.format("%d dias e %d horas", dias, horas);
        } else {
            int semanas = minutos / 10080;
            int dias = (minutos % 10080) / 1440;
            return String.format("%d semanas e %d dias", semanas, dias);
        }
    }

    public static int tempoGasto() {
        int semanas = (Integer.parseInt(Validator.validateInput("Semanas")))* 7 * 24 * 60 ;
        int dias = (Integer.parseInt(Validator.validateInput("Dias")))* 24 * 60;
        int horas = (Integer.parseInt(Validator.validateInput("Horas")))* 60;
        int minutos = Integer.parseInt(Validator.validateInput("Minutos"));
        int tempoTotal = semanas + dias + horas + minutos;
        return tempoTotal;
    }
}
