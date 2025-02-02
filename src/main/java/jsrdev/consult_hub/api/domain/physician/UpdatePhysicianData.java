package jsrdev.consult_hub.api.domain.physician;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jsrdev.consult_hub.api.domain.address.AddressData;

public record UpdatePhysicianData(
        @NotNull(message = "Id is required") Long id,
        String name,
        String avatar,
        String document,
        @Valid AddressData addressData
) {
}
