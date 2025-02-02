package jsrdev.consult_hub.api.domain.consult;

import jakarta.persistence.*;
import jsrdev.consult_hub.api.domain.patient.Patient;
import jsrdev.consult_hub.api.domain.physician.Physician;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consults")
@Entity(name = "Consult")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physician_id")
    private Physician physician;

    private LocalDateTime date;

    @Column (name = "cancellation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public Consult(Patient patient, Physician physician, LocalDateTime date) {
        this.patient = patient;
        this.physician = physician;
        this.date = date;
    }

    public void cancel(CancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
