package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto {
    private int id_prod;
    private int id_vendedor;
    private String nome_prod;
    private String descricao;
    private float preco;
    private int capacidadeKwhPorUnidade;
    private int estoqueUnidadesDisponiveis;
    private static List<Produto> produtos = new ArrayList<>();

    public int getId_prod() {
        return id_prod;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getCapacidadeKwhPorUnidade() {
        return capacidadeKwhPorUnidade;
    }

    public void setCapacidadeKwhPorUnidade(int capacidadeKwhPorUnidade) {
        this.capacidadeKwhPorUnidade = capacidadeKwhPorUnidade;
    }

    public int getEstoqueUnidadesDisponiveis() {
        return estoqueUnidadesDisponiveis;
    }

    public void setEstoqueUnidadesDisponiveis(int estoqueUnidadesDisponiveis) {
        this.estoqueUnidadesDisponiveis = estoqueUnidadesDisponiveis;
    }

    public static List<Produto> getProdutosList() {
        return produtos;
    }

    public Produto(int id_prod, int id_vendedor, String nome_prod, String descricao, float preco,
            int capacidadeKwhPorUnidade, int estoqueUnidadesDisponiveis) {
        this.id_prod = id_prod;
        this.id_vendedor = id_vendedor;
        this.nome_prod = nome_prod;
        this.descricao = descricao;
        this.preco = preco;
        this.capacidadeKwhPorUnidade = capacidadeKwhPorUnidade;
        this.estoqueUnidadesDisponiveis = estoqueUnidadesDisponiveis;
    }

    public void exibirProduto() {
        System.out.println("ID Produto: " + id_prod);
        System.out.println("ID Vendedor: " + id_vendedor);
        System.out.println("Nome: " + nome_prod);
        System.out.println("Descrição: " + descricao);
        System.out.println("Preço (por unidade): R$ " + String.format("%.2f", preco));
        System.out.println("Cota (capacidade por unidade): " + capacidadeKwhPorUnidade + " kWh");
        System.out.println("Estoque Disponível: " + estoqueUnidadesDisponiveis + " unidades");
    }

    public void adicionarEstoque(int unidades) {
        if (unidades > 0) {
            this.estoqueUnidadesDisponiveis += unidades;
            System.out.println("Adicionado " + unidades + " unidades ao estoque. Novo estoque: "
                    + this.estoqueUnidadesDisponiveis);
        } else {
            System.out.println("Quantidade inválida para adição.");
        }
    }

    public void removerEstoque(int unidades) {
        if (unidades > 0 && this.estoqueUnidadesDisponiveis >= unidades) {
            this.estoqueUnidadesDisponiveis -= unidades;
            System.out.println(
                    "Removido " + unidades + " unidades do estoque. Novo estoque: " + this.estoqueUnidadesDisponiveis);
        } else {
            System.out.println("Quantidade inválida para remoção ou estoque insuficiente. Estoque atual: "
                    + this.estoqueUnidadesDisponiveis);
        }
    }

    public static void cadastroProduto(Scanner scanner, Usuario usuarioLogado) {
        if (usuarioLogado == null || usuarioLogado.getTipo_usuario() != Usuario.tipoUsuario.VENDEDOR) {
            System.out.println("Apenas vendedores podem cadastrar produtos.");
            return;
        }
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço (por unidade): ");
        float preco = scanner.nextFloat();
        System.out.print("Cota (capacidade em kWh por unidade): ");
        int capacidadeKwh = scanner.nextInt();
        System.out.print("Estoque Disponível (unidades): ");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        int id_prod = produtos.size() + 1;
        int id_vendedor = usuarioLogado.getId();

        Produto produto = new Produto(id_prod, id_vendedor, nome, descricao, preco, capacidadeKwh, estoque);
        produtos.add(produto);

        ((Vendedor) usuarioLogado).adicionarProduto(produto);
        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("ID do Produto: " + id_prod);
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Preço: R$ " + String.format("%.2f", preco));
        System.out.println("Cota (capacidade por unidade): " + capacidadeKwh + " kWh");
        System.out.println("Estoque Disponível: " + estoque + " unidades");
        System.out.println("ID do Vendedor: " + id_vendedor);
        System.out.println("------------------------------");
        System.out.println("Produto adicionado à lista de produtos do vendedor.");
        System.out.println("Produto adicionado à lista de produtos disponíveis.");
    }

    public static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("Listando Produtos:");
            for (Produto produto : produtos) {
                produto.exibirProduto();
                System.out.println("------------------------------");
            }
        }
    }
}