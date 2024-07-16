package jsrdev.consult_hub.api.domain.physician;

import jsrdev.consult_hub.api.domain.address.AddressData;
import jsrdev.consult_hub.api.domain.consult.Consult;
import jsrdev.consult_hub.api.domain.patient.Patient;
import jsrdev.consult_hub.api.domain.patient.RegisterPatientData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Busca una db en memoria
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") // perfil a utilizar
class PhysicianRepositoryTest {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deberia retornar null cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void selectMedicWithSpecialtyInDateScenario1() {

        // GIVEN
        var nexMondayAt10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0); // 10 am

        /* registrar un medico, paciente y una consulta */
        var medic = addPhysician("Juan P", "https://imagen-physician.com", "juanp@example.com", "12443", Specialty.CARDIOLOGIA);
        var patient = addPatient("Paciente Prueba", "https://imagen-patient.com", "patienttest@example.com", "213242");
        addConsultation(medic, patient, nexMondayAt10H);

        // WHEN
        // Ver  si el medico disponible
        var freePhysician = physicianRepository.selectSpecialtyPhysicianInDate(
                Specialty.CARDIOLOGIA, nexMondayAt10H
        );

        // THEN
        assertThat(freePhysician).isNull();
    }

    @Test
    @DisplayName("Retorna un medico cuando si se encuentra disponible en la BD para ese horario")
    void selectMedicWithSpecialtyInDateScenario2() {

        // GIVEN
        var nexMondayAt10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0); // 10 am

        var physician = addPhysician("Juan P", "https://imagen-physician.com","juanp@example.com", "12443", Specialty.CARDIOLOGIA);

        // WHEN
        var freePhysician = physicianRepository.selectSpecialtyPhysicianInDate(
                Specialty.CARDIOLOGIA, nexMondayAt10H
        );

        // THEN
        assertThat(freePhysician).isEqualTo(physician);
    }

    private void addConsultation(Physician physician, Patient patient, LocalDateTime date) {
        entityManager.persist(new Consult(null, patient, physician, date, null));
    }

    private Physician addPhysician(String name, String avatar, String email, String document, Specialty specialty) {
        var physician = new Physician(physicianData(name, avatar, email, document, specialty));

        entityManager.persist(physician);
        return physician;
    }

    private Patient addPatient(String name, String avatar, String email, String document) {
        var patient = new Patient(patientData(name, avatar, email, document));

        entityManager.persist(patient);
        return patient;
    }

    private RegisterPhysicianData physicianData(String name, String avatar, String email, String document, Specialty specialty) {
        return new RegisterPhysicianData(
                name,
                avatar,
                email,
                "1232131231",
                document,
                specialty,
                addressData()
        );
    }

    private RegisterPatientData patientData(String name, String avatar, String email, String document) {
        return new RegisterPatientData(
                name,
                avatar,
                email,
                "1232131231",
                document,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "Mexico street",
                "Mexico",
                "Mexico muni or del.",
                "Mexico City",
                "14737",
                "mexico",
                "1212",
                "City central complement"
        );
    }
}