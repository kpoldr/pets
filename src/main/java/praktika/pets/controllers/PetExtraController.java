package praktika.pets.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import praktika.pets.api.model.PetExtraInfo;
import praktika.pets.model.PetColor;
import praktika.pets.model.PetCountry;
import praktika.pets.model.PetType;
import praktika.pets.services.PetExtraService;

@CrossOrigin
@RestController
@RequestMapping(path = "api/")
public class PetExtraController {

    private final PetExtraService petExtraService;

    @Autowired
    public PetExtraController(PetExtraService petExtraService) {
        this.petExtraService = petExtraService;
    }

    @GetMapping("pet/extra")
    public PetExtraInfo getPets() {
        return petExtraService.findPetExtraInfo();
    }

    @PostMapping(path="pet/color")
    public PetColor insertPetColor(@RequestBody @Valid PetColor petColor)
    {
        return petExtraService.insertPetColor(petColor);
    }

    @PostMapping(path="pet/country")
    public PetCountry insertPetCountry(@RequestBody @Valid PetCountry petCountry)
    {
        return petExtraService.insertPetCountry(petCountry);
    }

    @PostMapping(path="pet/type")
    public PetType insertPetType(@RequestBody @Valid PetType petType)
    {
        return petExtraService.insertPetType(petType);
    }

    @DeleteMapping(path="pet/color/{color}")
    public void deletePetColor(@PathVariable String color)
    {
        petExtraService.deletePetColor(color);
    }

    @DeleteMapping(path="pet/country/{country}")
    public void deletePetCountry(@PathVariable String country)
    {

        petExtraService.deletePetCountry(country);
    }

    @DeleteMapping(path="pet/type/{type}")
    public void deletePetType(@PathVariable String type)
    {
        petExtraService.deletePetType(type);
    }

}
