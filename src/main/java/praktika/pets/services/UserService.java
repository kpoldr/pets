package praktika.pets.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import praktika.pets.model.AppUser;
import praktika.pets.model.Role;
import praktika.pets.repositories.RoleRepository;
import praktika.pets.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<AppUser> findUsers() {

        return userRepository.findAll();

    }

    public boolean userExists(String name) {

        return userRepository.existsByUsername(name);

    }

    public AppUser findUserByName(String name) {

        return userRepository.findByUsername(name);

    }

    public AppUser insertUser(AppUser user) {

        return userRepository.save(user);
    }

    public Role insertRole(Role role) {

        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {

        AppUser user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }
}
