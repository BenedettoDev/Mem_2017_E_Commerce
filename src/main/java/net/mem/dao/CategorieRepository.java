package net.mem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mem.dao.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long>  {
	public List<Categorie> findByNom(String l);
}
