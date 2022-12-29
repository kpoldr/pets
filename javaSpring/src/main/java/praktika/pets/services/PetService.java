package praktika.pets.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import praktika.pets.api.model.ApiPet;
import praktika.pets.model.*;
import praktika.pets.repositories.*;
import praktika.pets.security.JWTHelper;
import praktika.pets.validation.BadRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetCountryRepository petCountryRepository;
    private final PetColorRepository petColorRepository;
    private final UserRepository userRepository;

    @Autowired
    public PetService(PetRepository petRepository, PetTypeRepository petTypeRepository, PetCountryRepository petCountryRepository, PetColorRepository petColorRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
        this.petCountryRepository = petCountryRepository;
        this.petColorRepository = petColorRepository;
        this.userRepository = userRepository;
    }

    public List<Pet> findPets() {

        return petRepository.findAll();

    }

    public List<ApiPet> findUserPets(String username) {

        AppUser user = userRepository.findByUsername(username);

        List<ApiPet> apiPets = new ArrayList<>();
        List<Pet> userPets = petRepository.findAllByAppUserId(user.getId());

        for (Pet pet : userPets) {

            apiPets.add(new ApiPet(
                    pet.getId(),
                    pet.getName(),
                    pet.getIdCode(),
                    pet.getColor().getName(),
                    pet.getCountry().getName(),
                    pet.getType().getName()));

        }

        return apiPets;
    }

    public ApiPet findPetId(Long id, HttpServletRequest request) throws BadRequestException {

        Optional<Pet> optionalPet = petRepository.findById(id);
        ApiPet apiPet = new ApiPet();
        if (optionalPet.isPresent()) {

            Pet pet = optionalPet.get();

            String jwtUsername = JWTHelper.VerifyToken(request);

            if (!Objects.equals(jwtUsername, pet.getAppUser().getUsername())) {

                throw new BadRequestException("JWT and username don't match");
            }

            apiPet.setId(pet.getId());
            apiPet.setName(pet.getName());
            apiPet.setIdCode(pet.getIdCode());
            apiPet.setType(pet.getType().getName());
            apiPet.setColor(pet.getColor().getName());
            apiPet.setCountry(pet.getCountry().getName());

            return apiPet;
        }

        return null;

    }

    public void deletePet(Long id, HttpServletRequest request) throws BadRequestException {

        Optional<Pet> pet = petRepository.findById(id);

        if (pet.isPresent()) {

            String jwtUsername = JWTHelper.VerifyToken(request);

            if (!Objects.equals(jwtUsername, pet.get().getAppUser().getUsername())) {

                throw new BadRequestException("JWT and username don't match");
            }

        }

        petRepository.deleteById(id);

    }

    public Pet UpdatePet(ApiPet apiPet, Long id, HttpServletRequest request) throws BadRequestException {


        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {

            Pet pet = optionalPet.get();


            String jwtUsername = JWTHelper.VerifyToken(request);

            if (!Objects.equals(jwtUsername, pet.getAppUser().getUsername())) {

                throw new BadRequestException("JWT and username don't match");
            }

            PetType petType = petTypeRepository.findByName(apiPet.getType());
            PetCountry petCountry = petCountryRepository.findByName(apiPet.getCountry());
            PetColor petColor = petColorRepository.findByName(apiPet.getColor());

            pet.setName(apiPet.getName());
            pet.setIdCode(apiPet.getIdCode());
            pet.setType(petType);
            pet.setColor(petColor);
            pet.setCountry(petCountry);

            return pet;
        }

        return null;
    }

    public Pet insertPet(ApiPet pet, String username) {

        PetType petType = petTypeRepository.findByName(pet.getType());
        PetCountry petCountry = petCountryRepository.findByName(pet.getCountry());
        PetColor petColor = petColorRepository.findByName(pet.getColor());
        AppUser appUser = userRepository.findByUsername(username);

        Pet petToSave = new Pet(pet.getName(), pet.getIdCode());

        petToSave.setAppUser(appUser);
        petToSave.setCountry(petCountry);
        petToSave.setColor(petColor);
        petToSave.setType(petType);

        petRepository.save(petToSave);

        return petToSave;
    }

}
