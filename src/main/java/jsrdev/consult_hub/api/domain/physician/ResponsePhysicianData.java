package jsrdev.consult_hub.api.domain.physician;

import com.fasterxml.jackson.annotation.JsonProperty;
import jsrdev.consult_hub.api.domain.address.AddressData;

public record ResponsePhysicianData(
        Long id,
        String name,
        String avatar,
        String email,
        @JsonProperty("phone_number") String phoneNumber,
        String document,
        String specialty,
        @JsonProperty("address") AddressData addressData
) {
    // Constructor no canónico delegando al constructor canónico
    public ResponsePhysicianData(Physician physician) {
        this(
                physician.getId(),
                physician.getName(),
                physician.getAvatar(),
                physician.getEmail(),
                physician.getPhoneNumber(),
                physician.getDocument(),
                physician.getSpecialty().toString(),
                new AddressData(physician.getAddress())
        );
    }
}
