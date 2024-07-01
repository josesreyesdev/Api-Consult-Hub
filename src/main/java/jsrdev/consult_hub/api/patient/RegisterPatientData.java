package jsrdev.consult_hub.api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jsrdev.consult_hub.api.address.AddressData;

public record RegisterPatientData(
        @NotBlank String name,
        @NotBlank String avatar,
        @NotBlank @Email String email,
        @NotBlank @JsonProperty("phone_number") String phoneNumber,
        @NotBlank @JsonProperty("identity_document") String identityDocument,
        @NotNull @Valid AddressData address
) { }
