package praktika.pets.validation;


import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationErrors {

    @Getter
    private List<ValidationError> errors = new ArrayList<>();

    public void addErrorMessage(String message) {
        errors.add(new ValidationError(message));
    }

    public void addFieldError(FieldError fieldError) {
        List<String> args = Stream.of(fieldError.getArguments())
                .filter(arg -> !(arg instanceof DefaultMessageSourceResolvable))
                .map(String::valueOf)
                .collect(Collectors.toList());

        errors.add(new ValidationError(fieldError.getCodes()[0], args));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}
