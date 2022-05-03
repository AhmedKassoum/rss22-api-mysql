package univ.rouen.rss.projetrss.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.rouen.rss.projetrss.models.Category;

public interface CategoryRepos extends JpaRepository<Category,Long> {
}
