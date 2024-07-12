package jsrdev.consult_hub.api.domain.consult;

import jsrdev.consult_hub.api.domain.patient.Patient;
import jsrdev.consult_hub.api.domain.patient.PatientRepository;
import jsrdev.consult_hub.api.domain.physician.Physician;
import jsrdev.consult_hub.api.domain.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultScheduleService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    public Consult schedule(AddScheduleConsultData data) {

        Optional<Physician> optionalPhysician = physicianRepository.findById(data.idPhysician());
        Optional<Patient> optionalPatient = patientRepository.findById(data.idPatient());

        Patient patient = null;
        if (optionalPatient.isPresent()) {
            patient = optionalPatient.get();
        }

        Physician physician = null;
        if (optionalPhysician.isPresent()) {
            physician = optionalPhysician.get();
        }

        Consult consult = new Consult(null, patient, physician, data.date());

        return consultRepository.save(consult);
    }
}
