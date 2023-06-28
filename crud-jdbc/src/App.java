import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {

        
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String password = "";
        

        String sqlUnion = "SELECT f.nome, f.email, c.nome FROM funcionario f, cargo c WHERE c.id_cargo = f.id_cargo";
        String sqlFindAll = "SELECT * FROM cargo ";
        String sqlAddCargo = "INSERT INTO cargo(nome) values('SCRUM Manager');";
        String sqlDeletCargo = "DELETE from cargo WHERE id_cargo = 5;" ;
        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = conn.prepareStatement(sqlUnion);   
            PreparedStatement stm2 = conn.prepareStatement(sqlAddCargo);
            PreparedStatement stm3 = conn.prepareStatement(sqlFindAll);

            PreparedStatement stm4 = conn.prepareStatement(sqlDeletCargo);
            

            //delete
            //stm4.executeUpdate();

            //insert into cargo
            //stm2.executeUpdate();     

            // selct
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println("nome:" + rs.getString("f.nome") + " email:" + rs.getString("f.email") + " cargo:" + rs.getString("c.nome") );
            }

            ResultSet rs2 = stm3.executeQuery();
            while (rs2.next()) {
                System.out.println("id:" + rs2.getString("id_cargo") + "nome:" + rs2.getString("nome")  );
            }


            // rs.close();
            // stm.close();
            //conn.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
    
        }
       
    
        // String sql = "SELECT * FROM funcionario";
        // String sql2 = "INSERT INTO funcionario(nome, email, idade, id_cargo) values('cristopher', 'cristopher@gmail.com', 25, 1);";
        // String sql3 = "UPDATE funcionario SET nome = 'josue', email = 'josue@hotmail.es' WHERE id_func = 4;" ;
        // String sql4 = "DELETE from funcionario WHERE id_func = 6;" ;
        // try {

        //     Connection conn = DriverManager.getConnection(url, user, password);
        //     PreparedStatement stm = conn.prepareStatement(sql);
        //     PreparedStatement stm2 = conn.prepareStatement(sql2);
        //     PreparedStatement stm3 = conn.prepareStatement(sql3);
        //     PreparedStatement stm4 = conn.prepareStatement(sql4);
            

        //     //delete
        //     stm4.executeUpdate();

        //     //update
        //     stm3.executeUpdate();


        //     //insert into
        //     // stm2.executeUpdate();


        //     // selct
        //     ResultSet rs = stm.executeQuery();
        //     while (rs.next()) {
        //         System.out.println("id:" + rs.getString("id_func") + " nome:" + rs.getString("nome") + " email: " + rs.getString("email") + " idade: " + rs.getString("idade")+ " cargo: " + rs.getString("id_cargo"));
        //     }


        //     // rs.close();
        //     // stm.close();
        //     //conn.close();
        // } catch (SQLException e) {
            
        //     e.printStackTrace();
    
        // }
       
    }
}
