package jsrdev.consult_hub.api.controller;

import jsrdev.consult_hub.api.physician.Physician;
import jsrdev.consult_hub.api.physician.PhysicianRepository;
import jsrdev.consult_hub.api.physician.RegisterPhysicianData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    @Autowired // no recomendable para hacer testing
    private PhysicianRepository physicianRepository;

    @PostMapping
    public void registerPhysician(@RequestBody RegisterPhysicianData registerPhysicianData) {
        physicianRepository.save(new Physician(registerPhysicianData));
    }
}
