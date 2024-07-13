package jsrdev.consult_hub.api.domain.consult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstSchedule, LocalDateTime lastSchedule);

    Boolean existsByPhysicianIdAndDate(Long idPhysician, LocalDateTime date);
}
