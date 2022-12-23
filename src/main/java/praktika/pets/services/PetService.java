package praktika.pets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import praktika.pets.model.Pet;
import praktika.pets.repositories.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository userRepository) {
        this.petRepository = userRepository;
    }

    public List<Pet> findPets() {

        return petRepository.findAll();

    }

    public Optional<Pet> findPetId(Long id) {

        return petRepository.findById(id);

    }

    public Pet insertPet(Pet pet) {

        return petRepository.save(pet);
    }

}
