package praktika.pets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import praktika.pets.model.AppUser;
import praktika.pets.model.Role;
import praktika.pets.services.UserService;

import java.util.ArrayList;


@SpringBootApplication
public class PetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {

		return args -> {

			userService.insertRole(new Role(null, "ROLE_USER"));
			userService.insertRole(new Role(null, "ROLE_ADMIN"));
			userService.insertUser(new AppUser(null, "user1", "password", new ArrayList<>(), new ArrayList<>()));
			userService.insertUser(new AppUser(null, "admin", "admin", new ArrayList<>(), new ArrayList<>()));
//
			userService.addRoleToUser("user1", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");
		};
	}
}
