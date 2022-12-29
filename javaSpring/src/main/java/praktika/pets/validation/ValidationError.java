package praktika.pets.validation;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor

public class ValidationError {

    @NonNull
    private String code;

    private List<String> arguments;

}
