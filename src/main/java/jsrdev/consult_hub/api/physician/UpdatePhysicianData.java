package jsrdev.consult_hub.api.physician;

import jakarta.validation.constraints.NotNull;
import jsrdev.consult_hub.api.address.AddressData;

public record UpdatePhysicianData(
        @NotNull Long id,
        String name,
        String avatar,
        String document,
        AddressData addressData
) {
}
