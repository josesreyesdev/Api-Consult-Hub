package jsrdev.consult_hub.api.physician;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jsrdev.consult_hub.api.address.AddressData;

public record RegisterPhysicianData(
        @NotBlank String name,
        @NotBlank String avatar,
        @NotBlank @Email String email,
        @NotBlank @JsonProperty("phone_number") String phoneNumber,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String document, // solo debe contener entre 4 y 6 digitos numericos
        @NotNull Specialty specialty,
        @NotNull @Valid AddressData address // notnull para un objeto
) { }
