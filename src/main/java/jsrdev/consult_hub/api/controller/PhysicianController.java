package jsrdev.consult_hub.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    @PostMapping
    public void registerPhysician(@RequestBody String parameter) {
        System.out.println("request llegó correctamente");
        System.out.println(parameter);
    }
}
