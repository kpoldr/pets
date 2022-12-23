package praktika.pets.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import praktika.pets.model.AppUser;
import praktika.pets.services.UserService;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="users")
    public AppUser insertUser(@RequestBody @Valid AppUser user)
    {
        return userService.insertUser(user);
    }

//    @GetMapping("users/{id}")
//    public Optional<User> getById(@PathVariable Long id) {
//
//        return userService.(id);
//    }

    @GetMapping("users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(userService.findUsers());
    }

}
