package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.consult.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhysicianWithConsult implements MedicalConsultationValidator {
    //medico con consulta

    @Autowired
    ConsultRepository consultRepository;

    public void validate(AddScheduleConsultData data) {

        if (data.idPhysician() == null) {
            return;
        }

        var physicianWithConsult = consultRepository
                .existsByPhysicianIdAndDate(data.idPhysician(), data.date());

        if (physicianWithConsult) {
            throw new ValidationException("El médico ya tiene una consulta activa en ese horario");
        }
    }
}
