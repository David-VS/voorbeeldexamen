package be.ehb.voorbeeldexamen.dao;

import be.ehb.voorbeeldexamen.entities.Persoon;
import org.springframework.data.repository.CrudRepository;

public interface PersoonDao extends CrudRepository<Persoon, Integer> {
}
