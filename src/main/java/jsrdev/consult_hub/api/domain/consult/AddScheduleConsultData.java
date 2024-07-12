package jsrdev.consult_hub.api.domain.consult;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jsrdev.consult_hub.api.domain.physician.Specialty;

import java.time.LocalDateTime;

public record AddScheduleConsultData(
        Long id,
        @JsonProperty("id_patient")
        @NotNull Long idPatient,
        @JsonProperty("id_physician")
        Long idPhysician,
        @NotNull @Future LocalDateTime date,
        Specialty specialty
) { }
