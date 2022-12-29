package praktika.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praktika.pets.model.PetCountry;
import praktika.pets.model.PetType;

@Repository
public interface PetCountryRepository extends JpaRepository<PetCountry, Long> {

    PetCountry findByName(String name);
    Long deleteByName(String name);
}
