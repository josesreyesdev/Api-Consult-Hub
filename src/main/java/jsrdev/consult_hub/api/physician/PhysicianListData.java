package jsrdev.consult_hub.api.physician;

public record PhysicianListData(
        Long id,
        String name,
        String specialty,
        String document,
        String email
) {

    public PhysicianListData(Physician physician) {
        this(physician.getId(), physician.getName(), physician.getSpecialty().toString(), physician.getDocument(), physician.getEmail());
    }
}
