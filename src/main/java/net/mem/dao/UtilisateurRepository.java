package net.mem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.mem.dao.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

	@Query("SELECT U FROM Utilisateur U WHERE U.username = ?")
	Utilisateur findByUserName(String name);

}
