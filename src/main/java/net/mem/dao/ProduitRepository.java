package net.mem.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.mem.dao.entities.Produit;


public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	public List<Produit> findByLabel(String l);
	public Page<Produit> findByLabel(String l,Pageable pageable);
	@Query("select e from Produit e where e.label like :x")
	public Page<Produit> chercherProduits(@Param("x")String mc,Pageable pageable);
	@Query("select e from Produit e,Categorie c where e.categorie=c.id and c.nom = :cat ")
	public Page<Produit> chercherProduitsByCategory(@Param("cat")String categorie, Pageable pageable);
	
	@Query("Select e from Produit e ")
	public List<Produit> derniersProduitsAjoute();
	
	@Query("SELECT e FROM Produit e WHERE e.suggestion=1")
	public List<Produit> produitsEnSuggestion();
	
	@Query("SELECT e FROM Produit e WHERE e.preparation=1")
	public List<Produit> produitsPreparation();
}

