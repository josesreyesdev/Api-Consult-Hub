package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.physician.PhysicianRepository;

public class ActivePhysician {
    // no permitir hacer citas con medicos inactivos


    private PhysicianRepository physicianRepository;

    public void validate(AddScheduleConsultData data) {
        if (data.idPhysician() == null) {
            return;
        }

        var isActivePhysician = physicianRepository.findActiveById(data.idPhysician());

        if (!isActivePhysician) {
            throw new ValidationException("No está permitido programar citas con médicos inactivos en el sistema");
        }
    }
}
