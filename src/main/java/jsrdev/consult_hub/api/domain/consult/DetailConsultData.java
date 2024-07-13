package jsrdev.consult_hub.api.domain.consult;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record DetailConsultData(
        Long id,
        @JsonProperty("id_patient")
        Long idPatient,
        @JsonProperty("id_physician")
        Long idPhysician,
        LocalDateTime date
) {
        public DetailConsultData(Consult consult) {
                this(consult.getId(), consult.getPatient().getId(), consult.getPhysician().getId(), consult.getDate());
        }
}
