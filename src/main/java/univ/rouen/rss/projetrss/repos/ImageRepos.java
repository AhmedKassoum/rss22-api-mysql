package univ.rouen.rss.projetrss.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.rouen.rss.projetrss.models.Image;

public interface ImageRepos extends JpaRepository<Image,Long> {
}
