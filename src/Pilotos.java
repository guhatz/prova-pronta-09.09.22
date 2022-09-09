import java.io.IOException;
import java.util.Scanner;

public class Pilotos {
	public static void main(String[] args) throws InterruptedException, IOException {
		int MAX_ELEMENTOS = 1;
		int opcao, qtdCadastrados = 0;
		Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
		Scanner in = new Scanner(System.in);

		do {
			System.out.println("\n****\nMENU\n****\n");
			System.out.println("1 - Cadastrar piloto");
			System.out.println("2 - Listar pilotos cadastrados");
			System.out.println("3 - Localizar piloto pelo CPF");
			System.out.println("4 - Aumentar espaço de armazenamento");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");

			opcao = in.nextInt();
			in.nextLine(); 

			if (opcao == 1) {
			
				if (qtdCadastrados == MAX_ELEMENTOS) {
					System.out.println("\nNão há espaço para cadastrar novos pilotos.");
					voltarMenu(in);

					continue;
				}

				Pessoa piloto = new Pessoa();

			
				System.out.print("Nome: ");
				piloto.setNome(in.nextLine());

				try {System.out.print("CPF: ");
					piloto.setCpf(in.nextLine());
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					voltarMenu(in);

					continue;
				}

				pilotos[qtdCadastrados] = piloto;
				qtdCadastrados++;

				System.out.println("\nPiloto cadastrado com sucesso.");
				voltarMenu(in);
			} else if (opcao == 2) {
		
				if (qtdCadastrados == 0) {
					System.out.println("\nErro!.");
					voltarMenu(in);

					continue;
				}

				System.out.println("Seus pilotos cadrastrados:");
				

				for (int i = 0; i < qtdCadastrados; i++) {
					System.out.println(pilotos[i]);
				}

				voltarMenu(in);
			} else if (opcao == 3) {
			
				if (qtdCadastrados == 0) {
					System.out.println("\nNão há motoristas cadastrados ainda.");
					voltarMenu(in);

					continue;
				}

				System.out.print("CPF para buscar: ");
				String cpfBusca = in.nextLine();

				if ("".equals(cpfBusca.trim())) {
					System.out.println("\nCPF da busca não pode ser vazio.");
					voltarMenu(in);

					continue;
				}

				Pessoa pilotoBusca = null;

				for (int i = 0; pilotoBusca == null && i < qtdCadastrados; i++) {
					if (pilotos[i].getCpf().equals(cpfBusca)) {
						pilotoBusca = pilotos[i];
					}
				}

				if (pilotoBusca != null) {
					System.out.printf("Piloto localizado: %s\n", pilotoBusca);
				} else {
					System.out.println("Erro");
				}

				voltarMenu(in);
			} else if (opcao == 4) {
				System.out.printf("novo tamanho (atual é %d): ", MAX_ELEMENTOS);
				int novotamanho = in.nextInt();
				in.nextLine(); 

				if (novotamanho <= MAX_ELEMENTOS) {
					System.out.println("Digite tamanho maior");
					voltarMenu(in);

					continue;
				}

				Pessoa[] novoVetor = new Pessoa[novotamanho];
				for (int i = 0; i < qtdCadastrados; i++) {
					novoVetor[i] = pilotos[i];
				}

				pilotos = novoVetor;
				MAX_ELEMENTOS = novotamanho;

				novoVetor = null;

				System.out.printf("validado(%d).\n", novotamanho);
				voltarMenu(in);
			} else if (opcao != 0) {
				System.out.println("\nErro!");
			}
		} while (opcao != 0);

		System.out.println("Fim do programa!");

		in.close();
	}

	private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
		System.out.println("\nPressione ENTER para voltar ao menu.");
		in.nextLine();

		if (System.getProperty("os.name").contains("Windows"))
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		else
			System.out.print("\033[H\033[2J");

		System.out.flush();
	}
}