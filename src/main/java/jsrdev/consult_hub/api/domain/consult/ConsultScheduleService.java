package jsrdev.consult_hub.api.domain.consult;

import jsrdev.consult_hub.api.domain.patient.PatientRepository;
import jsrdev.consult_hub.api.domain.physician.Physician;
import jsrdev.consult_hub.api.domain.physician.PhysicianRepository;
import jsrdev.consult_hub.api.infra.exceptions.IntegrityValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultScheduleService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    public Consult schedule(AddScheduleConsultData data) {

        // verificar que el paciente se encuentre en la BD
        if (patientRepository.findById(data.idPatient()).isEmpty()) {
            throw new IntegrityValidations("Patient Id not found, Id del Paciente no encontrado");
        }

        // verificar si medico es diferente de null y esta o no en la BD
        if (data.idPhysician() != null && physicianRepository.existsById(data.idPhysician())) {
            throw new IntegrityValidations("Physician Id not found, Id del Medico no encontrado");
        }

        var patient = patientRepository.findById(data.idPatient()).get();
        var physician = choosePhysician(data);

        Consult consult = new Consult(null, patient, physician, data.date());

        return consultRepository.save(consult);
    }

    private Physician choosePhysician(AddScheduleConsultData data) {
        return null;
    }
}
