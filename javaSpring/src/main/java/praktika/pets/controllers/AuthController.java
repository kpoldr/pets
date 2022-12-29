package praktika.pets.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import praktika.pets.api.model.JWTResponse;
import praktika.pets.api.model.RoleToUserForm;
import praktika.pets.model.AppUser;
import praktika.pets.model.Role;
import praktika.pets.services.UserService;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<AppUser> Register(@RequestBody AppUser user) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body(userService.insertUser(user));

    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> Register(@RequestBody Role role) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body(userService.insertRole(role));

    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> Register(@RequestBody RoleToUserForm form) {

        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/token/refresh")
    public void RefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refreshToken = authorizationHeader.substring("Bearer ". length());

                Algorithm algorithm = Algorithm.HMAC256("aaaa".getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();

                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String username = decodedJWT.getSubject();

                AppUser user = userService.findUserByName(username);

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                JWTResponse jwtResponse = new JWTResponse(user.getUsername(), accessToken, refreshToken);

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), jwtResponse);


            } catch (Exception exception) {

                response.setHeader("error", exception.getMessage());
//                        response.sendError(FORBIDDEN.value());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage() );
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }


        } else {
            throw new RuntimeException("Refresh token is missing");
        }

    }

}
