package jsrdev.consult_hub.api.address;

import jakarta.persistence.Column;
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

    @Column(name = "state_or_province")
    private String stateOrProvince;

    @Column(name = "municipality_or_delegation")
    private String municipalityOrDelegation;

    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    private String country;
    private String number;
    private String complement;

    public Address(AddressData addressData) {
        this.street = addressData.street();
        this.stateOrProvince = addressData.stateOrProvince();
        this.municipalityOrDelegation = addressData.municipalityOrDelegation();
        this.city = addressData.city();
        this.zipCode = addressData.zipCode();
        this.country = addressData.country();
        this.number = addressData.number();
        this.complement = addressData.complement();
    }

    public Address updatePhysicianData(AddressData addressData) {
        this.street = addressData.street();
        this.stateOrProvince = addressData.stateOrProvince();
        this.municipalityOrDelegation = addressData.municipalityOrDelegation();
        this.city = addressData.city();
        this.zipCode = addressData.zipCode();
        this.country = addressData.country();
        this.number = addressData.number();
        this.complement = addressData.complement();
        return this;
    }

    public Address updatePatientData(AddressData addressData) {
        this.street = addressData.street();
        this.stateOrProvince = addressData.stateOrProvince();
        this.municipalityOrDelegation = addressData.municipalityOrDelegation();
        this.city = addressData.city();
        this.zipCode = addressData.zipCode();
        this.country = addressData.country();
        this.number = addressData.number();
        this.complement = addressData.complement();
        return this;
    }
}
