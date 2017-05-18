package net.mem.dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Lieu implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String nom;

	public Lieu() {
		super();
	}

	public Lieu(String nom) {
		this.nom = nom;
	}
	
	public Lieu(Long id,String nom) {
		this.id = id;
		this.nom = nom;
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
	
	@Override
	public String toString() {
		return "Lieu [id=" + id + ", nom=" + nom + "]";
	}

}
