package praktika.pets.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import praktika.pets.api.model.PetExtraInfo;
import praktika.pets.model.PetColor;
import praktika.pets.model.PetCountry;
import praktika.pets.model.PetType;
import praktika.pets.repositories.PetColorRepository;
import praktika.pets.repositories.PetCountryRepository;
import praktika.pets.repositories.PetTypeRepository;

import java.util.List;

@Service
public class PetExtraService {

    private final PetColorRepository petColorRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetCountryRepository petCountryRepository;

    @Autowired
    public PetExtraService(PetColorRepository petColorRepository, PetTypeRepository petTypeRepository, PetCountryRepository petCountryRepository) {
        this.petColorRepository = petColorRepository;
        this.petTypeRepository = petTypeRepository;
        this.petCountryRepository = petCountryRepository;
    }

    public PetExtraInfo findPetExtraInfo() {

        List<PetColor> colors = petColorRepository.findAll();
        List<PetType> types = petTypeRepository.findAll();
        List<PetCountry> country = petCountryRepository.findAll();

        return new PetExtraInfo(colors, types, country);

    }

    public PetColor insertPetColor(PetColor petColor) {
        return petColorRepository.save(petColor);
    }

    public PetCountry insertPetCountry(PetCountry petCountry) {
        return petCountryRepository.save(petCountry);
    }

    public PetType insertPetType(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Transactional
    public void deletePetColor(String color) {
        petColorRepository.deleteByName(color);
    }

    @Transactional
    public void deletePetCountry(String country) {

        petColorRepository.deleteByName(country);
    }

    @Transactional
    public void deletePetType(String type) {
        petColorRepository.deleteByName(type);
    }

}
