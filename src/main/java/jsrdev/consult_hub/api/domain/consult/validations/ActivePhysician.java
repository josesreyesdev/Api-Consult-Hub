package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePhysician implements MedicalConsultationValidator {
    // no permitir hacer citas con medicos inactivos

    @Autowired
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
