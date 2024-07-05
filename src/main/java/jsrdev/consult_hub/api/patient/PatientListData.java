package jsrdev.consult_hub.api.patient;

public record PatientListData(
        String name,
        String avatar,
        String email,
        String identityDocument
) {
    public PatientListData(Patient patient) {
        this(patient.getName(), patient.getAvatar(), patient.getEmail(), patient.getIdentityDocument());
    }
}
