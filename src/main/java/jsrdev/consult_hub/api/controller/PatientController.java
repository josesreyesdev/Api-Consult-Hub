package jsrdev.consult_hub.api.controller;

import jakarta.validation.Valid;
import jsrdev.consult_hub.api.patient.Patient;
import jsrdev.consult_hub.api.patient.PatientRepository;
import jsrdev.consult_hub.api.patient.RegisterPatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public void registerPatient(@RequestBody @Valid RegisterPatientData registerPatientData) {
        patientRepository.save(new Patient(registerPatientData));
    }
}
