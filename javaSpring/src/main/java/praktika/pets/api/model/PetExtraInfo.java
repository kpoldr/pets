package praktika.pets.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import praktika.pets.model.PetColor;
import praktika.pets.model.PetCountry;
import praktika.pets.model.PetType;

import java.util.List;

@Data
@AllArgsConstructor
public class PetExtraInfo {

    public List<PetColor> colors;
    public List<PetType> types;
    public List<PetCountry> countries;



}
