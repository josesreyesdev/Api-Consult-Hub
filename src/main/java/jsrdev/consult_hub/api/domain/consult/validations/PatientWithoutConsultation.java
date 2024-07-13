package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.consult.ConsultRepository;

public class PatientWithoutConsultation {
    //patient sin consultas

    ConsultRepository consultRepository;

    public void validate(AddScheduleConsultData data) {
        var firstSchedule = data.date().withHour(7);
        var lastSchedule = data.date().withHour(18);

        var patientWithConsult = consultRepository
                .existsByPatientIdAndDateBetween(data.idPatient(), firstSchedule, lastSchedule);

        if (patientWithConsult) {
            throw new ValidationException("El paciente ya tiene consulta activa para este día");
        }
    }
}
