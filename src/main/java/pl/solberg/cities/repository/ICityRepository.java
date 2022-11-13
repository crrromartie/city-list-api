package pl.solberg.cities.repository;

import pl.solberg.cities.entity.City;

import java.util.List;
import java.util.Optional;

public interface ICityRepository {

    void save(City city);

    City update(City city);

    Optional<City> findById(Long id);

    List<City> findByName(String name);

    List<City> findPerPage(int page, int size);
}
