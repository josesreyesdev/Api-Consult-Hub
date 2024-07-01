package jsrdev.consult_hub.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    public Address(AddressData addressData) {
        this.street = addressData.street();
        this.district = addressData.district();
        this.city = addressData.city();
        this.number = addressData.number();
        this.complement = addressData.complement();
    }
}
