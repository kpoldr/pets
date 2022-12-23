package praktika.pets.model;

import jakarta.persistence.*;


@Entity
@Table(name = "pet_color")
public class PetColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public PetColor(String name) {
        this.name = name;
    }

    public PetColor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getters and setters for the fields
}