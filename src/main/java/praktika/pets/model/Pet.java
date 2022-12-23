package praktika.pets.model;


import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String idCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @OneToOne
    @JoinColumn(name = "pet_color_id")
    private PetColor color;

    @OneToOne
    @JoinColumn(name = "pet_country_id")
    private PetCountry country;

    @OneToOne
    @JoinColumn(name = "pet_type_id")
    private PetType type;

    public Pet(String name, String idCode, AppUser user, PetColor color, PetCountry country, PetType type) {
        this.name = name;
        this.idCode = idCode;
        this.user = user;
        this.color = color;
        this.country = country;
        this.type = type;
    }

    public Pet() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public PetColor getColor() {
        return color;
    }

    public void setColor(PetColor color) {
        this.color = color;
    }

    public PetCountry getCountry() {
        return country;
    }

    public void setCountry(PetCountry country) {
        this.country = country;
    }
}
