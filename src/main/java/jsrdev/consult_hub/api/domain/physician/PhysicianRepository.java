package jsrdev.consult_hub.api.domain.physician;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    Page<Physician> findByActiveTrue(Pageable pagination);

    @Query("""
        SELECT p FROM Physician p
        WHERE p.active = true AND
        p.specialty = :specialty AND
        p.id NOT IN(
            SELECT c.physician.id FROM Consult c WHERE c.date = :date
        )
        ORDER BY rand()
        LIMIT 1
        """)
    Physician selectSpecialtyPhysicianInDate(Specialty specialty, LocalDateTime date);
}
