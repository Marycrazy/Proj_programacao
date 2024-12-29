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
        // Verifica a lista específica do equipamento
        if (fornecedores.size() >= 6) {
            System.out.println("Já atingiu o limite de fornecedores para este equipamento.");
            return;
        }
        List<Fornecedor> forne = Sistema.getInstance().getFornecedores();
        Fornecedor.listarfornecedores(forne);
        boolean running = true;
        do {
            System.out.print("Selecione o fornecedor a associar (ou 0 para cancelar): ");
            int escolha = Input.readInt();
            if (escolha == 0) {
                System.out.println("A voltar ao menu principal.");
                Input.clearBuffer();
                running = false;
            } else if (escolha < 1 || escolha > forne.size()) {
                System.out.println("Opção inválida. Tente novamente.");
            } else {
                handleFornecedorSelection(fornecedores, forne, escolha);
                Input.clearBuffer();
                running = false;
            }
        } while (running);
        Main.pressEnterKey();
    }

    private static void handleFornecedorSelection(List<Fornecedor> fornecedores, List<Fornecedor> forne, int escolha) {
        Fornecedor fornecedorSelecionado = forne.get(escolha - 1);
        if (!fornecedores.contains(fornecedorSelecionado)) {
            fornecedores.add(fornecedorSelecionado);
            System.out.println("Fornecedor " + fornecedorSelecionado.getNome() + " associado com sucesso!");

        } else {
            System.out.println("Este fornecedor já está associado ao equipamento.");

        }
    }

    private static void addCategoria(List<Categoria> categorias) {
        // Verifica a lista específica do equipamento
        if (categorias.size() >= 4) {
            System.out.println("Já atingiu o limite de categorias para este equipamento.");
            Input.clearBuffer();
            Main.pressEnterKey();
            return;
        }
        List<Categoria> cat = Sistema.getInstance().getCategorias();
        Categoria.listarCategorias(cat, true);
        boolean running = true;
        do {
            System.out.print("Selecione a categoria a associar (ou 0 para cancelar): ");
            int escolha = Input.readInt();
            if (escolha == 0) {
                System.out.println("A voltar ao menu principal.");
                Input.clearBuffer();
                running = false;
            } else if (escolha < 1 || escolha > cat.size()) {
                System.out.println("Opção inválida. Tente novamente.");
                Input.clearBuffer();
            } else {
                // Adiciona somente se não estiver na lista
                handleCategoriaSelection(categorias, cat, escolha);
                Input.clearBuffer();
                running = false;
            }
        } while (running);
        Main.pressEnterKey();
    }

    private static void handleCategoriaSelection(List<Categoria> categorias, List<Categoria> cat, int escolha) {
        Categoria categoriaSelecionada = cat.get(escolha - 1);
        if (!categorias.contains(categoriaSelecionada)) {
            categorias.add(categoriaSelecionada);
            System.out.println("Categoria " + categoriaSelecionada.getDesignacao() + " associada com sucesso!");
        } else {
            System.out.println("Esta categoria já está associada ao equipamento.");
        }
    }

    private static void addinforEquipamento(String type){
        List<Equipamentos> equipamentos = Sistema.getInstance().getEquipamentos();
        System.out.println("Equipamentos:\n");
        for (int i = 0; i < equipamentos.size(); i++) {
            listarEquipamentos(equipamentos.get(i), i);
        }
        int escolha = select(type);
        if (escolha == 0) {
            return;
        }
        else {
            if (type.equals("fornecedor")) {
                addFornecedor(equipamentos.get(escolha - 1).getFornecedores());
            }
            else if (type.equals("categoria")) {
                addCategoria(equipamentos.get(escolha - 1).getCategorias());
            }
        }

    }

    private static int select(String type){
        if (type.equals("stock")){
            System.out.print("Selecione o equipamento a atualizar o"+ type + "(ou 0 para cancelar): ");
        }
        else if (type.equals("fornecedor") || type.equals("categoria")){
            System.out.print("Selecione "+ type +" a adicionar ao equipamento (ou 0 para cancelar): ");
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

    private static Equipamentos addEquipamentos(){
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
        Equipamentos equipamento = new Equipamentos(marca, modelo, codigoInterno, serie, versao, voltagem,
                                             quantidadeStock, precoVenda, observacoes, isOEM);

        // Associar fornecedores ao equipamento
        System.out.println("\nAssociar Fornecedores:");
        addFornecedor(equipamento.getFornecedores());

        // Associar categorias ao equipamento
        System.out.println("\nAssociar Categorias:");
        addCategoria(equipamento.getCategorias());

        // Retornar o equipamento configurado
        return equipamento;
    }

    private static void listarEquipamentos(Equipamentos equipamentos, int i) {
        System.out.println((i + 1) + ".\n"+"Marca: " + equipamentos.getMarca() + " Modelo: " + equipamentos.getModelo() +
        "\nCódigo Interno: " + equipamentos.getCodigoInterno() + " Preço de Venda: " + equipamentos.getPrecoVenda() +
        "\nOEM: " + (equipamentos.isOEM() ? "Sim" : "Não") + " Quantidade de Stock: " + equipamentos.getQuantidadeStock() + "\n");
    }

    private static void atualizarStock() {
        Main.clearConsole();
        List<Equipamentos> equipamentos = Sistema.getInstance().getEquipamentos();
        System.out.println("Equipamentos:\n");
        for (int i = 0; i < equipamentos.size(); i++) {
            listarEquipamentos(equipamentos.get(i), i);
        }
        int escolha = select("stock");
        if (escolha == 0) {
            return;
        }
        else {
            Input.clearBuffer();
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
            System.out.println("|5. Listar Equipamentos                   |");
            System.out.println("|6. Sair                                 |");
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
                    listarEquipamentosLoop();
                    break;
                case "6":
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

    public static void listarEquipamentosLoop() {
        boolean running = true;
        while (running) {
            Main.clearConsole();
            System.out.println("|-----------------------------------------------|");
            System.out.println("|Listar Equipamentos                            |");
            System.out.println("|1. Listar equipamentos por designação          |");
            System.out.println("|2. Listar todos os equipamentos                |");
            System.out.println("|3. Listar que sejam OEM ou não                 |");
            System.out.println("|4. Listar equipamentos por stock               |");
            System.out.println("|5. Listar equipamentos por marca ou codigo     |");
            System.out.println("|6. Listar equipamentos por categoria           |");
            System.out.println("|7. Sair                                        |");
            System.out.println("|-----------------------------------------------|");
            System.out.print("Option: ");
            String option = Input.readLine();
            switch (option) {
                case "1":
                    System.out.print("Designação (Modelo) do equipamento:");
                    String designacao = Input.readLine();
                    List<Equipamentos> modelo = Sistema.getInstance().getEquipamentos().stream().filter(e -> e.getModelo().contains(designacao)).toList();
                    System.out.println("Equipamentos com o seguinte moedelo "+designacao+":\n");
                    for (int i = 0; i < modelo.size(); i++) {
                        listarEquipamentos(modelo.get(i), i);
                    }
                    Main.pressEnterKey();
                    break;
                case "2":
                    List<Equipamentos> equipamentos = Sistema.getInstance().getEquipamentos();
                    System.out.println("Equipamentos:\n");
                    for (int i = 0; i < equipamentos.size(); i++) {
                        listarEquipamentos(equipamentos.get(i), i);
                    }
                    Main.pressEnterKey();
                    break;
                case "3":
                    System.out.println("Listar equipamentos OEM ou não OEM (sim/não):");
                    String input = Input.readLine();
                    if (input.equalsIgnoreCase("sim")) {
                        List<Equipamentos> equipamentosOEM = Sistema.getInstance().getEquipamentos().stream().filter(e -> e.isOEM()).toList();
                        System.out.println("Equipamentos OEM:\n");
                        for (int i = 0; i < equipamentosOEM.size(); i++) {
                            listarEquipamentos(equipamentosOEM.get(i), i);
                        }
                    }
                    else{
                        List<Equipamentos> equipamentosOEM = Sistema.getInstance().getEquipamentos().stream().filter(e -> !e.isOEM()).toList();
                        System.out.println("Equipamentos não OEM:\n");
                        for (int i = 0; i < equipamentosOEM.size(); i++) {
                            listarEquipamentos(equipamentosOEM.get(i), i);
                        }
                    }
                    Main.pressEnterKey();
                    break;
                case "4":
                    System.out.print("Limite de Stock: ");
                    int limiteEstoque = Input.readInt();
                    List<Equipamentos> stockList = Sistema.getInstance().getEquipamentos().stream().filter(e -> e.getQuantidadeStock() <= limiteEstoque).toList();
                    System.out.println("Equipamentos com stock menor ou igual a "+limiteEstoque+":\n");
                    for (int i = 0; i < stockList.size(); i++) {
                        listarEquipamentos(stockList.get(i), i);
                    }
                    Input.clearBuffer();
                    Main.pressEnterKey();
                    break;
                case "5":
                    System.out.print("Marca ou Código Interno: ");
                    String marcaOuCodigo = Input.readLine();
                    List<Equipamentos> marcaOuCodigoList = Sistema.getInstance().getEquipamentos().stream().filter(e -> e.getMarca().contains(marcaOuCodigo) || e.getCodigoInterno().contains(marcaOuCodigo.toLowerCase())).toList();
                    System.out.println("Equipamentos com a marca ou código interno "+marcaOuCodigo+":\n");
                    for (int i = 0; i < marcaOuCodigoList.size(); i++) {
                        listarEquipamentos(marcaOuCodigoList.get(i), i);
                    }
                    Main.pressEnterKey();
                    break;
                case "6":
                    System.out.print("Designação da Categoria: ");
                    String categoria = Input.readLine();
                    List<Equipamentos> categoriaList = Sistema.getInstance().getEquipamentos().stream().filter(e -> e.getCategorias().stream().anyMatch(c -> c.getDesignacao().contains(categoria))).toList();
                    System.out.println("Equipamentos com a categoria "+categoria+":\n");
                    for (int i = 0; i < categoriaList.size(); i++) {
                        listarEquipamentos(categoriaList.get(i), i);
                        Categoria.listarCategorias(categoriaList.get(i).getCategorias(), false);
                    }
                    Main.pressEnterKey();
                    break;
                case "7":
                    System.out.println("A voltar ao menu principal.");
                    Main.pressEnterKey();
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
