package praktika.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praktika.pets.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
