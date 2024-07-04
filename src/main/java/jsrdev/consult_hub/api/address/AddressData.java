package jsrdev.consult_hub.api.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AddressData(
        @NotBlank String street,

        @NotBlank @JsonProperty("state_or_province")
        String stateOrProvince,

        @NotBlank @JsonProperty("municipality_or_delegation")
        String municipalityOrDelegation,

        @NotBlank String city,

        @NotBlank @JsonProperty("zip_code")
        String zipCode,

        @NotBlank String country,
        @NotBlank String number,
        @NotBlank String complement
) { }
