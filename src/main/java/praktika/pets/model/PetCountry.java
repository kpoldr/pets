package praktika.pets.model;

import jakarta.persistence.*;


@Entity
@Table(name = "pet_country")
public class PetCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public PetCountry(String name) {
        this.name = name;
    }

    public PetCountry() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getters and setters for the fields
}