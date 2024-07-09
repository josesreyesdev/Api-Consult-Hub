package jsrdev.consult_hub.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jsrdev.consult_hub.api.domain.address.AddressData;

public record RegisterPatientData(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Avatar is required")
        String avatar,

        @NotBlank(message = "Email is required")
        @Email(message = "Must be a valid email")
        String email,

        @NotBlank(message = "Phone_number is required")
        @JsonProperty("phone_number")
        String phoneNumber,

        @NotBlank(message = "Identity_document is required") //@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        @JsonProperty("identity_document")
        String identityDocument,

        @JsonProperty("address")
        @NotNull(message = "Address is required")
        @Valid AddressData addressData
) {
}
