package jsrdev.consult_hub.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jsrdev.consult_hub.api.patient.*;
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
        return patientRepository.findByActiveTrue(pagination)
                .map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid UpdatePatientData updatePatientData) {
        Patient patient = patientRepository.getReferenceById(updatePatientData.id());
        patient.updatePatientData(updatePatientData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deactivatePatient(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.deactivatePatient();
    }

}
