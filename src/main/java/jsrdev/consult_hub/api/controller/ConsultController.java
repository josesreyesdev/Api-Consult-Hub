package jsrdev.consult_hub.api.controller;

import jakarta.validation.Valid;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.consult.DetailConsultData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consults")
public class ConsultController {

    @PostMapping
    public ResponseEntity<DetailConsultData> schedule(@RequestBody @Valid AddScheduleConsultData data) {

        return ResponseEntity.ok(new DetailConsultData(data.id(), data.idPatient(), data.idPhysician(), data.date()));
    }
}
