
package devfn;

import developpeur.Developpeur;
import connexion.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import dao.IDao;


public class DevFn implements IDao<Developpeur> {

    public DevFn() {
    }

    @Override
    public boolean create(Developpeur dev) {

        boolean bool = false;
        Statement st = null;
        Connection cn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            cn = Connexion.getCn();
            st = cn.createStatement();
            String req = "insert into devdata values('" + dev.getNom()
                    + "','" + dev.getJour() + "'," + dev.getNbrScript() + ")";

            int n = st.executeUpdate(req);
            if (n == 1) {
                bool = true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
        return bool;
    }

    @Override
    public List<Developpeur> findAll() {

        List<Developpeur> devs = null;
        Statement st = null;
        Connection cn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            cn = Connexion.getCn();
            st = cn.createStatement();
            String req = "SELECT * from devdata";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                devs.add(new Developpeur(rs.getString("Developpeurs"), rs.getString("Jour"), rs.getInt("NbScripts")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
        return devs;
    }

    

}
