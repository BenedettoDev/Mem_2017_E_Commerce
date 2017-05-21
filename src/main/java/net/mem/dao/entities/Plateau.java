package net.mem.dao.entities;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@DiscriminatorValue("PLA")
public class Plateau extends Confection {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Digits(fraction = 0, integer = 3)
	@Min(value=4,message = "Le champ  nombre de personnes est incorrect. Il doit contenir au minimum 4 personnes")
	private Integer nbPers;
	private TypePlateau typePlateau;
	@Column(columnDefinition = "TEXT")
	private String note;
	@NotEmpty
	private String typePlateauString;
	
	
	public Plateau() {
		super();
	}

	public Plateau(Integer nbPers, TypePlateau typePlateau, String note, double montant) {
		super(montant,"Plateau");
		this.nbPers = nbPers;
		this.typePlateau = typePlateau;
		this.note = note;
	}
	


	public Integer getNbPers() {
		return nbPers;
	}

	public void setNbPers(Integer nbPers) {
		this.nbPers = nbPers;
	}

	public TypePlateau getTypePlateau() {
		return typePlateau;
	}

	public void setTypePlateau(TypePlateau typePlateau) {
		this.typePlateau = typePlateau;
	}
	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getTypePlateauString() {
		return typePlateauString;
	}

	public void setTypePlateauString(String typePlateauString) {

		this.typePlateauString = typePlateauString;
	
	}


	@Override
	public String toString() {
		return "Plateau [nbPers=" + nbPers + ", typePlateau=" + typePlateau + ", note=" + note + ", typePlateauString="
				+ typePlateauString + "]";
	}




	public enum TypePlateau {
		Apero(0,"Apéro",5),
		Repas(1,"Repas",10),
		ApresRepas(2,"Après repas",5);
		
		private String name="";
		private int index;
		private int prix;
		
		private TypePlateau(int index,String name,int prix) {
			this.name = name;
			this.index=index;
			this.prix = prix;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
	            	this.index = index;
		}
		

		public int getPrix() {
			return prix;
		}

		public void setPrix(int prix) {
			this.prix = prix;
		}

		public static TypePlateau getEnum(String code) {
			for (TypePlateau status :TypePlateau.values()){
	            if (status.getName().equals(code)){
	                return status;
	            }
	        }
			return null;
		  }

		public String toString() {
			return '1'+name;
		}
	}
}
