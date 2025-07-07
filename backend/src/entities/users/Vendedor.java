package entities.users;

import java.util.ArrayList;
import java.util.List;

import entities.address.Bairro;
import entities.address.Cidade;
import entities.address.Logradouro;
import entities.address.Uf;
import entities.checkout.Pedido;
import entities.product.Produto;
import entities.users.Usuario.tipoUsuario;

public class Vendedor extends Usuario {
    private List<Produto> produtos;
    private List<Pedido> vendas;
    private List<Avaliacoes> avaliacoesRecebidas;

    public Vendedor(String nome, String email, String senha, tipoUsuario tipo_usuario, String data_nasc,
            String cpf_cnpj, String telefone, Logradouro cep, Logradouro logradouro, Bairro bairro, Cidade cidade,
            Uf uf) {
        super(nome, email, senha, tipo_usuario, data_nasc, cpf_cnpj, telefone, cep, logradouro, bairro, cidade, uf);
        this.vendas = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.avaliacoesRecebidas = new ArrayList<>();
    }

    public List<Pedido> getVendas() {
        return vendas;
    }

    public List<Avaliacoes> getAvaliacoesRecebidas() {
        return avaliacoesRecebidas;
    }

    public void exibirAvaliacoesRecebidas() {
        if (avaliacoesRecebidas.isEmpty()) {
            System.out.println("Você ainda não recebeu nenhuma avaliação.");
        } else {
            System.out.println("\n--- Minhas Avaliações Recebidas ---");
            for (Avaliacoes avaliacao : avaliacoesRecebidas) {
                avaliacao.exibirAvaliacao();
            }
            System.out.println("-----------------------------------");
        }
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto " + produto.getNome_prod() + " adicionado com sucesso!");
    }

    public void removerProduto(int idProduto) {
        for (Produto produto : produtos) {
            if (produto.getId_prod() == idProduto) {
                produtos.remove(produto);
                System.out.println("Produto " + produto.getNome_prod() + " removido com sucesso!");
                return;
            }
        }
        System.out.println("Produto com ID " + idProduto + " não encontrado.");
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado pelo vendedor.");
        } else {
            System.out.println("Produtos do Vendedor ID " + getId() + ":");
            for (Produto produto : produtos) {
                produto.exibirProduto();
                System.out.println("------------------------------");
            }
        }
    }

    public static void exibirHistoricoVendas(Usuario usuarioLogado) {
        if (!(usuarioLogado instanceof Vendedor)) {
            System.out.println("Apenas vendedores podem ver o histórico de vendas.");
            return;
        }
        Vendedor vendedor = (Vendedor) usuarioLogado;
        List<Pedido> vendas = vendedor.getVendas();

        if (vendas == null || vendas.isEmpty()) {
            System.out.println("Você ainda não realizou nenhuma venda.");
            return;
        }

        System.out.println("Histórico de Vendas:");
        for (Pedido pedido : vendas) {
            pedido.exibirPedido();
        }
    }
}