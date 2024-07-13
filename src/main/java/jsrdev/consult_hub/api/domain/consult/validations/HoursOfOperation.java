package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;

import java.time.DayOfWeek;

public class HoursOfOperation {

    public void validate(AddScheduleConsultData data) {
        //verificar que no sea  domingo y solo poder hacer consultas de lunes a sabado de 7 am a 7 pm

        //domingo
        var sunday = DayOfWeek.SUNDAY.equals(data.date().getDayOfWeek());

        //hora de abrir
        var openingTime = data.date().getHour() < 7;

        //hora de cierre
        var closingTime = data.date().getHour() > 19;

        if (sunday || openingTime || closingTime) {
            throw new ValidationException("Horario de atención es de lunes a sábado de 07:00 a 19:00 horas");
        }
    }
}
