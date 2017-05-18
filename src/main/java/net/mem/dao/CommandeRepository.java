package net.mem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import net.mem.dao.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	@Query(value ="SELECT C.* FROM Commande C,Utilisateur U WHERE C.FKUTILISATEUR = U.username and U.username = ? AND C.etat=0 ORDER BY C.id DESC LIMIT 1",nativeQuery = true)
	public Commande chargerDerniereCommande(String  username);

	 @Modifying
	@Transactional
	@Query(value="DELETE FROM Ligne WHERE id_commande = ?1 AND id_produit = ?2 ")
	public void supprimerLaLigne(Long id_commande,Long id_produit);

	 @Query (value="SELECT C.* FROM Commande C WHERE C.etat = 1 AND C.commande_prevu_pour = CURDATE()",nativeQuery= true)
	public List<Commande> commandeDuJour();

	 @Query (value="SELECT C.* FROM Commande C WHERE C.etat = 1 ORDER BY commande_prevu_pour ASC",nativeQuery= true)
	public List<Commande> commandesSemaine();

	 @Query (value="SELECT C.* FROM Commande C WHERE C.etat = 2  ORDER BY C.date_fin ASC ",nativeQuery = true)
	public List<Commande> chargerCommandeTermine();

	 @Query (value="SELECT C.* FROM Commande C WHERE C.fkutilisateur = ? and C.etat = 1  ORDER BY C.date_fin ASC ",nativeQuery = true)
	public List<Commande> chargerCommandeEnAttente(String name);
	 
	 @Query (value="SELECT C.* FROM Commande C WHERE C.fkutilisateur = ? and C.etat = 2  ORDER BY C.date_fin ASC ",nativeQuery = true)
		public List<Commande> chargerCommandeTermine(String name);
}
