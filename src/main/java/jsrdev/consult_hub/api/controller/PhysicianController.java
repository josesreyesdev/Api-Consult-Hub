package jsrdev.consult_hub.api.controller;

import jakarta.validation.Valid;
import jsrdev.consult_hub.api.physician.Physician;
import jsrdev.consult_hub.api.physician.PhysicianListData;
import jsrdev.consult_hub.api.physician.PhysicianRepository;
import jsrdev.consult_hub.api.physician.RegisterPhysicianData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    @Autowired // no recomendable para hacer testing
    private PhysicianRepository physicianRepository;

    @PostMapping
    public void registerPhysician(@RequestBody @Valid RegisterPhysicianData registerPhysicianData) {
        physicianRepository.save(new Physician(registerPhysicianData));
    }

    @GetMapping
    public List<PhysicianListData> getListOfPhysicians() {
        return physicianRepository.findAll().stream()
                .map(PhysicianListData::new).toList();
    }
}
