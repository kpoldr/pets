package praktika.pets.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponse {

    String username;
    String accessToken;
    String refreshToken;


}
