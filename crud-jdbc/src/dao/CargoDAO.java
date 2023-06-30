package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Factory.ConnectionFactory;
import models.Cargo;

public class CargoDAO {

    public void save(Cargo cargo) {

		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "INSERT INTO cargo(nome) VALUES(?)";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cargo.getId_cargo());
			pstm.setString(2, cargo.getNome());
			pstm.execute();
			System.out.println("Cargo salvo com sucesso!");
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

    // public void updateFuncionario(Funcionario funcionario){
	// 	Connection conn = null;
	// 	PreparedStatement pstm = null;
	// 	String sql = "UPDATE funcionario SET nome = ?, email = ?, idade = ? WHERE id_func = ?;" ;

	// 	try {
	// 		conn = ConnectionFactory.createConnectionToMySQL();
	// 		pstm = conn.prepareStatement(sql);
	// 		pstm.setString(1, funcionario.getNome());
	// 		pstm.setString(2, funcionario.getEmail());
    //         pstm.setInt(3, funcionario.getIdade());

	// 		pstm.setInt(4, funcionario.getId_func());
    //         pstm.execute();
    //         System.out.println("Funcionario atualizado com sucesso!");
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 	}finally {
	// 		try {
	// 			if (pstm != null) {
	// 				pstm.close();
	// 			}
	// 			if (conn != null) {
	// 				conn.close();
	// 			}
	// 		} catch (SQLException e) {
	// 			e.printStackTrace();
	// 		}
	// 	}
	// }

    public List<Cargo> findCargos() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        String sql = "SELECT * FROM cargo";
        List<Cargo> cargos = new ArrayList<Cargo>();

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()) {
                Cargo cargo = new Cargo();
                cargo.setId_cargo(rst.getInt("id_cargo"));
                cargo.setNome(rst.getString("nome"));
                cargos.add(cargo);
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
        return cargos;

    }
    public List<Cargo> findCargosAndFuncionario() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        String sql = "SELECT f.nome, f.email, c.nome FROM funcionario f, cargo c WHERE c.id_cargo = f.id_cargo";
        List<Cargo> cargos = new ArrayList<Cargo>();

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()) {
                Cargo cargo = new Cargo();
                cargo.setNome(rst.getString("f.nome"));
                cargo.setNome(rst.getString("f.email"));
                cargo.setNome(rst.getString("c.nome"));
                
                cargos.add(cargo);
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
        return cargos;

    }

    public void deleteByID(int id_cargo){
		Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE from cargo WHERE id_func = ?;";

		try {
			conn  = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id_cargo);
			pstm.execute();
			System.out.println("Cargo deletado com sucesso!");
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
