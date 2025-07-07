package app;

import entities.checkout.Carrinho;
import entities.checkout.ItemCarrinho;
import entities.checkout.NotaFiscal;
import entities.checkout.Pedido;
import entities.product.Produto;
import entities.users.Avaliacoes;
import entities.users.Comprador;
import entities.users.Usuario;
import entities.users.Vendedor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	public static void menuLogadoComprador(Scanner scanner, Comprador compradorLogado) {
		while (true) {
			System.out.println("\n============================================================");
			System.out.println("            MENU DO COMPRADOR - BEM-VINDO(A)               ");
			System.out.println("            " + compradorLogado.getNome().toUpperCase());
			System.out.println("============================================================");
			System.out.println(" 1. Meus Dados");
			System.out.println(" 2. Meus Pedidos");
			System.out.println(" 3. Comprar Cotas de Energia");
			System.out.println(" 4. Relatório de Sugestão de Cotas");
			System.out.println(" 5. Minha Carteira Digital");
			System.out.println(" 6. Sair (Logout)");
			System.out.println("============================================================");
			System.out.print(">>> Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					compradorLogado.meusDados();
					break;
				case 2:
					compradorLogado.verPedidos();
					break;
				case 3:
					comprar(scanner, compradorLogado);
					break;
				case 4:
					gerarRelatorioSugestao(compradorLogado, entities.product.Produto.getProdutosList());
					break;
				case 5:
					menuCarteira(scanner, compradorLogado);
					break;
				case 6:
					System.out.println("\n--- Logout realizado com sucesso! Até logo! ---");
					return;
				default:
					System.out.println("\n! Opção inválida. Por favor, escolha um número entre 1 e 6.");
			}
			System.out.println("\n------------------------------------------------------------");
			System.out.println("Pressione ENTER para continuar..."); // Pausa para o usuário ler a saída
			scanner.nextLine();
		}
	}

	public static void menuCarteira(Scanner scanner, Usuario usuarioLogado) {
		while (true) {
			System.out.println("\n============================================================");
			System.out.println("             MENU CARTEIRA DIGITAL DE                     ");
			System.out.println("             " + usuarioLogado.getNome().toUpperCase());
			System.out.println("============================================================");
			System.out.println(" 1. Ver Saldo Atual");
			System.out.println(" 2. Sacar Saldo");

			if (usuarioLogado instanceof Comprador) {
				System.out.println(" 3. Adicionar Fundos");
				System.out.println(" 4. Voltar ao Menu Principal");
				System.out.println("============================================================");
				System.out.print(">>> Escolha uma opção: ");
			} else { // Vendedor
				System.out.println(" 3. Voltar ao Menu Principal");
				System.out.println("============================================================");
				System.out.print(">>> Escolha uma opção: ");
			}

			int opcaoCarteira = scanner.nextInt();
			scanner.nextLine();

			switch (opcaoCarteira) {
				case 1:
					System.out.println("\n--- Saldo da Carteira ---");
					System.out.println("Seu saldo atual é: R$ " + String.format("%.2f", usuarioLogado.getCarteira().getSaldo()));
					System.out.println("-------------------------");
					break;
				case 2:
					System.out.print("\n>>> Digite o valor que deseja sacar: R$ ");
					float valorSacar = scanner.nextFloat();
					scanner.nextLine();
					if (valorSacar > 0) {
						usuarioLogado.getCarteira().saque(valorSacar);
					} else {
						System.out.println("\n! Valor inválido. O saque deve ser um valor positivo.");
					}
					break;
				case 3:
					if (usuarioLogado instanceof Comprador) {
						System.out.print("\n>>> Digite o valor que deseja adicionar: R$ ");
						float valorAdicionar = scanner.nextFloat();
						scanner.nextLine();
						if (valorAdicionar > 0) {
							usuarioLogado.getCarteira().adicionarSaldo(valorAdicionar);
						} else {
							System.out.println("\n! Valor inválido. A adição deve ser um valor positivo.");
						}
					} else { // Vendedor quer voltar
						System.out.println("\nVoltando ao menu principal...");
						return;
					}
					break;
				case 4:
					if (usuarioLogado instanceof Comprador) {
						System.out.println("\nVoltando ao menu principal...");
						return;
					}
					System.out.println("\n! Opção inválida. Por favor, escolha um número entre 1 e 3.");
					break;
				default:
					System.out.println("\n! Opção inválida. Por favor, tente novamente.");
			}
			System.out.println("\n------------------------------------------------------------");
			System.out.println("Pressione ENTER para continuar...");
			scanner.nextLine();
		}
	}

	public static void menuLogadoVendedor(Scanner scanner, Vendedor vendedorLogado) {
		while (true) {
			System.out.println("\n============================================================");
			System.out.println("             MENU DO VENDEDOR - BEM-VINDO(A)               ");
			System.out.println("             " + vendedorLogado.getNome().toUpperCase());
			System.out.println("============================================================");
			System.out.println(" 1. Meus Dados");
			System.out.println(" 2. Gerenciar Produtos");
			System.out.println(" 3. Minhas Vendas");
			System.out.println(" 4. Minhas Avaliações");
			System.out.println(" 5. Minha Carteira Digital");
			System.out.println(" 6. Sair (Logout)");
			System.out.println("============================================================");
			System.out.print(">>> Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					vendedorLogado.meusDados();
					break;
				case 2:
					menuVender(scanner, vendedorLogado);
					break;
				case 3:
					entities.users.Vendedor.exibirHistoricoVendas(vendedorLogado);
					break;
				case 4:
					vendedorLogado.exibirAvaliacoesRecebidas();
					break;
				case 5:
					menuCarteira(scanner, vendedorLogado);
					break;
				case 6:
					System.out.println("\n--- Logout realizado com sucesso! Até logo! ---");
					return;
				default:
					System.out.println("\n! Opção inválida. Por favor, escolha um número entre 1 e 6.");
			}
			System.out.println("\n------------------------------------------------------------");
			System.out.println("Pressione ENTER para continuar...");
			scanner.nextLine();
		}
	}

	public static void menuVender(Scanner scanner, Vendedor vendedorLogado) {
		if (!(vendedorLogado instanceof Vendedor)) {
			System.out.println("! Apenas vendedores podem gerenciar produtos.");
			return;
		}
		while (true) {
			System.out.println("\n============================================================");
			System.out.println("            GERENCIAR PRODUTOS - VENDEDOR                 ");
			System.out.println("            " + vendedorLogado.getNome().toUpperCase());
			System.out.println("============================================================");
			System.out.println(" 1. Cadastrar Produto");
			System.out.println(" 2. Ver Meus Produtos");
			System.out.println(" 3. Histórico de Vendas");
			System.out.println(" 4. Voltar ao Menu Principal");
			System.out.println(" 5. Sair da Aplicação");
			System.out.println("============================================================");
			System.out.print(">>> Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					entities.product.Produto.cadastroProduto(scanner, vendedorLogado);
					break;
				case 2:
					vendedorLogado.listarProdutos(); // Chama o método do vendedor para listar seus produtos
					break;
				case 3:
					entities.users.Vendedor.exibirHistoricoVendas(vendedorLogado);
					break;
				case 4:
					System.out.println("\nVoltando ao menu principal...");
					return;
				case 5:
					System.out.println("\nEncerrando a aplicação. Até mais!");
					System.exit(0);
					break;
				default:
					System.out.println("\n! Opção inválida. Por favor, tente novamente.");
			}
			System.out.println("\n------------------------------------------------------------");
			System.out.println("Pressione ENTER para continuar...");
			scanner.nextLine();
		}
	}

	public static void comprar(Scanner scanner, Comprador compradorLogado) {
		Carrinho carrinho = new Carrinho();
		boolean continuarAdicionandoItens = true;

		while (continuarAdicionandoItens) {
			System.out.println("\n============================================================");
			System.out.println("             ADICIONAR ITENS AO CARRINHO                 ");
			System.out.println("             " + compradorLogado.getNome().toUpperCase());
			System.out.println("============================================================");
			System.out.println(
					"Saldo atual da sua carteira: R$ " + String.format("%.2f", compradorLogado.getCarteira().getSaldo()));
			System.out.println("\n--- Produtos Disponíveis para Compra ---");

			if (entities.product.Produto.getProdutosList().isEmpty()) {
				System.out.println("Nenhum produto disponível para compra no momento.");
				if (carrinho.estaVazio()) {
					System.out.println("\n! Não há produtos para adicionar. Compra cancelada.");
					return;
				} else {
					System.out.println("\nVocê já tem itens no carrinho. Prossiga para o pagamento.");
					break;
				}
			}

			for (Produto p : entities.product.Produto.getProdutosList()) {
				p.exibirProduto();
				System.out.println("------------------------------------------------------------");
			}

			System.out.print("\n>>> Digite o ID do produto que deseja adicionar (ou 0 para finalizar): ");
			int idProduto = scanner.nextInt();
			scanner.nextLine();

			if (idProduto == 0) {
				continuarAdicionandoItens = false;
				break;
			}

			Produto produtoSelecionado = null;
			for (Produto p : entities.product.Produto.getProdutosList()) {
				if (p.getId_prod() == idProduto) {
					produtoSelecionado = p;
					break;
				}
			}

			if (produtoSelecionado == null) {
				System.out.println("\n! Produto não encontrado. Por favor, digite um ID válido.");
				continue;
			}

			System.out.print(">>> Digite a quantidade de UNIDADES de '" + produtoSelecionado.getNome_prod() +
					"' (Cota: " + produtoSelecionado.getCapacidadeKwhPorUnidade() + " kWh cada, Estoque: " +
					produtoSelecionado.getEstoqueUnidadesDisponiveis() + " unidades): ");
			int quantidadeUnidades = scanner.nextInt();
			scanner.nextLine();

			if (quantidadeUnidades <= 0 || quantidadeUnidades > produtoSelecionado.getEstoqueUnidadesDisponiveis()) {
				System.out.println(
						"\n! Quantidade de unidades inválida ou superior ao estoque disponível para esta cota. Estoque atual: " +
								produtoSelecionado.getEstoqueUnidadesDisponiveis() + " unidades.");
				System.out.println("Por favor, digite uma quantidade válida.");
				continue;
			}

			carrinho.adicionarItem(produtoSelecionado, quantidadeUnidades);

			System.out.print("\n>>> Deseja adicionar mais itens ao carrinho? (S/N): ");
			String respostaContinuar = scanner.nextLine();
			if (!respostaContinuar.equalsIgnoreCase("S")) {
				continuarAdicionandoItens = false;
			}
			System.out.println("\n------------------------------------------------------------");
			System.out.println("Pressione ENTER para continuar..."); // Pausa após adicionar item
			scanner.nextLine();
		}

		// clearConsole(); // Descomente para limpar o console
		System.out.println("\n============================================================");
		System.out.println("             RESUMO DO SEU CARRINHO DE COMPRAS             ");
		System.out.println("============================================================");
		if (carrinho.estaVazio()) {
			System.out.println("\n! Seu carrinho está vazio. Compra cancelada.");
			System.out.println("Pressione ENTER para continuar...");
			scanner.nextLine();
			return;
		}
		carrinho.exibirCarrinho(); // Exibe o carrinho de forma organizada

		float valorTotalCarrinho = carrinho.getValorTotalCarrinho();
		System.out.println("\n--- VALOR TOTAL A PAGAR: R$ " + String.format("%.2f", valorTotalCarrinho) + " ---");

		Vendedor vendedorPrincipal = null;
		if (!carrinho.estaVazio()) {
			Produto primeiroProduto = carrinho.getItens().get(0).getProduto();
			for (Usuario u : entities.users.Usuario.getUsuariosList()) {
				if (u instanceof Vendedor && u.getId() == primeiroProduto.getId_vendedor()) {
					vendedorPrincipal = (Vendedor) u;
					break;
				}
			}
		}

		if (vendedorPrincipal == null) {
			System.out
					.println("\n! Erro interno: Vendedor principal não encontrado para os itens do carrinho. Compra cancelada.");
			System.out.println("Pressione ENTER para continuar...");
			scanner.nextLine();
			return;
		}

		Object[] resultadoPagamento = realizarPagamento(scanner, compradorLogado, vendedorPrincipal, valorTotalCarrinho);

		String formaPagamentoFinal = (String) resultadoPagamento[0];
		boolean pagamentoAprovado = (boolean) resultadoPagamento[1];
		int parcelas = (int) resultadoPagamento[2];

		if (!pagamentoAprovado) {
			System.out.println("\n! Pagamento não aprovado ou cancelado. Compra não realizada.");
			System.out.println("Pressione ENTER para continuar...");
			scanner.nextLine();
			return;
		}

		System.out.println("\n--- Processando Transação ---");
		if (formaPagamentoFinal.equals("CARTEIRA")) {
			compradorLogado.getCarteira().saque(valorTotalCarrinho);
			System.out
					.println("Saque de R$" + String.format("%.2f", valorTotalCarrinho) + " efetuado da carteira do comprador.");
		} else {
			System.out
					.println("Débito da carteira do comprador não aplicável para esta forma de pagamento (simulado externo).");
		}

		vendedorPrincipal.getCarteira().adicionarSaldo(valorTotalCarrinho);
		System.out.println(
				"Depósito de R$" + String.format("%.2f", valorTotalCarrinho) + " realizado na carteira do vendedor principal.");

		Pedido novoPedido = new Pedido(carrinho.getItens(), valorTotalCarrinho, formaPagamentoFinal, compradorLogado,
				vendedorPrincipal);
		if (formaPagamentoFinal.equals(Pedido.FormaPagamento.CARTAOCREDITO.getCodigo()) && parcelas > 1) {
			novoPedido.setParcelas(parcelas);
		}

		System.out.println("\n--- PEDIDO FINALIZADO COM SUCESSO! ---");

		NotaFiscal nf = criarNotaFiscalParaPedido(
				novoPedido.getIdPedido(),
				compradorLogado,
				vendedorPrincipal,
				carrinho.getItens().get(0).getProduto(), // Pega o primeiro produto para a NF (simplificação)
				valorTotalCarrinho,
				novoPedido);

		novoPedido.gerarNotaFiscal(nf);
		novoPedido.exibirPedido();

		for (ItemCarrinho item : carrinho.getItens()) {
			Produto produtoDoItem = item.getProduto();
			int quantidadeUnidadesCompradas = item.getQuantidadeUnidades();
			produtoDoItem.removerEstoque(quantidadeUnidadesCompradas);
		}

		System.out.print("\n>>> Deseja avaliar o vendedor agora? (S/N): ");
		String desejaAvaliar = scanner.nextLine();

		if (desejaAvaliar.equalsIgnoreCase("S")) {
			System.out.print("Sua Nota para o pedido (0 a 10): ");
			int nota = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Escreva sua avaliação (opcional): ");
			String descricao = scanner.nextLine();
			Avaliacoes avaliacao = new Avaliacoes(nota, descricao, compradorLogado, novoPedido);

			compradorLogado.getAvaliacoes().add(avaliacao);
			vendedorPrincipal.getAvaliacoesRecebidas().add(avaliacao);

			System.out.println("\nObrigado por sua avaliação! Ela foi registrada.");
			avaliacao.exibirAvaliacao();
		}
		System.out.println("\nPressione ENTER para continuar...");
		scanner.nextLine();
	}

	private static NotaFiscal criarNotaFiscalParaPedido(
			int idPedido,
			Comprador comprador,
			Vendedor vendedor,
			Produto produtoReferencia,
			float valorTotal,
			Pedido pedidoAssociado) {
		String enderecoFormatado = comprador.getLogradouro().toString() + ", " +
				comprador.getBairro().toString() + ", " +
				comprador.getCidade().toString() + "-" +
				comprador.getUf().toString() + " - CEP: " +
				comprador.getCep().getCep();

		return new NotaFiscal(
				idPedido,
				1000 + idPedido,
				comprador.getNome(),
				comprador.getCpf_cnpj(),
				enderecoFormatado,
				comprador.getEmail(),
				vendedor.getNome(),
				vendedor.getCpf_cnpj(),
				123456,
				vendedor.getEmail(),
				produtoReferencia.getId_prod(),
				valorTotal,
				new java.util.Date(),
				pedidoAssociado);
	}

	public static Object[] realizarPagamento(Scanner scanner, Comprador comprador, Vendedor vendedor,
			float valorTotal) {
		String formaPagamentoEscolhida = "";
		boolean pagamentoAprovado = false;
		int parcelas = 1;

		while (true) {
			System.out.println("\n============================================================");
			System.out.println("             ESCOLHA A FORMA DE PAGAMENTO                ");
			System.out.println("============================================================");
			System.out.println(" 1. Carteira Digital (Saldo disponível: R$ "
					+ String.format("%.2f", comprador.getCarteira().getSaldo()) + ")");
			System.out.println(" 2. Cartão de Crédito");
			System.out.println(" 3. PIX");
			System.out.println(" 4. Boleto Bancário");
			System.out.println(" 5. Cancelar Pagamento");
			System.out.println("============================================================");
			System.out.print(">>> Escolha uma opção: ");
			int opcaoPagamento = scanner.nextInt();
			scanner.nextLine();

			switch (opcaoPagamento) {
				case 1: // Carteira
					if (comprador.getCarteira().getSaldo() < valorTotal) {
						System.out.println("\n! Saldo insuficiente na carteira para esta compra.");
					} else {
						formaPagamentoEscolhida = "CARTEIRA";
						pagamentoAprovado = true;
					}
					break;

				case 2: // Cartão de Crédito
					System.out.println("\n--- Pagamento com Cartão de Crédito ---");
					if (valorTotal > 500) {
						System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal));
						System.out.print("Número de parcelas (1 a 12x): ");
						parcelas = scanner.nextInt();
						scanner.nextLine();

						if (parcelas < 1 || parcelas > 12) {
							System.out.println("! Número de parcelas inválido. Por favor, escolha entre 1 e 12.");
							break;
						}
						float valorParcela = valorTotal / parcelas;
						System.out.printf("Compra de R$ %.2f em %d parcelas de R$ %.2f\n", valorTotal, parcelas, valorParcela);
					} else {
						System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal) + " (Pagamento à vista).");
					}
					System.out.println("Processando pagamento via Cartão de Crédito...");
					System.out.println("Pagamento com Cartão de Crédito aprovado!");
					formaPagamentoEscolhida = "CARTAOCREDITO";
					pagamentoAprovado = true;
					break;

				case 3: // PIX
					System.out.println("\n--- Pagamento via PIX ---");
					System.out.println("Chave PIX do Vendedor: " + vendedor.getEmail());
					System.out.printf("Valor a pagar: R$ %.2f\n", valorTotal);
					System.out.println("Aguardando confirmação do pagamento via PIX...");
					System.out.println("Pagamento PIX confirmado!");
					formaPagamentoEscolhida = "PIX";
					pagamentoAprovado = true;
					break;

				case 4: // Boleto
					System.out.println("\n--- Pagamento via Boleto Bancário ---");
					System.out.printf("Valor do boleto: R$ %.2f\n", valorTotal);
					System.out.println("Boleto gerado. O pagamento será processado após a compensação bancária.");
					System.out.println("Por favor, pague o boleto em até 3 dias úteis.");
					formaPagamentoEscolhida = "BOLETO";
					pagamentoAprovado = true;
					break;

				case 5: // Cancelar Pagamento
					System.out.println("\nPagamento cancelado pelo usuário.");
					return new Object[] { "CANCELADO", false, 1 };
				default:
					System.out.println("\n! Opção de pagamento inválida. Por favor, tente novamente.");
			}

			if (pagamentoAprovado) {
				System.out.println("\nPressione ENTER para continuar...");
				scanner.nextLine();
				break;
			} else {
				System.out.println("Por favor, escolha outra forma de pagamento ou adicione saldo à carteira.");
				System.out.println("\nPressione ENTER para continuar...");
				scanner.nextLine();
			}
		}
		return new Object[] { formaPagamentoEscolhida, pagamentoAprovado, parcelas };
	}

	public static void gerarRelatorioSugestao(Comprador comprador, List<Produto> produtos) {
		System.out.println("\n============================================================");
		System.out.println("             RELATÓRIO DE SUGESTÃO DE COTAS                ");
		System.out.println("============================================================");
		if (produtos.isEmpty()) {
			System.out.println("Nenhuma cota solar disponível para sugestão no momento.");
			return;
		}

		float consumo = comprador.getMediaConsumoMensal();

		if (consumo <= 0) {
			System.out.println(
					"Sua média de consumo mensal não foi informada ou é inválida. Não é possível gerar uma sugestão de cotas.");
			System.out.println("Por favor, atualize seu perfil com um consumo válido.");
			return;
		}

		List<Produto> produtosOrdenados = new ArrayList<>(produtos);
		produtosOrdenados.sort(Comparator
				.comparingDouble((Produto p) -> p.getPreco() / p.getCapacidadeKwhPorUnidade())
				.thenComparingDouble(Produto::getPreco));

		double consumoAlvo = consumo;
		List<Produto> cotasSugeridas = new ArrayList<>();
		double totalCustoEstimado = 0;
		double kwhTotalSugerido = 0;

		while (kwhTotalSugerido < consumoAlvo) {
			Produto melhorProximaCota = null;

			double menorExcedenteKwh = Double.MAX_VALUE;
			double menorCustoPorKwhExcedente = Double.MAX_VALUE;

			for (Produto cota : produtosOrdenados) {
				if (cota.getCapacidadeKwhPorUnidade() <= 0)
					continue;

				double kwhComPotencial = kwhTotalSugerido + cota.getCapacidadeKwhPorUnidade();
				double custoPorKwhDaCota = cota.getPreco() / cota.getCapacidadeKwhPorUnidade();

				if (kwhComPotencial <= consumoAlvo) {
					if (melhorProximaCota == null || (custoPorKwhDaCota < (melhorProximaCota.getPreco()
							/ melhorProximaCota.getCapacidadeKwhPorUnidade()))) {
						melhorProximaCota = cota;
						menorExcedenteKwh = Double.MAX_VALUE;
					} else if (custoPorKwhDaCota == (melhorProximaCota.getPreco()
							/ melhorProximaCota.getCapacidadeKwhPorUnidade()) &&
							cota.getPreco() < melhorProximaCota.getPreco()) {
						melhorProximaCota = cota;
						menorExcedenteKwh = Double.MAX_VALUE;
					}
				} else {
					double excedenteAtual = kwhComPotencial - consumoAlvo;
					if (melhorProximaCota == null
							|| (kwhTotalSugerido + melhorProximaCota.getCapacidadeKwhPorUnidade() > consumoAlvo)) {
						if (excedenteAtual < menorExcedenteKwh) {
							menorExcedenteKwh = excedenteAtual;
							menorCustoPorKwhExcedente = custoPorKwhDaCota;
							melhorProximaCota = cota;
						} else if (excedenteAtual == menorExcedenteKwh && custoPorKwhDaCota < menorCustoPorKwhExcedente) {
							menorCustoPorKwhExcedente = custoPorKwhDaCota;
							melhorProximaCota = cota;
						}
					}
				}
			}

			if (melhorProximaCota == null) {
				break;
			}

			cotasSugeridas.add(melhorProximaCota);
			kwhTotalSugerido += melhorProximaCota.getCapacidadeKwhPorUnidade();
			totalCustoEstimado += melhorProximaCota.getPreco();
		}

		System.out.println("Seu consumo médio mensal: " + String.format("%.2f", consumo) + " kWh");

		Map<String, Integer> contagemPorProduto = new LinkedHashMap<>();
		Map<String, Produto> detalhesPorProduto = new HashMap<>();

		for (Produto p : cotasSugeridas) {
			String chaveProduto = p.getId_prod() + "-" + p.getNome_prod() + "-" + p.getCapacidadeKwhPorUnidade() + "-"
					+ p.getPreco();
			contagemPorProduto.put(chaveProduto, contagemPorProduto.getOrDefault(chaveProduto, 0) + 1);
			detalhesPorProduto.put(chaveProduto, p);
		}

		System.out.println("Cotas sugeridas para cobrir seu consumo:");
		if (contagemPorProduto.isEmpty()) {
			System.out.println("- Nenhuma cota adequada encontrada para cobrir o consumo com as cotas disponíveis.");
		} else {
			for (Map.Entry<String, Integer> entry : contagemPorProduto.entrySet()) {
				Produto p = detalhesPorProduto.get(entry.getKey());
				int count = entry.getValue();
				System.out.println("- " + (count > 1 ? count + "x " : "") + p.getNome_prod() + " | "
						+ p.getCapacidadeKwhPorUnidade() + " kWh | R$ " + String.format("%.2f", p.getPreco()));
			}
		}

		System.out.println("Total de kWh sugerido: " + String.format("%.2f", kwhTotalSugerido) + " kWh");
		System.out.println("Total estimado de custo: R$ " + String.format("%.2f", totalCustoEstimado));

		if (kwhTotalSugerido > consumoAlvo) {
			System.out.println("Excedente sugerido: " + String.format("%.2f", kwhTotalSugerido - consumoAlvo) + " kWh");
		} else if (kwhTotalSugerido < consumoAlvo) {
			System.out
					.println("Consumo restante não coberto: " + String.format("%.2f", consumoAlvo - kwhTotalSugerido) + " kWh");
		}
		System.out.println("=========================================");
	}
}