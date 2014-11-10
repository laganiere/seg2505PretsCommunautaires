package ca.uottawa.eecs.seg2505.objetpret;

import java.util.Date;


public class Disponibilite {

    private Date date = null;

    private String statut = "disponible";

    private Objet objet = null;
    
    public Disponibilite(Objet objet) {
        this.objet = objet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date newDate) {
        date = newDate;
    }

    public String getStatut() {
        return statut;
    }

    public void setDate(String newStatut) {
        statut = newStatut;
    }

    public void setObjet(Objet newObjet) {
        objetprett = newObjet;
    }

}
        
