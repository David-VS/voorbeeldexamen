package be.ehb.voorbeeldexamen.dao;

import be.ehb.voorbeeldexamen.entities.Bod;
import be.ehb.voorbeeldexamen.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface BodDao extends CrudRepository<Bod, Integer> {

    Iterable<Bod> findAllByProductId(Product id);
}
