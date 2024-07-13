package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.patient.PatientRepository;

public class ActivePatient {
    // no permitir hacer citas a pacientes inactivos

    private PatientRepository patientRepository;

    public void validate(AddScheduleConsultData data) {
        if (data.idPatient() == null) {
            return;
        }

        var isActivePatient = patientRepository.findActiveByID(data.idPatient());

        if (!isActivePatient) {
            throw new ValidationException("No está permitido agendar citas con pacientes inactivos en el sistema");
        }
    }
}
