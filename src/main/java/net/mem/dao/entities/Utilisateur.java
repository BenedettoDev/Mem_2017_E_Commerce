package net.mem.dao.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	 @NotNull
	@Id
	private String username;
	@Email
	@NotEmpty
	private String mail;
	@NotEmpty
	private String nom;

	private String prenom;
	
	 private String telephone;

	 private String adresse;
	 
	 private String password;
	 
	 private boolean actived;

	 private String role;

	@OneToMany (mappedBy="utilisateur",fetch=FetchType.LAZY)
	private Collection<Commande> commandes;
	
	//Constructeurs
	public Utilisateur() {
		super();
	}

	public Utilisateur(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Utilisateur(String username, String mail, String nom, String prenom, String password) {
		this(username,password);
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.role = "USER";
		this.actived = true;
	}

	public Utilisateur(Utilisateur utilisateur) {
		this(utilisateur.username,utilisateur.password);
		this.mail = utilisateur.mail;
		this.nom = utilisateur.nom;
		this.prenom = utilisateur.prenom;
		this.role = "USER";
		this.actived = true;
	}
	
	//Getters et setters
	public String getNom() {
		return nom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String pass) {
		this.password = pass;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}
	
	public String getRoles() {
		return role;
	}

	public void setRoles(String roles) {
		this.role = roles;
	}
	

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void insciption() {
		this.actived = true;
		this.role = "USER";		
	}
	@Override
	public String toString() {
		return "Utilisateur [username=" + username + ", mail=" + mail + ", nom=" + nom + ", prenom=" + prenom
				+ ", password=" + password + ", actived=" + actived + ", roles=" + role + "]";
	}


}
