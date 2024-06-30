package jsrdev.consult_hub.api.address;

public record AddressData(
        String street,
        String district,
        String city,
        String number,
        String complement
) { }
