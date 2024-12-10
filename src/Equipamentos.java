package src;
import java.util.ArrayList;
import java.util.List;
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

    public void setQuantidadeStock() {
        this.quantidadeStock = Integer.parseInt(Validator.validateInput("Quantidade de Stock"));
    }

    private static void addFornecedor(List<Fornecedor> fornecedores) {
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

    private static void addCategoria(List<Categoria> categorias) {
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

    private static void addinforEquipamento(String type){
        List<Equipamentos> equipamentos = Sistema.getInstance().getEquipamentos();
        listarEquipamentos(equipamentos);
        int escolha = select(type);
        if (escolha == 0) {
            return;
        }
        else {
            if (type.equals("fornecedor")) {
                addFornecedor(equipamentos.get(escolha - 1).getFornecedores());
                System.out.println("Fornecedor adicionado com sucesso!");
            }
            else if (type.equals("categoria")) {
                addCategoria(equipamentos.get(escolha - 1).getCategorias());
                System.out.println("Categoria adicionada com sucesso!");
            }
            Main.pressEnterKey();
        }

    }

    private static int select(String type){
        if (type.equals("stock")){
            System.out.print("Selecione o equipamento a atualizar o"+ type + "(ou 0 para cancelar): ");
        }
        else{
            System.out.print("Selecione "+ type +" a adicionar o fornecedor (ou 0 para cancelar): ");
        }
        int escolha = Input.readInt();
        if (escolha == 0) {
            System.out.println("A voltar ao menu principal.");
            Main.pressEnterKey();
            return escolha;
        }
        else {
            return escolha;
        }
    }

    public static Equipamentos addEquipamentos(){
        Main.clearConsole();
        System.out.print("Adicionar equipamento: \n");
        String marca = Validator.validateInput("Marca");
        String modelo = Validator.validateInput("Modelo");
        String codigoInterno = Validator.validateInput("Código Interno");
        Serie serie = Serie.adicionarSerie();
        Versao versao = Versao.adicionarVersao();
        float voltagem = Float.parseFloat(Validator.validateInput("Voltagem"));
        int quantidadeStock = Integer.parseInt(Validator.validateInput("Quantidade de Stock"));
        float precoVenda = Float.parseFloat(Validator.validateInput("Preço de Venda"));
        String observacoes = Validator.validateInput("Observações");
        System.out.print("OEM (Sim/Não): ");
        boolean isOEM = Input.readLine().equalsIgnoreCase("sim");
        Equipamentos equipamento = new Equipamentos(marca, modelo, codigoInterno, serie, versao, voltagem, quantidadeStock, precoVenda, observacoes, isOEM);
        addFornecedor(Sistema.getInstance().getFornecedores());
        addCategoria(Sistema.getInstance().getCategorias());
        return equipamento;
    }

    private static void listarEquipamentos(List<Equipamentos> equipamentos) {
        System.out.println("Equipamentos:\n");
        for (int i = 0; i < equipamentos.size(); i++) {
            System.out.println((i + 1) + ". \n"+ "Marca: " + equipamentos.get(i).getMarca() + "Modelo: " + equipamentos.get(i).getModelo() + 
            "\nCódigo Interno: " + equipamentos.get(i).getCodigoInterno() + "Preço de Venda: " + equipamentos.get(i).getPrecoVenda() + 
            "\nOEM: " + (equipamentos.get(i).isOEM() ? "Sim" : "Não") + "Quantidade de Stock: " + equipamentos.get(i).getQuantidadeStock() + 
            "\n");
        }
    }

    public static void atualizarStock() {
        Main.clearConsole();
        List<Equipamentos> equipamentos = Sistema.getInstance().getEquipamentos();
        listarEquipamentos(equipamentos);
        int escolha = select("stock");
        if (escolha == 0) {
            return;
        }
        else {
            equipamentos.get(escolha - 1).setQuantidadeStock();
            System.out.println("Stock atualizado com sucesso!");
            Main.pressEnterKey();
        }
    }

    public static void equipamentosLoop() {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|----------------------------------------|");
            System.out.println("|1. Adicionar equipamento                |");
            System.out.println("|2. Adicionar fornecedor a equipamento   |");
            System.out.println("|3. Adicionar categoria a equipamento    |");
            System.out.println("|4. Atualizar Stock                      |");
            System.out.println("|5. Sair                                 |");
            System.out.println("|----------------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    Equipamentos equip = addEquipamentos();
                    if (equip != null) {
                        Sistema.getInstance().adicionarEquipamento(equip);
                        Main.pressEnterKey();
                    }
                    else{
                        System.out.println("Equipamento não adicionado.");
                    }
                    break;
                case "2":
                    addinforEquipamento("fornecedor");
                    break;
                case "3":
                    addinforEquipamento("categoria");
                    break;
                case "4":
                    atualizarStock();
                    break;
                case "5":
                    System.out.println("A voltar ao menu principal.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    Main.pressEnterKey();
                    break;
            }
        }
    }

    public String toString() {
        return "Equipamento: " + marca + " " + modelo + ", Código: " + codigoInterno + ", Preço: " + precoVenda +
               ", OEM: " + (isOEM ? "Sim" : "Não") + ", Quantidade: " + quantidadeStock;
    }
}
