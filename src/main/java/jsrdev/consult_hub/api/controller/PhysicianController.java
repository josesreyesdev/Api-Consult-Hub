package jsrdev.consult_hub.api.controller;

import jsrdev.consult_hub.api.physician.RegisterPhysicianData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    @PostMapping
    public void registerPhysician(@RequestBody RegisterPhysicianData registerPhysicianData) {
        System.out.println(registerPhysicianData);
    }
}
