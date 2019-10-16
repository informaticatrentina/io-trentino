package it.tndigit.iot.web.validator;

import it.tndigit.iot.service.dto.EnteDTO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class EnteValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return EnteDTO.class.equals(aClass);
    }


    @Override
    public void validate(Object target, Errors errors) {


        EnteDTO enteDTO = (EnteDTO) target;








    }
}
