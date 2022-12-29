package praktika.pets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import praktika.pets.api.model.ApiPet;
import praktika.pets.model.*;
import praktika.pets.services.PetExtraService;
import praktika.pets.services.PetService;
import praktika.pets.services.UserService;

import java.util.ArrayList;


@SpringBootApplication
public class PetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, PetExtraService petExtraService, PetService petService) {

		return args -> {

			userService.insertRole(new Role(null, "ROLE_USER", new ArrayList<>()));
			userService.insertRole(new Role(null, "ROLE_ADMIN", new ArrayList<>()));

			userService.insertUser(new AppUser(null, "username", "password", new ArrayList<>(), new ArrayList<>()));
			userService.insertUser(new AppUser(null, "testuser", "password", new ArrayList<>(), new ArrayList<>()));
			userService.insertUser(new AppUser(null, "admin", "admin", new ArrayList<>(), new ArrayList<>()));

			userService.addRoleToUser("username", "ROLE_USER");
			userService.addRoleToUser("testuser", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");

			petExtraService.insertPetType(new PetType(null, "Dog",  new ArrayList<>()));
			petExtraService.insertPetType(new PetType(null, "Cat",  new ArrayList<>()));
			petExtraService.insertPetType(new PetType(null, "Bird", new ArrayList<>()));
			petExtraService.insertPetType(new PetType(null, "Lizard", new ArrayList<>()));
			petExtraService.insertPetType(new PetType(null, "Fish", new ArrayList<>()));

			petExtraService.insertPetColor(new PetColor(null, "White",new ArrayList<>()));
			petExtraService.insertPetColor(new PetColor(null, "Black", new ArrayList<>()));
			petExtraService.insertPetColor(new PetColor(null, "Brown", new ArrayList<>()));
			petExtraService.insertPetColor(new PetColor(null, "Red", new ArrayList<>()));
			petExtraService.insertPetColor(new PetColor(null, "Yellow", new ArrayList<>()));

			petExtraService.insertPetCountry(new PetCountry(null, "Finland", new ArrayList<>()));
			petExtraService.insertPetCountry(new PetCountry(null, "Latvia", new ArrayList<>()));
			petExtraService.insertPetCountry(new PetCountry(null, "Germany", new ArrayList<>()));
			petExtraService.insertPetCountry(new PetCountry(null, "Estonia", new ArrayList<>()));
			petExtraService.insertPetCountry(new PetCountry(null, "Sweden", new ArrayList<>()));

			petService.insertPet(new ApiPet(null, "dogger", "1234", "Black", "Finland", "Dog"),"admin");

		};
	}
}
