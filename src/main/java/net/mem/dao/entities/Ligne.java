package net.mem.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ligne implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Id id;
		
	@ManyToOne
	@JoinColumn(name = "FKPRODUIT")
	private Produit produit;
	
	
	@Column(updatable = true)
	private int quantite;
	
	@Column(updatable = true)
	private double prix;
	
	@ManyToOne
	@JoinColumn(name="FKCOMMANDE")
	private Commande commande;
	
	public Ligne() {
	}
	
	public Ligne(Produit produit) {
		this.produit= produit;
		this.id.idProduit = produit.getId();
	}
	
	public Ligne(Produit produit, int quantite, Commande commande) {
		super();
		this.id.idProduit = produit.getId();
		this.id.idCommande = commande.getId();
		this.produit = produit;
		this.quantite = quantite;
		this.commande = commande;
	}
	public Ligne(Id id, Produit produit, int quantite, Commande commande) {
		super();
		this.id = id;
		this.produit = produit;
		this.quantite = quantite;
		this.commande = commande;
	}

	
	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
		this.id.idCommande = commande.getId();
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Ligne [id=" + id + ", produit=" + produit + ", quantite=" + quantite + ", prix=" + prix + ", commande="
				+ commande + "]";
	}



	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
	
		private Long idCommande;
		private Long idProduit;
		
		public Id () {
			super();
		}
		
		public Id(Long idCommande, Long idProduit) {
			super();
			this.idCommande = idCommande;
			this.idProduit = idProduit;
		}

		public Long getIdCommande() {
			return idCommande;
		}

		public void setIdCommande(Long idCommande) {
			this.idCommande = idCommande;
		}

		public Long getIdProduit() {
			return idProduit;
		}

		public void setIdProduit(Long idProduit) {
			this.idProduit = idProduit;
		}

		@Override
		public String toString() {
			return "Id [idCommande=" + idCommande + ", idProduit=" + idProduit + "]";
		}
	}
}
