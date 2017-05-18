package net.mem.dao.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "commande")
public class Commande  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(updatable = true)
	private double total;
	
	@Column(updatable = true)
	private Etat etat; /* encours - terminée */
	
	@Column(updatable = true)
	private Date date_debut;
	
	@Column(updatable = true)
	private Date date_fin;
	
	@Column(updatable = true)
	private Date commande_prevu_pour;
	
	@ManyToOne
	@JoinColumn(name="FKUTILISATEUR")
	private Utilisateur utilisateur;

	@ManyToOne
	@JoinColumn(name="FKCONFECTION")
	private Confection confection;
	
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
	protected Set<Ligne> lignes = new HashSet<>();
	
	
	
	public Commande() {
		super();
	}
	
	public Commande(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
		this.total = 0;
		this.etat = Etat.EnCours;
		this.date_debut = new Date();
		this.date_fin = null;
		this.commande_prevu_pour = null;
	}
	
	public Commande(Long id, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.total = 0;
		this.etat = Etat.EnCours;
		this.date_debut = new Date();
		this.date_fin = null;
		this.commande_prevu_pour = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public Date getCommande_prevu_pour() {
		return commande_prevu_pour;
	}

	public void setCommande_prevu_pour(Date commande_prevu_pour) {
		this.commande_prevu_pour = commande_prevu_pour;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Set<Ligne> getLignes() {
		return lignes;
	}
	
	public boolean supprimerLigne(Long id) {
		Iterator<Ligne> it = this.lignes.iterator(); 
		while (it.hasNext()) {
		    Ligne s = it.next();
		    if (s.getId().getIdProduit() == id) {
		        it.remove();
		        return true;
		    }
		}
		return false;
	}
	
	public double caltulMontantTotal() {
		double totalCommande = 0;
		for (Ligne laLigne : this.getLignes()) {
			totalCommande += laLigne.getPrix();
		}
		DecimalFormat df = new DecimalFormat("########.00");
		String str = df.format(totalCommande); 
		return Double.parseDouble(str.replace(',', '.')); 
	}

	public void setLignes(Set<Ligne> lignes) {
		this.lignes = lignes;
	}
	
	public Confection getConfection() {
		return confection;
	}

	public void setConfection(Confection confection) {
		this.confection = confection;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", total=" + total + ", etat=" + etat + ", date_debut=" + date_debut
				+ ", date_fin=" + date_fin + ", commande_prevu_pour=" + commande_prevu_pour + ", utilisateur="
				+ utilisateur + ", lignes=" + lignes + "]";
	}


	public enum Etat {
	
		EnAttente("En Attente"),
		EnCours("En Cours"),
		terminee("Terminée");
		
		private String name="";
		
		Etat (String name) {
			this.name=name;
		}

		public String toString() {
			return name;
		}
	}
	
}
