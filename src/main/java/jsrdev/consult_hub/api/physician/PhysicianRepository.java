package jsrdev.consult_hub.api.physician;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    Page<Physician> findByActiveTrue(Pageable pagination);
}
