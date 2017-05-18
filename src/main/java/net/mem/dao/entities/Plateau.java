package net.mem.dao.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PLA")
public class Plateau extends Confection {

	private static final long serialVersionUID = 1L;
	
	private int nbPers;
	private TypePlateau typePlateau;
	private String note;
	private String typePlateauString;
	
	
	public Plateau() {
		super();
	}

	public Plateau(int nbPers, TypePlateau typePlateau, String note, double montant) {
		super(montant,"Plateau");
		this.nbPers = nbPers;
		this.typePlateau = typePlateau;
		this.note = note;
	}
	
	public int getNbPers() {
		return nbPers;
	}

	public void setNbPers(int nbPers) {
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
		return "Plateau [nbPers=" + nbPers + ", typePlateau=" + typePlateau + ", note=" + note + "]";
	}


	public enum TypePlateau {
		Apero("Apéro"),
		Repas("Repas"),
		ApresRepas("Après repas");
		
		private String name="";
		
		private TypePlateau(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
		public static TypePlateau getEnum(String code) {

		    switch (code) {
		        case "Apéro":
		            return Apero;
		        case "Repas":
		            return Repas;
		        case "Après repas":
		            return ApresRepas;
		        default:
		            return null;
		     }
		   }

		public String toString() {
			return name;
		}
	}
}
