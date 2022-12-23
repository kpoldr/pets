package praktika.pets.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import praktika.pets.model.Pet;
import praktika.pets.services.PetService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(path="pet")
    public Pet insertPet(@RequestBody @Valid Pet pet)
    {
        return petService.insertPet(pet);
    }

    @GetMapping("pet/{id}")
    public Optional<Pet> getById(@PathVariable Long id) {

        return petService.findPetId(id);
    }

    @GetMapping("pets")
    public List<Pet> getPets() {
        return petService.findPets();
    }

}
