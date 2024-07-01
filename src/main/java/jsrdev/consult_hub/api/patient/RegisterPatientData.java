package jsrdev.consult_hub.api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jsrdev.consult_hub.api.address.AddressData;

public record RegisterPatientData(
        String name,
        String avatar,
        String email,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("identity_document") String identityDocument,
        AddressData address
) { }
