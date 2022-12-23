package praktika.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praktika.pets.model.PetCountry;

@Repository
public interface PetCountryRepository extends JpaRepository<PetCountry, Long> {

    long deleteByName(String name);
}
