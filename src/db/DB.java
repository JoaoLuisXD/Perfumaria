package src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    static Connection conn = null;

    public static Connection getConn() {
        if(conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/perfumaria", "root", "root000010000");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }    
        return conn;
    }

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeStatement(Statement st){
        if(st!=null) {
            try {
                st.close(); 
            } 
            catch (SQLException e) {
                throw new RuntimeException(e); 
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();    
            } catch (SQLException e) {
                throw new RuntimeException(e); 
            }
        }
    }

}