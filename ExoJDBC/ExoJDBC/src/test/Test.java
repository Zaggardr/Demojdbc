
package test;

import developpeur.Developpeur;
import connexion.Connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import devfn.DevFn;
import java.util.Scanner;


public class Test {

    private static DevFn df = new DevFn();


    public static void insertDev(Developpeur dev) {

        df.create(dev);

    }
    
    public static void DevMaxScript() {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            cn = Connexion.getCn();
            
            st = cn.createStatement();
            String req = "SELECT Developpeurs, Jour, max(NbScripts)"
                    + " from devdata"
                    + " group by Jour";
            
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println(" " + new Developpeur(rs.getString("Developpeurs"), rs.getString("Jour"), rs.getInt("max(NbScripts)")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }

    public static void DevScriptDecroissant() {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");           
            cn = Connexion.getCn();            
            st = cn.createStatement();
            String req = "SELECT Developpeurs, sum(NbScripts) as c"
                    + " from devdata"
                    + " Group by Developpeurs"
                    + " order by c desc";
            
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("Le développeur : " + rs.getString("Developpeurs") + " avec un nombre de script égal a : " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }

    public static void ScriptSemaine() {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");            
            cn = Connexion.getCn();            
            st = cn.createStatement();
            String req = "SELECT SUM(NbScripts) FROM devdata";           
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("Le nombre des scripts réalisés pandant la semaine est " + rs.getInt("SUM(NbScripts)") + " scripts");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }

    public static void ScriptDevSemaine(String nomDev) {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");           
            cn = Connexion.getCn();            
            st = cn.createStatement();
            String req = "SELECT SUM(NbScripts) FROM devdata WHERE Developpeurs='" + nomDev + "'";           
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("Le nombre des scripts réalisés pandant la semaine par " + nomDev + " est " + rs.getInt("SUM(NbScripts)") + " scripts");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }
    
    public static void LanceReq() {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            cn = Connexion.getCn();
            st = cn.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez votre requête SQL :");
            String req = scanner.nextLine();
            scanner.close();
            boolean bool = st.execute(req);
            if (bool) {
                ResultSet rs = st.executeQuery(req);
                ResultSetMetaData metaData = rs.getMetaData();
                System.out.println("\n---------------------\nLe nombre de colonnes est : " + metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    System.out.println("Colonne " + i + ": " + metaData.getColumnName(i) + " (Type: "
                            + metaData.getColumnTypeName(i) + ")");
                }
                System.out.println("\nContenu du résultat :");
                while (rs.next()) {
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.print("\n");
                }
            }else{
                // Si la requête modifie des lignes, afficher le nombre de lignes modifiées
                int rowsAffected = st.getUpdateCount();
                System.out.println("Nombre de lignes modifiées : " + rowsAffected);
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }

    }

    public static void main(String[] args) {
        
        //Test.DevMaxScript();
        //Test.ScriptSemaine();
        //Test.DevScriptDecroissant();
        //Test.ScriptDevSemaine("ALAMI");
        Test.LanceReq();

//                Test.insertDev(new Developpeur("ALAMI","Lundi",1));
//                Test.insertDev(new Developpeur("WAFI","Lundi",2));
//                Test.insertDev(new Developpeur("SALAMI","Mardi",9));
//                Test.insertDev(new Developpeur("SAFI","Mardi",2));
//                Test.insertDev(new Developpeur("ALAMI","Mardi",2));
//                Test.insertDev(new Developpeur("SEBIHI","Mercredi",2));
//                Test.insertDev(new Developpeur("WAFI","Jeudi",3));
//                Test.insertDev(new Developpeur("ALAOUI","Vendredi",9));
//                Test.insertDev(new Developpeur("WAFI","Vendredi",3));
//                Test.insertDev(new Developpeur("SEBIHI","Vendredi",4));
        
    }

}
