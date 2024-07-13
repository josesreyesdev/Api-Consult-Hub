package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;

import java.time.Duration;
import java.time.LocalDateTime;

public class AnticipationTime {

    public void validate(AddScheduleConsultData data) {
        // Horario de anticipacion, no puede ser en menos de 30 min la consulta

        // hora actual
        var currentTime = LocalDateTime.now();

        // Hora de consulta
        var consultationTime = data.date();

        // 30 minutos de diferencia?
        var is30minutesDifference = Duration.between(currentTime, consultationTime).toMinutes() < 30;

        if (is30minutesDifference) {
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipación");
        }
    }
}
