package jsrdev.consult_hub.api.physician;

import jakarta.persistence.*;
import jsrdev.consult_hub.api.address.Address;
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

    @Column(name = "phone_number") //name in DB
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;


    public Physician(RegisterPhysicianData registerPhysicianData) {
        this.name = registerPhysicianData.name();
        this.avatar = registerPhysicianData.avatar();
        this.email = registerPhysicianData.email();
        this.document = registerPhysicianData.document();
        this.phoneNumber = registerPhysicianData.phoneNumber();
        this.specialty = registerPhysicianData.specialty();
        this.address = new Address(registerPhysicianData.address());
    }
}
