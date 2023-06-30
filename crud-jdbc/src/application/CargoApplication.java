package application;

import java.util.List;

import javax.swing.JOptionPane;

import dao.CargoDAO;
import models.Cargo;

public class CargoApplication {
	public static void exibirMenuCargo() {
		CargoDAO cargoDAO = new CargoDAO();
		int opcao = 0;

		do {
			String escolha = JOptionPane.showInputDialog(
					"Menu de Cargo\n\n" +
							"1. Adicionar Cargo\n" +
							"2. Atualizar Cargo\n" +
							"3. Excluir Cargo\n" +
							"4. Visualizar Cargos\n" +
							"5. Visualizar Cargos  y Funcionarios\n" +
							"6. Sair\n\n" +
							"Escolha uma opção:");

			if (escolha == null) {
				// Usuário clicou em "Cancelar" ou fechou a janela
				break;
			}

			try {
				opcao = Integer.parseInt(escolha);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, tente novamente.");
				continue;
			}

			switch (opcao) {
				case 1:
					String nome = JOptionPane.showInputDialog("Digite o nome do cargo:");
					if (nome != null && !nome.isEmpty()) {
						System.out.println(nome);
						Cargo novoCargo = new Cargo();
						novoCargo.setNome(nome);
						cargoDAO.save(novoCargo);
						JOptionPane.showMessageDialog(null, "Cargo adicionado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Nome inválido. Por favor, tente novamente.");
					}
					break;
				case 2:
					int id;
					try {
						id = Integer
								.parseInt(JOptionPane.showInputDialog("Digite o ID do cargo a ser atualizado:"));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID inválido. Por favor, tente novamente.");
						continue;
					}
					String novoNome = JOptionPane.showInputDialog("Digite o novo nome do cargo:");
					if (novoNome != null && !novoNome.isEmpty()) {
						Cargo cargoActualizado = new Cargo();
						cargoActualizado.setNome(novoNome);
						cargoActualizado.setId_cargo(id);

						cargoDAO.updateCargo(cargoActualizado);
						JOptionPane.showMessageDialog(null, "Cargo atualizado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Nome inválido. Por favor, tente novamente.");
					}
					break;
				case 3:
					int idExcluir;
					try {
						idExcluir = Integer
								.parseInt(JOptionPane.showInputDialog("Digite o ID do Cargo a ser excluído:"));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID inválido. Por favor, tente novamente.");
						continue;
					}

					cargoDAO.deleteByID(idExcluir);
					JOptionPane.showMessageDialog(null, "Cargo excluído com sucesso!");
					break;
				case 4:
					StringBuilder listaCargos = new StringBuilder();
					for (Cargo c : cargoDAO.findCargos()) {
						listaCargos.append("ID: ").append(c.getId_cargo()).append("\n");
						listaCargos.append("Nome do Cargo: ").append(c.getNome()).append("\n");
						listaCargos.append("---------------------------------\n");
					}
					JOptionPane.showMessageDialog(null, listaCargos.toString(),
							"Lista de Cargos", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 5:
					List<Cargo> cargosFuncionarios = cargoDAO.findCargosAndFuncionario();
					StringBuilder listaCargosFuncionarios = new StringBuilder();
					for (Cargo c : cargosFuncionarios) {
						listaCargosFuncionarios.append("Cargo: ").append(c.getNome()).append("\n");
						listaCargosFuncionarios.append("Nome Funcionario: ").append(c.getFuncionario().getNome())
								.append("\n");
						listaCargosFuncionarios.append("Email: ").append(c.getFuncionario().getNome()).append("\n");
						listaCargosFuncionarios.append("---------------------------------\n");
					}
					JOptionPane.showMessageDialog(null, listaCargosFuncionarios.toString(),
							"Lista de Cargos y Funcionarios", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "Voltando ao Menu Principal...");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, tente novamente.");
			}
		} while (opcao != 6);
	}
}
