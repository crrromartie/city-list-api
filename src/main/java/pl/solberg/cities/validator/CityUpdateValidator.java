package pl.solberg.cities.validator;

import pl.solberg.cities.dto.CityUpdateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class CityUpdateValidator implements ConstraintValidator<NameOrPhotoNotBlank, CityUpdateDto> {

    @Override
    public boolean isValid(CityUpdateDto value, ConstraintValidatorContext context) {
        return !isBlank(value.getName()) || !isBlank(value.getPhoto());
    }
}
