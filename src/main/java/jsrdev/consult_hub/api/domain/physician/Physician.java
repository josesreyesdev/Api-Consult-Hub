package jsrdev.consult_hub.api.domain.physician;

import jakarta.persistence.*;
import jsrdev.consult_hub.api.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "physicians")
@Entity(name = "Physician")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // comparar
public class Physician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String avatar;
    private String email;
    private String document;

    private Boolean active;

    @Column(name = "phone_number") //name in DB
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;


    public Physician(RegisterPhysicianData registerPhysicianData) {
        this.active = true;
        this.name = registerPhysicianData.name();
        this.avatar = registerPhysicianData.avatar();
        this.email = registerPhysicianData.email();
        this.document = registerPhysicianData.document();
        this.phoneNumber = registerPhysicianData.phoneNumber();
        this.specialty = registerPhysicianData.specialty();
        this.address = new Address(registerPhysicianData.addressData());
    }

    public void updatePhysicianData(UpdatePhysicianData updatePhysicianData) {
        if (updatePhysicianData.name() != null) {
            this.name = updatePhysicianData.name();
        }
        if (updatePhysicianData.avatar() != null) {
            this.avatar = updatePhysicianData.avatar();
        }
        if (updatePhysicianData.document() != null) {
            this.document = updatePhysicianData.document();
        }
        if (updatePhysicianData.addressData() != null) {
            this.address = address.updatePhysicianData(updatePhysicianData.addressData());
        }
    }

    public void deactivatePhysician() {
        this.active = false;
    }
}
