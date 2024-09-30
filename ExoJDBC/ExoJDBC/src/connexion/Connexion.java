package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {

    private static String login = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/scriptsdeveloppeurs";
    private static Connection cn = null;

    public static Connection getCn() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, password);
            Statement stmt = cn.createStatement();
            String query1 = "CREATE TABLE if not exists Devdata  (Developpeurs VARCHAR (32),Jour CHAR (11),NbScripts INTEGER)";
            stmt.execute(query1);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver introvable");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cn ;
    }
}
