package app;

import entities.Bairro;
import entities.Cidade;
import entities.Comprador;
import entities.Logradouro;
import entities.Produto;
import entities.Uf;
import entities.Usuario;
import entities.Vendedor;

import java.util.List;

public class MockData {

  public static void loadMockData() {
    // --- 1. Criar objetos de Endereço para uso comum ---
    Logradouro logradouroMock = new Logradouro("01001-000", "Rua Mockington");
    Bairro bairroMock = new Bairro("Centro Mock");
    Cidade cidadeMock = new Cidade("Cidade Mock");
    Uf ufMock = new Uf("SP");

    // --- 2. Criar e Adicionar Usuários (Comprador e Vendedor) ---

    // Vendedor Mock
    Vendedor vendedorMock = new Vendedor(
        "Vendedor Felpera",
        "vendedor.felpera@email.com",
        "mock123",
        Usuario.tipoUsuario.VENDEDOR,
        "01/01/1980",
        "11.222.333/0001-44",
        "11987654321",
        logradouroMock, logradouroMock, bairroMock, cidadeMock, ufMock);
    Usuario.adicionarListagemUsuarios(vendedorMock);
    System.out.println("Vendedor Mock cadastrado: " + vendedorMock.getNome());

    // Comprador Mock
    Comprador compradorMock = new Comprador(
        "Comprador Yuri",
        "comprador.yuri@email.com",
        "mock123",
        Usuario.tipoUsuario.COMPRADOR,
        "15/05/1990",
        "123.456.789-00", // CPF
        "11912345678",
        logradouroMock, logradouroMock, bairroMock, cidadeMock, ufMock);
    compradorMock.setMediaConsumoMensal(250.0f);
    Usuario.adicionarListagemUsuarios(compradorMock);
    System.out.println("Comprador Mock cadastrado: " + compradorMock.getNome() + " (Consumo: 250 kWh)");

    compradorMock.getCarteira().adicionarSaldo(5000.0f);
    System.out.println("Saldo de R$5000,00 adicionado à carteira do Comprador Mock.");

    // --- 3. Criar e Adicionar Produtos (Cotas de Energia) ---

    List<Produto> produtosGlobais = Produto.getProdutosList();

    int idProd1 = produtosGlobais.size() + 1;
    Produto prod1 = new Produto(
        idProd1,
        vendedorMock.getId(), // ID do vendedor que está cadastrando o produto
        "Energia Essencial",
        "Cota basica de 50 kWh",
        25.0f, // Preço por unidade
        50, // Capacidade em kWh por unidade
        20 // Estoque de unidades disponíveis
    );
    produtosGlobais.add(prod1);
    vendedorMock.adicionarProduto(prod1);
    System.out.println("Produto Mock 1 cadastrado: " + prod1.getNome_prod());

    // Produto 2: Energia Média
    int idProd2 = produtosGlobais.size() + 1;
    Produto prod2 = new Produto(
        idProd2,
        vendedorMock.getId(),
        "Energia Media",
        "Cota ideal para consumo residencial",
        50.0f,
        100,
        15);
    produtosGlobais.add(prod2);
    vendedorMock.adicionarProduto(prod2);
    System.out.println("Produto Mock 2 cadastrado: " + prod2.getNome_prod());

    // Produto 3: Super Cota
    int idProd3 = produtosGlobais.size() + 1;
    Produto prod3 = new Produto(
        idProd3,
        vendedorMock.getId(),
        "Super Cota",
        "Cota para grandes consumidores",
        140.0f,
        250,
        10);
    produtosGlobais.add(prod3);
    vendedorMock.adicionarProduto(prod3);
    System.out.println("Produto Mock 3 cadastrado: " + prod3.getNome_prod());

    System.out.println("===== DADOS MOCKADOS CARREGADOS COM SUCESSO =====");
  }
}
