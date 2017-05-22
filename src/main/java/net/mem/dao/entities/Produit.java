package net.mem.dao.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String label;
	
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="FK_lieu")
	private Lieu lieu;
	
	@DecimalMin(value="0.50")
	private double prix;

	@Size(max=10)
	private String unite;
	private String img;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="FK_categorie")
	private Categorie categorie;

	private boolean suggestion;
		
	private boolean preparation;
	
	private boolean morceau;
	
	private boolean tranche;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="produit_allergene",
		joinColumns = @JoinColumn(name = "produit_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "allergene_id", referencedColumnName = "id"))
	protected Set<Allergene> allergenes = new HashSet<>();
	
	
	public Produit() {
		super();
	}

	public Produit( String label, String nom, Lieu lieu, double prix, String unite, String photo,
			String description) {
		super();
		this.label = label;
		this.nom = nom;
		this.lieu = lieu;
		this.prix = prix;
		this.unite = unite;
		this.img = photo;
		this.description = description;
	}
	public Produit( String label, String nom, Lieu lieu, double prix, String unite, String photo,
			String description,Categorie categorie) {
		this(label,nom,lieu,prix,unite,photo,description);
		this.categorie=categorie;
	}
	
	public Produit(Long id , String label, String nom, Lieu lieu, double prix, String unite, String photo,
			String description,Categorie categorie) {
		this(label,nom,lieu,prix,unite,photo,description);
		this.categorie=categorie;
		this.id = id;
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
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Lieu getLieu() {
		return lieu;
	}
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public boolean isSuggestion() {
		return suggestion;
	}

	public void setSuggestion(boolean suggestion) {
		this.suggestion = suggestion;
	}

	public Set<Allergene> getAllergenes() {
		return allergenes;
	}

	public void setAllergenes(Set<Allergene> allergenes) {
		this.allergenes = allergenes;
	}
	

	public boolean isPreparation() {
		return preparation;
	}

	public void setPreparation(boolean preparation) {
		this.preparation = preparation;
	}
	

	public boolean isMorceau() {
		return morceau;
	}

	public void setMorceau(boolean morceau) {
		this.morceau = morceau;
	}

	public boolean isTranche() {
		return tranche;
	}

	public void setTranche(boolean tranche) {
		this.tranche = tranche;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", label=" + label + ", nom=" + nom + ", lieu=" + lieu + ", prix=" + prix
				+ ", unite=" + unite + ", img=" + img + ", description=" + description + ", categorie=" + categorie
				+ "]";
	}
	
	
}
