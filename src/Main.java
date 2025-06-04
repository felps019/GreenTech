import entities.Comprador;
import entities.Usuario;
import entities.Vendedor;
import java.util.Scanner;

import app.MockData;

public class Main {
	private static Usuario usuarioLogado = null;
	private static Comprador compradorLogado = null;
	private static Vendedor vendedorLogado = null;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		MockData.loadMockData();

		while (true) {
			System.out.println("_______________________________________________");
			System.out.println("\nMenu:");
			System.out.println("1. Login");
			System.out.println("2. Cadastro de Usuário");
			System.out.println("3. Sair");
			System.out.println("_______________________________________________");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					Usuario tipoUsuarioLogin = Usuario.login(scanner);
					if (tipoUsuarioLogin != null) {
						Main.usuarioLogado = tipoUsuarioLogin;
					}

					if (tipoUsuarioLogin instanceof Comprador) {
						Comprador comprador = (Comprador) tipoUsuarioLogin;
						app.Menu.menuLogadoComprador(scanner, comprador);
					} else if (tipoUsuarioLogin instanceof Vendedor) {
						Vendedor vendedor = (Vendedor) tipoUsuarioLogin;
						app.Menu.menuLogadoVendedor(scanner, vendedor);
					}
					break;
				case 2:
					Usuario.cadastroUsuario(scanner);
					break;
				case 3:
					System.out.println("Logout realizado com sucesso!");
					scanner.close();
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
}
