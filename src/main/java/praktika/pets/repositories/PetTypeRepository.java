package praktika.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praktika.pets.model.PetType;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {

    long deleteByName(String name);
}
