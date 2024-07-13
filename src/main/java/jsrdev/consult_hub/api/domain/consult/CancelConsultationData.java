package jsrdev.consult_hub.api.domain.consult;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CancelConsultationData(
        @JsonProperty("id_consult")
        @NotNull Long idConsult,
        @NotNull CancellationReason reason
) { }
