package praktika.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praktika.pets.model.PetColor;

@Repository
public interface PetColorRepository extends JpaRepository<PetColor, Long> {

    long deleteByName(String name);

}
