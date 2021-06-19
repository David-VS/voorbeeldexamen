package be.ehb.voorbeeldexamen.dao;

import be.ehb.voorbeeldexamen.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Integer> {
}
