package jsrdev.consult_hub.api.controller;

import jakarta.validation.Valid;
import jsrdev.consult_hub.api.patient.Patient;
import jsrdev.consult_hub.api.patient.PatientListData;
import jsrdev.consult_hub.api.patient.PatientRepository;
import jsrdev.consult_hub.api.patient.RegisterPatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public void registerPatient(@RequestBody @Valid RegisterPatientData registerPatientData) {
        patientRepository.save(new Patient(registerPatientData));
    }

    @GetMapping
    public Page<PatientListData> getListOfPatients(Pageable pagination) {
        return patientRepository.findAll(pagination)
                .map(PatientListData::new);
    }

}
