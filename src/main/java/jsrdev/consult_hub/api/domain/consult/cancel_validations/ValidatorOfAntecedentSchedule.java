package jsrdev.consult_hub.api.domain.consult.cancel_validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.CancelConsultationData;
import jsrdev.consult_hub.api.domain.consult.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorOfAntecedentSchedule implements CancellationConsultationValidator{

    @Autowired
    private ConsultRepository consultRepository;

    @Override
    public void validate(CancelConsultationData data) {

        var consult = consultRepository.getReferenceById(data.idConsult());
        var currentTime = LocalDateTime.now();

        var differenceInHours = Duration.between(currentTime, consult.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("La consulta solo puede ser cancelada con antecedencia minima de 24 hrs");
        }

    }
}
