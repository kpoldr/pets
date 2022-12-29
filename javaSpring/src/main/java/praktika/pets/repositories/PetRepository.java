package praktika.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import praktika.pets.model.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByAppUserId(Long id);

    // Custom delete because of eager fetching
    @Modifying
    @Query("delete from Pet p where p.id = ?1")
    void deleteById(Long id);

}
