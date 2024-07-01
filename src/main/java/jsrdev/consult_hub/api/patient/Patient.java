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
@EqualsAndHashCode(of = "id") // comparar
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;
    private String email;
    private String phoneNumber;
    private String identityDocument;
    @Embedded
    private Address address;
}
