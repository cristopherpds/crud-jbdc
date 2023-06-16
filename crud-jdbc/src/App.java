import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        String sql = "select * from funcionario";
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String password = "";

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();


            while (rs.next()) {
                System.out.println(rs.getString("nome"));
            }

            rs.close();

            stm.close();


        } catch (Exception e) {
            
            e.printStackTrace();
        }
       
    }
}
