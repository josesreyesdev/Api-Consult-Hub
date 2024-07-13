package jsrdev.consult_hub.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.consult.ConsultRepository;

public class PhysicianWithConsult {
    //medico con consulta

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
