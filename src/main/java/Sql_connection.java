import java.sql.Connection;
import java.sql.DriverManager;
public class Sql_connection {
    public static Connection getCon(){
        Connection con=null;
        try{
            String jdbcURL = "jdbc:mysql://localhost:3306/parkinglot?useSSL=false";
            String userName = "root";
            String password = "Rohitsharma45";
            Connection connection;
            System.out.println("Connecting to database : " +jdbcURL);
            connection = DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connection is successful!!!" + connection);
            return connection;
        }
        catch(Exception e){System.out.println(e);}
        return con;
    }
}
