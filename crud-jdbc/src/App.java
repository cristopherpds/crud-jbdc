import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {

        String sql = "SELECT * FROM funcionario";
        String sql2 = "INSERT INTO funcionario(nome, email, idade) values('cristopher', 'cristopher@gmail.com', 25);";
        String sql3 = "UPDATE funcionario SET nome = 'josue', email = 'josue@hotmail.es' WHERE id_func = 4;" ;
        String sql4 = "DELETE from funcionario WHERE id_func = 6;" ;
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String password = "";

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = conn.prepareStatement(sql);
            PreparedStatement stm2 = conn.prepareStatement(sql2);
            PreparedStatement stm3 = conn.prepareStatement(sql3);
            PreparedStatement stm4 = conn.prepareStatement(sql4);
            

            //delete
            stm4.executeUpdate();

            //update
            stm3.executeUpdate();


            //insert into
            // stm2.executeUpdate();


            // selct
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println("id:" + rs.getString("id_func") + " nome:" + rs.getString("nome") + " email: " + rs.getString("email") + " idade: " + rs.getString("idade"));
            }


            // rs.close();
            // stm.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
    
        }
       
    }
}
