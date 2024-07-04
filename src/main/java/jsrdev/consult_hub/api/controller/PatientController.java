package jsrdev.consult_hub.api.controller;

import jakarta.validation.Valid;
import jsrdev.consult_hub.api.patient.Patient;
import jsrdev.consult_hub.api.patient.PatientListData;
import jsrdev.consult_hub.api.patient.PatientRepository;
import jsrdev.consult_hub.api.patient.RegisterPatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<PatientListData> getListOfPatients() {
        return patientRepository.findAll().stream()
                .map(PatientListData::new).toList();
    }

}
