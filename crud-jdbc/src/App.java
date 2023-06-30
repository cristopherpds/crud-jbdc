import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.CargoDAO;
import dao.FuncionarioDAO;
import models.Cargo;
import models.Funcionario;
import application.CargoApplication;
import application.FuncionarioApplication;

public class App extends JFrame implements ActionListener{
    private JButton funcionariosButton;
    private JButton cargosButton;
    private JButton sairButton;

    public App() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));
        setResizable(false);

        // Define uma fonte personalizada para os botões
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        funcionariosButton = new JButton("Funcionários");
        cargosButton = new JButton("Cargos");
        sairButton = new JButton("Sair");

        funcionariosButton.addActionListener(this);
        cargosButton.addActionListener(this);
        sairButton.addActionListener(this);

        // Aplica a fonte personalizada aos botões
        funcionariosButton.setFont(buttonFont);
        cargosButton.setFont(buttonFont);
        sairButton.setFont(buttonFont);

        // Define uma cor de fundo personalizada para os botões
        Color buttonBackgroundColor = new Color(220, 220, 220);
        funcionariosButton.setBackground(buttonBackgroundColor);
        cargosButton.setBackground(buttonBackgroundColor);
        sairButton.setBackground(buttonBackgroundColor);

        add(funcionariosButton);
        add(cargosButton);
        add(sairButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == funcionariosButton) {
            FuncionarioApplication.exibirMenuFuncionario();
        } else if (e.getSource() == cargosButton) {
            CargoApplication.exibirMenuCargo();
        } else if (e.getSource() == sairButton) {
            JOptionPane.showMessageDialog(null, "Encerrando o programa...");
            dispose(); // Fechar a janela
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                App menu = new App();
                menu.setVisible(true);
            }
        });
    }
/*    public static void main(String[] args) throws SQLException {
        int opcao = 0;

        do {

            String escolha = JOptionPane.showInputDialog(null,
                    "Menu Principal\n\n" +
                            "1. Funcionários\n" +
                            "2. Cargos\n" +
                            "3. Sair\n\n" +
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
                    FuncionarioApplication.exibirMenuFuncionario();
                    break;
                case 2:
                    CargoApplication.exibirMenuCargo();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Encerrando o programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 3);
    } */
}
