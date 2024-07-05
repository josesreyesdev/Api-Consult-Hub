package jsrdev.consult_hub.api.patient;

import jakarta.persistence.*;
import jsrdev.consult_hub.api.address.Address;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String avatar;
    private String email;

    @Column(name = "phone_number") //name in DB
    private String phoneNumber;

    @Column(name = "identity_document")
    private String identityDocument;

    private Boolean active;

    @Embedded
    private Address address;

    public Patient(RegisterPatientData registerPatientData) {
        this.active = true;
        this.name = registerPatientData.name();
        this.avatar = registerPatientData.avatar();
        this.email = registerPatientData.email();
        this.phoneNumber = registerPatientData.phoneNumber();
        this.identityDocument = registerPatientData.identityDocument();
        this.address = new Address(registerPatientData.addressData());
    }

    public void updatePatientData(UpdatePatientData updatePatientData) {
        if (updatePatientData.name() != null) {
            this.name = updatePatientData.name();
        }
        if (updatePatientData.avatar() != null) {
            this.avatar = updatePatientData.avatar();
        }
        if (updatePatientData.phoneNumber() != null) {
            this.phoneNumber = updatePatientData.phoneNumber();
        }
        if (updatePatientData.addressData() != null) {
            this.address = address.updatePatientData(updatePatientData.addressData());
        }
    }

    public void deactivatePatient() {
        this.active = false;
    }
}
