
package developpeur;



public class Developpeur {
    
    private String nom;
    private String jour;
    private int nbrScript;

    public Developpeur() {
    }

    
    public Developpeur(String nom, String jour, int nbrScript) {
        this.nom = nom;
        this.jour = jour;
        this.nbrScript = nbrScript;
    }

    public String getNom() {
        return nom;
    }

    public String getJour() {
        return jour;
    }

    public int getNbrScript() {
        return nbrScript;
    }

    public void setNomDeveloppeur(String nom) {
        this.nom = nom;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setNbrSript(int nbrScript) {
        this.nbrScript = nbrScript;
    }

    
    @Override
    public String toString() {
        return " Le Developpeur : " + nom + ", son jour est :" 
                + jour + ", le nombre des Sripts réalisés a ce jour est : " + nbrScript;
    }
    
    
    
}
