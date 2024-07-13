package jsrdev.consult_hub.api.controller;

import jakarta.validation.Valid;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.consult.ConsultScheduleService;
import jsrdev.consult_hub.api.domain.consult.DetailConsultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consults")
public class ConsultController {

    @Autowired
    private ConsultScheduleService consultScheduleService;

    @PostMapping
    public ResponseEntity<DetailConsultData> schedule(@RequestBody @Valid AddScheduleConsultData data) {

        var consult = consultScheduleService.schedule(data);

        return ResponseEntity.ok(consult);
    }
}
