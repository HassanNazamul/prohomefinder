package prohomefinder.prohomefinder.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import prohomefinder.prohomefinder.model.House;

public interface HomeRepository extends CrudRepository<House, Integer> {

    Optional<House> findByHouseid(int houseid);

    void deleteByHouseid(int houseid);
}
