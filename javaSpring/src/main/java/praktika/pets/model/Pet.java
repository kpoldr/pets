package praktika.pets.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pet")
public class Pet {

    public Pet(String name, String idCode) {
        this.name = name;
        this.idCode = idCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "id_code", nullable = false)
    private String idCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "pet_color_id",  referencedColumnName = "id", nullable = false)
    private PetColor color;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "pet_country_id",  referencedColumnName = "id", nullable = false )
    private PetCountry country;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "pet_type_id",  referencedColumnName = "id", nullable = false)
    private PetType type;

}
