package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {

	private static int incremento = 0;

	private int id_usuario;
	private String nome;
	private String email;
	private String senha;
	private String cpf_cnpj;
	private String telefone;
	private boolean logado = false;
	private String data_nasc;
	private tipoUsuario tipo_usuario;
	private Carteira carteira;
	private float mediaConsumoMensal;
	private Logradouro cep;
	private Logradouro logradouro;
	private Bairro bairro;
	private Cidade cidade;
	private Uf uf;

	private static List<Usuario> usuarios = new ArrayList<>();

	public Usuario(String nome, String email, String senha, tipoUsuario tipo_usuario, String data_nasc,
			String cpf_cnpj, String telefone, Logradouro cep, Logradouro logradouro, Bairro bairro, Cidade cidade, Uf uf) {
		this.id_usuario = incremento++;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo_usuario = tipo_usuario;
		this.logado = false;
		this.cpf_cnpj = cpf_cnpj;
		this.telefone = telefone;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.data_nasc = data_nasc;
		this.carteira = new Carteira();
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public tipoUsuario getTipo_usuario() {
		return tipo_usuario;
	}

	public int getId() {
		return id_usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}

	public float getMediaConsumoMensal() {
		return mediaConsumoMensal;
	}

	public void setMediaConsumoMensal(float mediaConsumoMensal) {
		this.mediaConsumoMensal = mediaConsumoMensal;
	}

	public Logradouro getCep() {
		return cep;
	}

	public void setCep(Logradouro cep) {
		this.cep = cep;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public tipoUsuario getTipoUsuario() {
		return tipo_usuario;
	}

	public enum tipoUsuario {
		VENDEDOR,
		COMPRADOR
	}

	public static Usuario login(Scanner scanner) {
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Senha: ");
		String senha = scanner.nextLine();

		Usuario usuarioLogado = null;

		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
				u.setLogado(true);
				System.out.println("Login realizado com sucesso!");
				usuarioLogado = u;
				break;
			}
		}
		if (usuarioLogado != null) {
			if (usuarioLogado.getTipoUsuario() == tipoUsuario.COMPRADOR) {
				return (Comprador) usuarioLogado;
			} else if (usuarioLogado.getTipoUsuario() == tipoUsuario.VENDEDOR) {
				return (Vendedor) usuarioLogado;
			}
		} else {
			System.out.println("Credenciais inválidas.");
		}
		return null;
	}

	public static List<Usuario> getUsuariosList() {
		return usuarios;
	}

	public static void adicionarListagemUsuarios(Usuario novoUsuario) {
		usuarios.add(novoUsuario);
	}

	public String getEndereço() {
		String logradouroStr = (this.logradouro != null) ? this.getLogradouro().toString() : "N/A";
		String bairroStr = (this.bairro != null) ? this.getBairro().toString() : "N/A";
		String cidadeStr = (this.cidade != null) ? this.getCidade().toString() : "N/A";
		String ufStr = (this.uf != null) ? this.getUf().toString() : "N/A";
		String cepStr = (this.cep != null) ? this.getCep().getCep() : "N/A";

		return logradouroStr + ", " + bairroStr + ", " + cidadeStr + "-" + ufStr + " - CEP: " + cepStr;
	}

	public void meusDados() {
		if (this.logado) {
			System.out.println("ID: " + this.id_usuario + "\n" + "Nome: " + this.nome + "\n" + "Email: " + this.email
					+ "\n" + "Telefone: " + this.telefone + "\n" + "CPF/CNPJ: " + this.cpf_cnpj + "\n" + "Endereço: "
					+ this.getEndereço() + "\n" + "Data Nascimento: " + this.data_nasc);
		} else {
			System.out.println("Faça login para visualizar seus dados!");
		}
	}

	public void meusDados(String dado) {
		if (this.logado) {
			switch (dado.toLowerCase()) {
				case "nome":
					System.out.println("Nome: " + this.nome);
					break;
				case "email":
					System.out.println("Email: " + this.email);
					break;
				case "telefone":
					System.out.println("Telefone: " + this.telefone);
					break;
				case "cpf":
					System.out.println("CPF: " + this.cpf_cnpj);
					break;
				case "cnpj":
					System.out.println("CNPJ: " + this.cpf_cnpj);
					break;
				case "endereço":
					System.out.println("Endereço: " + this.getEndereço());
					break;
				default:
					System.out.println("Dado inválido");
					break;
			}
		} else {
			System.out.println("Faça login para visualizar seus dados!");
		}
	}

	public void trocarSenha(String senhaA, String senhaN) {
		if (senhaA.equals(this.senha) && this.logado) {
			this.senha = senhaN;
			System.out.println("Senha alterada com sucesso!");
		} else {
			System.out.println("Não foi possível alterar a senha!");
		}
	}

	public void editarNome(String nome, String novoName) {
		if (nome.equals(this.nome) && this.logado) {
			this.nome = novoName;
			System.out.println("Nome alterado com sucesso!");
		} else {
			System.out.println("Não foi possível alterar o nome!");
		}
	}

	public void logout(boolean sair) {
		if (this.logado == true) {
			System.out.println("Você realmente deseja sair?");
			if (sair == true) {
				this.logado = false;
			} else {
				this.logado = true;
			}
		}
	}

	private static boolean isValidEmail(String email) {
		return email != null && email.contains("@") && email.contains(".com");
	}

	private static boolean isEmailAlreadyRegistered(String email) {
		for (Usuario u : usuarios) {
			if (u.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isValidCPF(String cpf) {
		if (cpf == null || cpf.isEmpty()) {
			return false;
		}
		String cleanCpf = cpf.replaceAll("[^0-9]", "");
		return cleanCpf.length() == 11;
	}

	private static boolean isCpfCnpjAlreadyRegistered(String cpfCnpj) {
		String cleanCpfCnpj = cpfCnpj.replaceAll("[^0-9]", "");
		for (Usuario u : usuarios) {
			if (u.getCpf_cnpj().replaceAll("[^0-9]", "").equals(cleanCpfCnpj)) {
				return true;
			}
		}
		return false;
	}

	public static void cadastroUsuario(Scanner scanner) {
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		String email;
		while (true) {
			System.out.print("Email: ");
			email = scanner.nextLine();
			if (!isValidEmail(email)) {
				System.out.println("! Formato de email inválido. Deve conter '@' e '.com'.");
			} else if (isEmailAlreadyRegistered(email)) {
				System.out.println("! Este email já está em uso. Por favor, digite outro email.");
			} else {
				break;
			}
		}

		System.out.print("Senha: ");
		String senha = scanner.nextLine();
		System.out.print("Tipo (1 - Comprador, 2 - Vendedor): ");
		int tipo = scanner.nextInt();
		scanner.nextLine();

		String cpfCnpj;
		while (true) {
			System.out.print("CPF/CNPJ: ");
			cpfCnpj = scanner.nextLine();

			if (tipo == 1) {
				if (!isValidCPF(cpfCnpj)) {
					System.out.println("! CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
					continue;
				}
			}

			if (isCpfCnpjAlreadyRegistered(cpfCnpj)) {
				System.out.println("! Este CPF/CNPJ já está em uso. Por favor, digite outro.");
			} else {
				break;
			}
		}

		System.out.print("Telefone: ");
		String telefone = scanner.nextLine();
		System.out.print("Data de nascimento (DD/MM/AAAA): ");
		String dataNasc = scanner.nextLine();

		System.out.print("CEP: ");
		String cepStr = scanner.nextLine();
		System.out.print("Endereço (Rua/Avenida): ");
		String enderecoStr = scanner.nextLine();
		Logradouro logradouro = new Logradouro(cepStr, enderecoStr);

		System.out.print("Bairro: ");
		String novoBairro = scanner.nextLine();
		Bairro bairro = new Bairro(novoBairro);

		System.out.print("Cidade: ");
		String novaCidade = scanner.nextLine();
		Cidade cidade = new Cidade(novaCidade);

		System.out.print("UF: ");
		String novoUf = scanner.nextLine();
		Uf uf = new Uf(novoUf);

		float mediaConsumo = 0;

		Usuario novoUsuario;
		if (tipo == 1) {
			tipoUsuario meuTipo = tipoUsuario.COMPRADOR;
			System.out.print("Informe sua média de consumo mensal (kWh): ");
			mediaConsumo = scanner.nextFloat();
			scanner.nextLine();

			novoUsuario = new Comprador(nome, email, senha, meuTipo, dataNasc, cpfCnpj, telefone, logradouro, logradouro,
					bairro, cidade, uf);
			novoUsuario.setMediaConsumoMensal(mediaConsumo);
		} else {
			tipoUsuario meuTipo = tipoUsuario.VENDEDOR;
			novoUsuario = new Vendedor(nome, email, senha, meuTipo, dataNasc, cpfCnpj, telefone, logradouro, logradouro,
					bairro, cidade, uf);
		}
		entities.Usuario.adicionarListagemUsuarios(novoUsuario);
		System.out.println("\n--- Cadastro realizado com sucesso! ---");
	}
}