package src;

import java.lang.invoke.StringConcatFactory;
import java.sql.Date;
import java.util.List;
import java.util.Map;
public class Servicos {
    private int codigo;
    private Date data;
    private String descricao;
    private String estado;
    private double tempoProcessamento;
    private double valorTotal;
    private List<Equipamentos> equipamento;
    private Map <Equipamentos, Integer> quantidades;
}
