package praktika.pets.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import praktika.pets.api.model.ApiPet;
import praktika.pets.model.Pet;
import praktika.pets.security.JWTHelper;
import praktika.pets.services.PetService;
import praktika.pets.validation.BadRequestException;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(path = "api/")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(path="pet/{username}")
    public Pet insertPet(@PathVariable String username, @RequestBody @Valid ApiPet pet, HttpServletRequest request )
    {

        String jwtUsername = JWTHelper.VerifyToken(request);

        if (Objects.equals(jwtUsername, username)) {

            return petService.insertPet(pet, username);
        } else {

            throw new BadRequestException("JWT and username don't match");
        }

    }

    @PutMapping(path="pet/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody @Valid ApiPet apiPet, HttpServletRequest request )
    {

        Pet pet = petService.UpdatePet(apiPet, id, request);

        if (pet == null) {

            throw new BadRequestException("Pet doesn't exist ");
        }

        return pet;
    }

    @DeleteMapping(path="pet/{id}")
    public void deletePet(@PathVariable Long id, HttpServletRequest request)
    {
        petService.deletePet(id, request);
    }



    @GetMapping("pet/{id}")
    public ApiPet getById(@PathVariable Long id, HttpServletRequest request) {

        ApiPet apiPet = petService.findPetId(id, request);

        if (apiPet == null) {

            throw new BadRequestException("Pet doesn't exist");

        }

        return apiPet;
    }

    @GetMapping("pets")
    public List<Pet> getPets() {
        return petService.findPets();
    }

    @GetMapping("pets/{username}")
    public ResponseEntity<List<ApiPet>> getUserPets(@PathVariable String username, HttpServletRequest request ) throws ValidationException {


        String jwtUsername = JWTHelper.VerifyToken(request);

        if (Objects.equals(jwtUsername, username)) {

            return ResponseEntity.ok().body(petService.findUserPets(username));
        } else {

            throw new BadRequestException("JWT and username don't match");
        }



    }

}
