package praktika.pets.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiPet {

    private Long id;

    @Size(min = 1, max = 32)
    @Column(nullable = false)
    private String name;

    @Size(min = 1, max = 16)
    @Column(nullable = false)
    private String idCode;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String type;

}
