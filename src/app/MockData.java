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
        Logradouro logradouroMock = new Logradouro("01001-000", "Rua Mockington");
        Bairro bairroMock = new Bairro("Centro Mock");
        Cidade cidadeMock = new Cidade("Cidade Mock");
        Uf ufMock = new Uf("SP");

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

        Comprador compradorMock = new Comprador(
                "Comprador Yuri",
                "comprador.yuri@email.com",
                "mock123",
                Usuario.tipoUsuario.COMPRADOR,
                "15/05/1990",
                "123.456.789-00",
                "11912345678",
                logradouroMock, logradouroMock, bairroMock, cidadeMock, ufMock);
        compradorMock.setMediaConsumoMensal(250.0f);
        Usuario.adicionarListagemUsuarios(compradorMock);
        System.out.println("Comprador Mock cadastrado: " + compradorMock.getNome() + " (Consumo: 250 kWh)");

        compradorMock.getCarteira().adicionarSaldo(5000.0f);
        System.out.println("Saldo de R$5000,00 adicionado à carteira do Comprador Mock.");

        List<Produto> produtosGlobais = Produto.getProdutosList();

        int idProd1 = produtosGlobais.size() + 1;
        Produto prod1 = new Produto(
                idProd1,
                vendedorMock.getId(),
                "Energia Essencial",
                "Cota basica de 50 kWh",
                25.0f,
                50,
                20);
        produtosGlobais.add(prod1);
        vendedorMock.adicionarProduto(prod1);
        System.out.println("Produto Mock 1 cadastrado: " + prod1.getNome_prod());

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
