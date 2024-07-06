package jsrdev.consult_hub.api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jsrdev.consult_hub.api.address.AddressData;

public record ResponsePatientData(
        Long id,
        String name,
        String avatar,
        String email,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("identity_document") String identityDocument,
        @JsonProperty("address") AddressData addressData
) {
    public ResponsePatientData(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getAvatar(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getIdentityDocument(),
                new AddressData(patient.getAddress())
        );
    }
}
