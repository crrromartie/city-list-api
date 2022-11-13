package pl.solberg.cities.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.solberg.cities.entity.City;
import pl.solberg.cities.enums.CityFields;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Repository
public class CityRepository implements ICityRepository {

    private static final String SELECT_CITY = "SELECT c FROM City c";
    private static final String SELECT_CITY_BY_NAME = "SELECT c FROM City c WHERE c.name = :name";

    private final EntityManager entityManager;

    @Override
    public void save(City city) {
        entityManager.persist(city);
    }

    @Override
    public City update(City city) {
        return entityManager.merge(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return ofNullable(entityManager.find(City.class, id));
    }

    @Override
    public List<City> findByName(String name) {
        return entityManager.createQuery(SELECT_CITY_BY_NAME, City.class)
                .setParameter(CityFields.NAME.getCode(), name)
                .getResultList();
    }

    @Override
    public List<City> findPerPage(int page, int size) {
        return entityManager.createQuery(SELECT_CITY, City.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
