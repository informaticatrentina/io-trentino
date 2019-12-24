package it.tndigit.iot.web.validator;

import it.tndigit.iot.service.dto.ServizioDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ServizioValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ServizioDTO.class.equals(aClass);
    }


    @Override
    public void validate(Object target, Errors errors) {


        ServizioDTO enteDTO = (ServizioDTO) target;








    }
}
