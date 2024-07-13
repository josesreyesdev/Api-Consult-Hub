package jsrdev.consult_hub.api.domain.consult;

import jsrdev.consult_hub.api.domain.consult.validations.MedicalConsultationValidator;
import jsrdev.consult_hub.api.domain.patient.Patient;
import jsrdev.consult_hub.api.domain.patient.PatientRepository;
import jsrdev.consult_hub.api.domain.physician.Physician;
import jsrdev.consult_hub.api.domain.physician.PhysicianRepository;
import jsrdev.consult_hub.api.infra.exceptions.IntegrityValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultScheduleService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    List<MedicalConsultationValidator> validators;

    public DetailConsultData schedule(AddScheduleConsultData data) {

        // verificar que el paciente se encuentre en la BD
        if (patientRepository.findById(data.idPatient()).isEmpty()) {
            throw new IntegrityValidations("Patient Id not found, Id del Paciente no encontrado");
        }

        // verificar si medico es diferente de null y esta o no en la BD
        if (data.idPhysician() != null && !physicianRepository.existsById(data.idPhysician())) {
            throw new IntegrityValidations("Physician Id not found, Id del Medico no encontrado");
        }

        // Validations
        validators.forEach(v -> v.validate(data));

        Patient patient = patientRepository.findById(data.idPatient()).get();

        Physician physician = selectPhysician(data);
        if (physician == null) {
            throw new IntegrityValidations("No hay médicos disponibles para este horario y especialidad");
        }

        Consult consult = new Consult(null, patient, physician, data.date());

        consultRepository.save(consult);

        return new DetailConsultData(consult);
    }

    private Physician selectPhysician(AddScheduleConsultData data) {
        // si medico es null elegir aleatoriamente un medico disponible
        // no permitir agendar citas con medicos inactivos

        if (data.idPhysician() != null) {
            return physicianRepository.getReferenceById(data.idPhysician());
        }

        if (data.specialty() == null) {
            throw new IntegrityValidations("Seleccione una especialidad para el medico");
        }
        return physicianRepository.selectSpecialtyPhysicianInDate(data.specialty(), data.date());
    }
}
