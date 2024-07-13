package jsrdev.consult_hub.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.consult.CancelConsultationData;
import jsrdev.consult_hub.api.domain.consult.ConsultScheduleService;
import jsrdev.consult_hub.api.domain.consult.DetailConsultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consults")
public class ConsultController {

    @Autowired
    private ConsultScheduleService consultScheduleService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailConsultData> schedule(@RequestBody @Valid AddScheduleConsultData data) {

        var consult = consultScheduleService.schedule(data);

        return ResponseEntity.ok(consult);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancel(@RequestBody @Valid CancelConsultationData data) {
        consultScheduleService.cancel(data);

        return ResponseEntity.noContent().build();
    }
}
