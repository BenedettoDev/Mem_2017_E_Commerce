package net.mem.dao.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CONF",discriminatorType = DiscriminatorType.STRING,length=3)
public abstract class Confection implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private double montant;
	private String type;
	
	
	public Confection() {
		super();
	}


	public Confection(double montant,String type) {
		super();
		this.montant = montant;
		this.type=type;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}
