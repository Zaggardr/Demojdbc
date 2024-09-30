
package test;

import beans.Site;
import java.sql.*;


public class Test {

    
    public static void main(String[] args) {
   
//        save(new Site("SAFI"));
//        save(new Site("MARRAKECH"));
//        save(new Site("EL JADIDA"));
//    
        load();
    }

    public static void save(Site s) {
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/db";
        Connection cn = null;
        Statement st = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            st = cn.createStatement();
            String req = "insert into site values(null,'" + s.getNom() + "')";
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        } finally {
            try {
                
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");

            }

        }

    }

    public static void load() {
        
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/db";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            st = cn.createStatement();
            String req = "select * from site";
            rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");

            }

        }

    }
}
