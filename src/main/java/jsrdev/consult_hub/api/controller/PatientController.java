package jsrdev.consult_hub.api.controller;

import jsrdev.consult_hub.api.patient.RegisterPatientData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @PostMapping
    public void registerPatient(@RequestBody RegisterPatientData registerPatientData) {
        System.out.println(registerPatientData);
    }
}
