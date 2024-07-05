package jsrdev.consult_hub.api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jsrdev.consult_hub.api.address.AddressData;

public record UpdatePatientData(
        @NotNull Long id,
        String name,
        String avatar,
        @JsonProperty("identity_document")
        String identityDocument,
        AddressData addressData
) { }
