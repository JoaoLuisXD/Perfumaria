import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        //ResultSet rs = null;
        try{
            /* conn = DB.getConn();
            st = conn.createStatement();
            rs = st.executeQuery("select * from aluno");

            while (rs.next()) {
                System.out.println(rs.getString("nome")+ " " + rs.getString("cpf"));
            }
 */
            /* conn = DB.getConn();
            st = conn.prepareStatement("insert into aluno(nome,cpf) values (?,?)");
            st.setString(1, "Josefina");
            st.setString(2, "444.444.444-44");
            Date data = Date.valueOf("2005/10/10");
            st.setDate(3, data);
            st.executeUpdate();
            int linhasAfetadas = st.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas); */
            
            //update banco de dados
           /*  conn = DB.getConn();
            st = conn.prepareStatement("update aluno set nome = ? where matricula = ?");
            st.setString(1, "Hugo");
            st.setInt(2, 1);
            int linhas = st.executeUpdate();
            System.out.println("Linhas: " + linhas);            
        */

        //delete banco de dados
            /* conn = DB.getConn();
            st = conn.prepareStatement("delete from aluno where matricula = ?");
            st.setInt(1, 1);
            int linhas = st.executeUpdate();
            System.out.println("Linhas: " + linhas);           
 */
        }


        catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            //DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
    
}