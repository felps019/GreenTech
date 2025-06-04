package entities;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
	private int id_pedido;
	private List<ItemCarrinho> itens;
	private float valorTotal;
	private Date data;
	private String forma_pagamento;
	private static int incrementaId = 0;
	private StatusPedido status;
	private NotaFiscal notaFiscal;
	private Comprador comprador;
	private Vendedor vendedor;
	private int parcelas;

	public enum StatusPedido {
		ATIVO,
		CANCELADO
	}

	public enum FormaPagamento {
		CARTAOCREDITO("1"),
		PIX("2"),
		BOLETO("3"),
		CARTEIRA("4");

		private final String codigo;

		FormaPagamento(String codigo) {
			this.codigo = codigo;
		}

		public String getCodigo() {
			return codigo;
		}
	}

	public Pedido(List<ItemCarrinho> itens, float valorTotal, String forma_pagamento_string,
			Comprador comprador, Vendedor vendedor) {
		this.id_pedido = ++incrementaId;
		this.itens = new ArrayList<>(itens);
		this.valorTotal = valorTotal;
		this.forma_pagamento = FormaPagamento.valueOf(forma_pagamento_string).getCodigo();
		this.data = new Date();
		this.status = StatusPedido.ATIVO;
		this.comprador = comprador;
		this.vendedor = vendedor;
		this.parcelas = 1;

		comprador.getPedidos().add(this);
		vendedor.getVendas().add(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("--- DETALHES DO PEDIDO ").append(id_pedido).append(" ---\n");
		sb.append("Comprador: ").append(comprador != null ? comprador.getNome() : "N/A").append("\n");
		sb.append("Vendedor Principal: ").append(vendedor != null ? vendedor.getNome() : "N/A").append("\n");
		sb.append("Data: ").append(data).append("\n");
		sb.append("Status: ").append(status).append("\n");
		sb.append("Itens:\n");
		for (ItemCarrinho item : itens) {
			sb.append("  - ").append(item.getQuantidadeUnidades()).append("x ")
					.append(item.getProduto().getNome_prod()).append(" (")
					.append(item.getProduto().getCapacidadeKwhPorUnidade()).append(" kWh cada) | Subtotal: R$ ")
					.append(String.format("%.2f", item.getSubtotal())).append("\n");
		}
		sb.append("Total de kWh do Pedido: ").append(getTotalKwhPedido()).append(" kWh\n"); // Adiciona o total de kWh
		sb.append("Valor Total do Pedido: R$ ").append(String.format("%.2f", valorTotal)).append("\n");
		sb.append("Forma de Pagamento: ").append(forma_pagamento);
		if (forma_pagamento.equals(FormaPagamento.CARTAOCREDITO.getCodigo()) && parcelas > 1) {
			sb.append(" (em ").append(parcelas).append("x)");
		}
		sb.append("\n");
		sb.append("--------------------------------------");

		return sb.toString();
	}

	public int getTotalKwhPedido() {
		int totalKwh = 0;
		for (ItemCarrinho item : itens) {
			totalKwh += item.getTotalKwhItem();
		}
		return totalKwh;
	}

	public void exibirPedido() {
		System.out.println(this.toString());
		if (notaFiscal != null) {
			notaFiscal.exibirNota();
		}
		System.out.println("--------------------------------------"); // Separador final
	}

	public void gerarNotaFiscal(NotaFiscal nf) {
		this.notaFiscal = nf;
		System.out.println("Nota fiscal associada ao pedido " + this.id_pedido);
	}

	public void cancelarPedido() {
		if (this.status == StatusPedido.ATIVO) {
			this.status = StatusPedido.CANCELADO;
			System.out.println("Pedido cancelado com sucesso.");
		} else {
			System.out.println("O pedido já consta como cancelado no sistema.");
		}
	}

	public int getIdPedido() {
		return id_pedido;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public float getValorTotalPedido() {
		return valorTotal;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}
}