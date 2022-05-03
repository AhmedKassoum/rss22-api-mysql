package univ.rouen.rss.projetrss.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.rouen.rss.projetrss.models.Content;

public interface ContentRepos extends JpaRepository<Content,Long> {
}
