package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.Cargo;
import models.Funcionario;

public class CargoDAO {

    public void save(Cargo cargo) {

        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO cargo(nome) VALUES(?) ";

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cargo.getNome());
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

    public void updateCargo(Cargo cargo) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE cargo SET nome = ? WHERE id_cargo = ?";

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cargo.getNome());
            pstm.setInt(2, cargo.getId_cargo());
            pstm.execute();
            System.out.println("Cargo atualizado com sucesso!");
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
        List<Cargo> cargosFuncionarios = new ArrayList<Cargo>();

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()) {
                Cargo cargo = new Cargo();
                Funcionario funcionario = new Funcionario();
                /*
                 * funcionario.setNome(rst.getString("f.nome"));
                 * funcionario.setEmail(rst.getString("f.email"));
                 * cargo.setNome(rst.getString("c.nome"));
                 */
                funcionario.setNome(rst.getString("f.nome"));
                funcionario.setEmail(rst.getString("f.email"));
                cargo.setNome(rst.getString("c.nome"));
                cargo.setFuncionario(funcionario);

                cargosFuncionarios.add(cargo);
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
        return cargosFuncionarios;
    }

    public void deleteByID(int id_cargo) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE from cargo WHERE id_cargo = ?";

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id_cargo);
            pstm.execute();
            System.out.println("Cargo deletado com sucesso!");
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
}
