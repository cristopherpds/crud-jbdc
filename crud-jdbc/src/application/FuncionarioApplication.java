package application;

import javax.swing.JOptionPane;

import dao.FuncionarioDAO;
import models.Funcionario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioApplication {

	public static void exibirMenuFuncionario() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		int opcao = 0;

		do {
			String escolha = JOptionPane.showInputDialog(
					"Menu de Funcionario\n\n" +
							"1. Adicionar Funcionario\n" +
							"2. Atualizar Funcionario\n" +
							"3. Excluir Funcionario\n" +
							"4. Visualizar Funcionario\n" +
							"5. Sair\n\n" +
							"Escolha uma opção:");

			if (escolha == null) {
				// Usuário clicou em "Cancelar" ou fechou a janela
				break;
			}

			try {
				opcao = Integer.parseInt(escolha);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,
						"Opção inválida. Por favor, tente novamente.");
				continue;
			}

			switch (opcao) {
				case 1:
					String nome = JOptionPane.showInputDialog("Digite o nome do funcionario:");
					String email = JOptionPane.showInputDialog("Digite o email do funcionario:");
					if (nome != null && !nome.isEmpty()) {
						int idade = 0;
						int id_cargo = 0;
						try {
							idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do Funcionario:"));
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"Idade inválida. Por favor, tente novamente.");
							continue;
						}
						try {
							id_cargo = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Cargo:"));
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"ID do Cargo inválida. Por favor, tente novamente.");
							continue;
						}

						Funcionario novoFuncionario = new Funcionario();
						novoFuncionario.setNome(nome);
						novoFuncionario.setIdade(idade);
						novoFuncionario.setEmail(email);
						novoFuncionario.setId_cargo(id_cargo);

						funcionarioDAO.save(novoFuncionario);
						JOptionPane.showMessageDialog(null, "Funcionario adicionado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null,
								"Nome inválido. Por favor, tente novamente.");
					}
					break;
				case 2:
					int id;
					try {
						id = Integer
								.parseInt(JOptionPane.showInputDialog("Digite o ID do funcionario a ser atualizado:"));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"ID inválido. Por favor, tente novamente.");
						continue;
					}

					String novoNome = JOptionPane.showInputDialog("Digite o novo nome do funcionario:");
					String novoEmail = JOptionPane.showInputDialog("Digite o novo email do funcionario:");
					if (novoNome != null && !novoNome.isEmpty()) {
						int novaIdade = 0;
						try {
							novaIdade = Integer
									.parseInt(JOptionPane.showInputDialog("Digite a nova idade do funcionario:"));
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"Idade inválida. Por favor, tente novamente.");
							continue;
						}

						Funcionario funcionarioActualizado = new Funcionario();
						funcionarioActualizado.setNome(novoNome);
						funcionarioActualizado.setEmail(novoEmail);
						funcionarioActualizado.setIdade(novaIdade);
						funcionarioActualizado.setId_func(id);

						funcionarioDAO.updateFuncionario(funcionarioActualizado);
						JOptionPane.showMessageDialog(null, "Funcionario atualizado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null,
								"Nome inválido. Por favor, tente novamente.");
					}
					break;
				case 3:
					int idExcluir;
					try {
						idExcluir = Integer
								.parseInt(JOptionPane.showInputDialog("Digite o ID do Funcionario a ser excluído:"));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"ID inválido. Por favor, tente novamente.");
						continue;
					}

					funcionarioDAO.deleteByID(idExcluir);
					JOptionPane.showMessageDialog(null, "Funcionario excluído com sucesso!");
					break;
				case 4:
					StringBuilder listaFuncionario = new StringBuilder();
					for (Funcionario f : funcionarioDAO.findFuncionarios()) {
						listaFuncionario.append("ID: ").append(f.getId_func()).append("\n");
						listaFuncionario.append("Nome: ").append(f.getNome()).append("\n");
						listaFuncionario.append("Email: ").append(f.getEmail()).append("\n");
						listaFuncionario.append("Idade: ").append(f.getIdade()).append("\n");
						listaFuncionario.append("---------------------------------\n");
					}
					JOptionPane.showMessageDialog(null, listaFuncionario.toString(),
							"Lista de Funcionarios",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Voltando ao Menu Principal...");
					break;
				default:
					JOptionPane.showMessageDialog(null,
							"Opção inválida. Por favor, tente novamente.");
			}
		} while (opcao != 5);
	}

}
