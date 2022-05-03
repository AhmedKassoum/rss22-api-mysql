package univ.rouen.rss.projetrss.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.rouen.rss.projetrss.models.Item;

public interface ItemRepos extends JpaRepository<Item,Long> {
    Item findItemByGuid(String guid);
    void deleteItemByGuid(String guid);
}
