package net.mem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.mem.dao.entities.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {

	@Query (value="SELECT A.* FROM Article A WHERE A.is_Visible = 1",nativeQuery= true)
	List<Article> findArticleVisible();

}
