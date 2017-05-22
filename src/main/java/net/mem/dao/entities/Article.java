package net.mem.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String titre;
	
	@Column(columnDefinition = "TEXT")
	private String Description;
	
	@ManyToOne
	@JoinColumn(name="FK_produit")
	private Produit produit;

	private Date date;
	
	public boolean isVisible;
	
	public Article() {
		super();
	}

	public Article(String titre, String description, Produit produit) {
		super();
		this.titre = titre;
		Description = description;
		this.produit = produit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", titre=" + titre + ", Description=" + Description + ", produit=" + produit
				+ ", date=" + date + ", isVisible=" + isVisible + "]";
	}

	
	
	
	
}
