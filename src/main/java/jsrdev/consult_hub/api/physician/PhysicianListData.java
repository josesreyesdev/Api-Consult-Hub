package jsrdev.consult_hub.api.physician;

public record PhysicianListData(
        String name,
        String specialty,
        String document,
        String email
) {

    public PhysicianListData(Physician physician) {
        this(
                physician.getName(), physician.getSpecialty().toString(),
                physician.getDocument(), physician.getEmail()
        );
    }
}
