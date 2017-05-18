package net.mem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mem.dao.entities.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {

}
