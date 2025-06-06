package entities.users;

import java.util.ArrayList;
import java.util.List;

import entities.address.Bairro;
import entities.address.Cidade;
import entities.address.Logradouro;
import entities.address.Uf;
import entities.checkout.Pedido;

public class Comprador extends Usuario {
	private int id_comprador;
	private List<Pedido> pedidos;
	private List<Avaliacoes> avaliacoes;

	public Comprador(String nome, String email, String senha, tipoUsuario tipo_usuario, String data_nasc,
			String cpf_cnpj, String telefone, Logradouro cep, Logradouro logradouro, Bairro bairro, Cidade cidade,
			Uf uf) {
		super(nome, email, senha, tipo_usuario, data_nasc, cpf_cnpj, telefone, cep, logradouro, bairro, cidade, uf);
		this.pedidos = new ArrayList<>();
		this.avaliacoes = new ArrayList<>();
	}

	public int getId_comprador() {
		return id_comprador;
	}

	public int getId_usuario() {
		return super.getId();
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public List<Avaliacoes> getAvaliacoes() {
		return avaliacoes;
	}

	public void verPedidos() {
		if (pedidos.isEmpty()) {
			System.out.println("Nenhum pedido realizado.");
		} else {
			System.out.println("Pedidos do Comprador ID " + getId() + ":");
			for (Pedido pedido : pedidos) {
				System.out.println(pedido);
				System.out.println("------------------------------");
			}
		}
	}

	public void verAvaliacoes() {
		if (avaliacoes.isEmpty()) {
			System.out.println("Nenhuma avaliação realizada.");
		} else {
			System.out.println("Avaliações do Comprador:");
			for (Avaliacoes avaliacao : avaliacoes) {
				System.out.println(avaliacao);
				System.out.println("------------------------------");
			}
		}
	}
}