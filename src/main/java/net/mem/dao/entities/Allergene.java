package net.mem.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Allergene implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToMany(mappedBy = "allergenes")
	private List<Produit> produits;
	public Allergene() {
		super();
	}
	
	public Allergene(String nom,String description) {
		super();
		this.nom = nom;
		this.description = description;
	}
	public Allergene(Long id,String nom,String description) {
		this(nom,description);
		this.id=id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return "Allergene [id=" + id + ", nom=" + nom + ", description=" + description + ", produits=" + produits + "]";
	}
}
