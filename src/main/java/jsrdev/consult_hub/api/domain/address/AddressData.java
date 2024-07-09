package jsrdev.consult_hub.api.domain.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AddressData(
        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "State/Province is required")
        @JsonProperty("state_or_province")
        String stateOrProvince,

        @NotBlank(message = "Municipally/delegation is required")
        @JsonProperty("municipality_or_delegation")
        String municipalityOrDelegation,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "Zip_code is required")
        @JsonProperty("zip_code")
        String zipCode,

        @NotBlank(message = "Country")
        String country,

        @NotBlank(message = "Number is required")
        String number,

        @NotBlank(message = "Complement is required")
        String complement
) {
        public AddressData(Address address) {
                this(
                        address.getStreet(),
                        address.getStateOrProvince(),
                        address.getMunicipalityOrDelegation(),
                        address.getCity(),
                        address.getZipCode(),
                        address.getCountry(),
                        address.getNumber(),
                        address.getComplement()
                );
        }
}
