package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Factory.ConnectionFactory;
import models.Funcionario;

public class FuncionarioDAO {

    public void save(Funcionario funcionario) {

		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "INSERT INTO funcionario(nome, email, idade) VALUES (?, ?, ?)";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, funcionario.getNome());
			pstm.setString(2, funcionario.getEmail());
			pstm.setInt(3,funcionario.getIdade());
			pstm.execute();
			System.out.println("Funcionario salvo com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

    public void updateFuncionario(Funcionario funcionario){
		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "UPDATE funcionario SET nome = ?, email = ?, idade = ? WHERE id_func = ?;" ;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, funcionario.getNome());
			pstm.setString(2, funcionario.getEmail());
            pstm.setInt(3, funcionario.getIdade());

			pstm.setInt(4, funcionario.getId_func());
            pstm.execute();
            System.out.println("Funcionario atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

    public List<Funcionario> findFuncionarios() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(rst.getInt("id_func"));
                funcionario.setNome(rst.getString("nome"));
                funcionario.setIdade(rst.getInt("idade"));
                funcionario.setEmail(rst.getString("email"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return funcionarios;

    }

    public void deleteByID(int id_func){
		Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE from funcionario WHERE id_func = ?;";

		try {
			conn  = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id_func);
			pstm.execute();
			System.out.println("Funcionario deletado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
