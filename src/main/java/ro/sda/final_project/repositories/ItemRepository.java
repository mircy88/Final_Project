package ro.sda.final_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.final_project.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
