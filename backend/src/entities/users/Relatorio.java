package entities.users;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import entities.product.Produto;

public class Relatorio {

    private static int contadorRelatorios = 0;
    private int idRelatorio;
    private LocalDateTime dataEmissao;
    private float consumoEnergia;
    private float taxaDistribuidora;
    private Usuario usuario;

    public Relatorio() {
        this.idRelatorio = contadorRelatorios++;
        this.dataEmissao = LocalDateTime.now();
        this.consumoEnergia = 0;
        this.taxaDistribuidora = 0;
    }

    public static void criarRelatorio(Scanner scanner, List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhuma cota solar disponível para exibir.");
            return;
        }

        System.out.print("Digite seu consumo mensal de energia (kWh): ");
        double consumoEnergia = scanner.nextDouble();

        System.out.print("Digite a tarifa de energia da sua distribuidora (R$/kWh): ");
        double taxaDistribuidora = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\n========= LISTA DE COTAS SOLARES =========");
        Produto.listarProdutos();

        System.out.print("Digite o ID da cota solar desejada: ");
        int idCotaEscolhida = scanner.nextInt();
        scanner.nextLine();

        Produto cotaSelecionada = null;
        for (Produto p : produtos) {
            if (p.getId_prod() == idCotaEscolhida) {
                cotaSelecionada = p;
                break;
            }
        }

        if (cotaSelecionada == null) {
            System.out.println("Erro: ID da cota solar não encontrado. Por favor, tente novamente.");
            return;
        }

        double energiaDaCota = cotaSelecionada.getCapacidadeKwhPorUnidade();
        double precoCota = cotaSelecionada.getPreco();
        double economiaMensal = Math.min(consumoEnergia, energiaDaCota) * taxaDistribuidora;
        double economiaAnual = economiaMensal * 12;

        System.out.println("\n========= RELATÓRIO DE ECONOMIA =========");
        System.out.println("Consumo mensal informado pelo usuário: " + consumoEnergia + " kWh");
        System.out.println("Tarifa de energia da distribuidora: R$ " + taxaDistribuidora + " por kWh");
        System.out.println("Cota solar adquirida: " + cotaSelecionada.getNome_prod() + " (ID: "
                + cotaSelecionada.getId_prod() + ")");
        System.out.println("Quantidade de energia da cota: " + energiaDaCota + " kWh");
        System.out.println("Valor da cota: R$ " + precoCota);
        System.out.println("Economia mensal estimada: R$ " + economiaMensal);
        System.out.println("Economia anual estimada: R$ " + economiaAnual);
        System.out.println("===========================================");
    }

    public void exibirRelatorio(int idRelatorio) {
        if (this.idRelatorio == idRelatorio) {
            imprimirDados();
        } else {
            System.out.println("Relatório não encontrado com o ID informado.");
        }
    }

    public void exibirRelatorio(LocalDateTime dataEmissao) {
        if (this.dataEmissao.toLocalDate().equals(dataEmissao.toLocalDate())) {
            imprimirDados();
        } else {
            System.out.println("Relatório não encontrado com a data informada.");
        }
    }

    private void imprimirDados() {
        System.out.println("\n===== RELATÓRIO DE CONSUMO DE ENERGIA =====");
        System.out.println("ID do Relatório: " + idRelatorio);
        System.out.println("Data de Emissão: " + this.dataEmissao.toLocalDate().toString());
        System.out.println("Consumo de Energia: " + consumoEnergia + " kWh");
        System.out.println("Taxa da Distribuidora: R$ " + taxaDistribuidora);
        System.out.println("===========================================\n");
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }
}
