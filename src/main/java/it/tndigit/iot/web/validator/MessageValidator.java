package it.tndigit.iot.web.validator;

import it.tndigit.iot.service.dto.message.MessageDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class MessageValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MessageDTO.class.equals(aClass);
    }


    @Override
    public void validate(Object target, Errors errors) {


        MessageDTO messageDTO = (MessageDTO) target;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "messages.codiceFiscale.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoMessage", "messages.tipoMessage.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oggetto", "messages.oggetto.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "testo", "messages.testo.empty");


        //Effettuo la validazione della lunghezza dei campi

    }


    public void validate(Object target, Errors errors, Boolean withPayment) {

        MessageDTO messageDTO = (MessageDTO) target;
        this.validate(target,errors);

        if (withPayment){
            //Inserire la validazione

        }


    }




}
