package net.mem.dao.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PAN")
public class Panier extends Confection {

	private static final long serialVersionUID = 1L;

	
	private int nbPanier;
	private boolean identique;
	public Panier() {
		super();
	}
	public Panier(int nbPanier, boolean identique,double montant) {
		super(montant,"Panier");
		this.nbPanier = nbPanier;
		this.identique = identique;
	}
	public int getNbPanier() {
		return nbPanier;
	}
	public void setNbPanier(int nbPanier) {
		this.nbPanier = nbPanier;
	}
	public boolean isIdentique() {
		return identique;
	}
	public void setIdentique(boolean identique) {
		this.identique = identique;
	}
	@Override
	public String toString() {
		return "Panier [nbPanier=" + nbPanier + ", identique=" + identique + "]";
	}
	
}
