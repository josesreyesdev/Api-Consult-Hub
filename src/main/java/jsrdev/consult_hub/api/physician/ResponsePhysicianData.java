package jsrdev.consult_hub.api.physician;

import com.fasterxml.jackson.annotation.JsonAlias;
import jsrdev.consult_hub.api.address.AddressData;

public record ResponsePhysicianData(
        Long id,
        String name,
        String avatar,
        String email,
        @JsonAlias("phone_number") String phoneNumber,
        String document,
        String specialty,
        @JsonAlias("address") AddressData addressData
) {
}
