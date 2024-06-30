package jsrdev.consult_hub.api.physician;

import jsrdev.consult_hub.api.address.AddressData;

public record RegisterPhysicianData(
        String name,
        String photo,
        String email,
        String document,
        Specialty specialty,
        AddressData address
) { }
