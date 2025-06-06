package app;

import entities.users.Comprador;
import entities.users.Usuario;
import entities.users.Vendedor;

import java.util.Scanner;

public class Main {
	private static Usuario usuarioLogado = null;
	private static Comprador compradorLogado = null;
	private static Vendedor vendedorLogado = null;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n============================================================");
			System.out.println("            BEM-VINDO(A) AO GREENTECH SOLAR               ");
			System.out.println("============================================================");
			System.out.println(" 1. Fazer Login");
			System.out.println(" 2. Crie sua conta GreenTech");
			System.out.println(" 3. Sair da Aplicação");
			System.out.println("============================================================");
			System.out.print(">>> Escolha uma opção: ");
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
					System.out.println("\n--- Encerrando a aplicação. Obrigado(a) por usar o GreenTech! ---");
					scanner.close();
					return;
				default:
					System.out.println("\n! Opção inválida. Por favor, escolha um número entre 1 e 3.");
			}
			System.out.println("\n------------------------------------------------------------");
			System.out.println("Pressione ENTER para continuar...");
			scanner.nextLine();
		}
	}
}
