package ca.uottawa.eecs.seg2505.objetpret.model;

import java.util.Date;


public class Disponibilite {

    public enum Statut{DISPONIBLE, ENDEMANDE, PRETER, NONDISPONIBLE}

    private Date date = null;

    private Statut statut = Statut.DISPONIBLE;

    private Objet objet = null;
    
    public Disponibilite(Objet objet, Date date) {
        this.objet = objet;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut newStatut) {
        statut = newStatut;
    }

    public Objet getObjet() {
        return objet;
    }

}
        
