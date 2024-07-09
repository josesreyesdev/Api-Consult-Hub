package jsrdev.consult_hub.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jsrdev.consult_hub.api.domain.address.AddressData;

public record UpdatePatientData(

        @NotNull(message = "Id is required")
        Long id,

        String name,

        String avatar,

        @JsonProperty("phone_number")
        String phoneNumber,

        @JsonProperty("address")
        @Valid AddressData addressData
) { }
