package jsrdev.consult_hub.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jsrdev.consult_hub.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePatientData> registerPatient(
            @RequestBody @Valid RegisterPatientData registerPatientData,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var patient = patientRepository.save(new Patient(registerPatientData));

        ResponsePatientData responsePatientData = new ResponsePatientData(patient);
        URI url = uriComponentsBuilder.
                path("/patients/{id}").
                buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(url).body(responsePatientData); // status - 201 - created
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> getListOfPatients(Pageable pagination) {
        var page = patientRepository.findByActiveTrue(pagination)
                .map(PatientListData::new);
        return ResponseEntity.ok(page); // Status - 200 - success
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponsePatientData> updatePatient(@RequestBody @Valid UpdatePatientData updatePatientData) {
        Patient patient = patientRepository.getReferenceById(updatePatientData.id());
        patient.updatePatientData(updatePatientData);

        return ResponseEntity.ok(new ResponsePatientData(patient)); // status - 200
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deactivatePatient(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.deactivatePatient();
        return ResponseEntity.noContent().build(); // status - 204
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePatientData> getPatientById(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new ResponsePatientData(patient)); // Status - 200 - success
    }
}
