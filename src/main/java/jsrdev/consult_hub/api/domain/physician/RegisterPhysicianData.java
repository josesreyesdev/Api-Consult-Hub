package jsrdev.consult_hub.api.domain.physician;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jsrdev.consult_hub.api.domain.address.AddressData;

public record RegisterPhysicianData(
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

        @NotBlank(message = "Document is required")
        @Pattern(regexp = "\\d{4,6}")
        String document, // solo debe contener entre 4 y 6 digitos numericos

        @NotNull(message = "Specialty is required")
        Specialty specialty,

        @JsonProperty("address")
        @NotNull(message = "Address is required")
        @Valid AddressData addressData // notnull para un objeto
) { }
