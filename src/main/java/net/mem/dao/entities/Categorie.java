package net.mem.dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String label;
	private String nom;
	public Categorie() {
		super();
	}
	public Categorie(String nom) {
		this.nom = nom;
	}
	
	public Categorie(Long id, String label, String nom) {
		super();
		this.id = id;
		this.label = label;
		this.nom = nom;
	}
	public Categorie(String label, String nom) {
		this(nom);
		this.label = label;	
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", label=" + label + ", nom=" + nom + "]";
	}
	
}
