package praktika.pets.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import praktika.pets.api.model.RoleToUserForm;
import praktika.pets.model.AppUser;
import praktika.pets.model.Role;
import praktika.pets.services.UserService;

import java.net.URI;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

//    @PostMapping("login")
//    public String Login(@RequestBody AppUser user) {
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        return tokenService.generateToken(authenticate);
//    }

    @PostMapping("register")
    public ResponseEntity<AppUser> Register(@RequestBody AppUser user) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body(userService.insertUser(user));

    }

    @PostMapping("role/add")
    public ResponseEntity<Role> Register(@RequestBody Role role) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body(userService.insertRole(role));

    }

    @PostMapping("role/addtouser")
    public ResponseEntity<?> Register(@RequestBody RoleToUserForm form) {

        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();

    }

}
