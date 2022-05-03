package univ.rouen.rss.projetrss.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.rouen.rss.projetrss.models.Author;

public interface AuthorRepos extends JpaRepository<Author,Long> {
}
