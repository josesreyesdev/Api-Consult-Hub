package jsrdev.consult_hub.api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PatientListData(
        Long id,
        String name,
        String avatar,
        String email,
        @JsonProperty("identity_document")
        String identityDocument
) {
    public PatientListData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getAvatar(), patient.getEmail(), patient.getIdentityDocument());
    }
}
