package net.mem.dao.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("PAN")
public class Panier extends Confection {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Digits(fraction = 0, integer = 3)
	@Min(value=1,message = "Le champ  nombre de paniers est incorrect. Il doit contenir au minimum 1 panier")
	private Integer nbPanier;
	@NotNull
	@Digits(fraction = 0, integer = 3)
	@Min(value=25,message = "Le champ  valeur est incorrect. Le minimum du panier doit être de 25 €")
	private Integer valeur;
	private boolean identique;
	public Panier() {
		super();
	}
	public Panier(int nbPanier, boolean identique,double montant) {
		super(montant,"Panier");
		this.nbPanier = nbPanier;
		this.identique = identique;
	}
	
	public Integer getNbPanier() {
		return nbPanier;
	}
	public void setNbPanier(Integer nbPanier) {
		this.nbPanier = nbPanier;
	}
	public boolean isIdentique() {
		return identique;
	}
	public void setIdentique(boolean identique) {
		this.identique = identique;
	}
	
	public Integer getValeur() {
		return valeur;
	}
	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	@Override
	public String toString() {
		return "Panier [nbPanier=" + nbPanier + ", identique=" + identique + "]";
	}
	
}
