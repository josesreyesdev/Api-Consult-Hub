package jsrdev.consult_hub.api.patient;

import jsrdev.consult_hub.api.address.AddressData;

public record RegisterPatientData(
        String name,
        String avatar,
        String email,
        String phoneNumber,
        String identityDocument,
        AddressData address
) { }
