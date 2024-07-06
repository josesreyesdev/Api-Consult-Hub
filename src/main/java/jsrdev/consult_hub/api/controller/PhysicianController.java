package jsrdev.consult_hub.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jsrdev.consult_hub.api.physician.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    @Autowired // no recomendable para hacer testing
    private PhysicianRepository physicianRepository;

    @PostMapping
    public ResponseEntity<ResponsePhysicianData> registerPhysician(
            @RequestBody @Valid RegisterPhysicianData registerPhysicianData,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        var physician = physicianRepository.save(new Physician(registerPhysicianData));

        ResponsePhysicianData responsePhysicianData = new ResponsePhysicianData(physician);
        URI url = uriComponentsBuilder.path("/physicians/{id}")
                .buildAndExpand(physician.getId())
                .toUri();

        return ResponseEntity.created(url).body(responsePhysicianData);
        // status 201-created
        // url - donde encontrar el medico ej: http://localhost:8080/physicians/xx
    }

    /* Con @PageableDefault() configuramos algunos valores por defecto si es que el front no
     * envia estos parametros
     * */
    @GetMapping
    public ResponseEntity<Page<PhysicianListData>> getListOfPhysicians(@PageableDefault(size = 15) Pageable pagination) {
        var page = physicianRepository.findByActiveTrue(pagination)
                .map(PhysicianListData::new);
        return ResponseEntity.ok(page); // status - 200 - ok
    }

    @PutMapping
    @Transactional
    // libera la transaccion para hacer un commit en la BD o hace un rollback si hubo alguna inconsistencia de datos
    public ResponseEntity<ResponsePhysicianData> updatePhysician(@RequestBody @Valid UpdatePhysicianData updatePhysicianData) {
        Physician physician = physicianRepository.getReferenceById(updatePhysicianData.id());
        physician.updatePhysicianData(updatePhysicianData);

        return ResponseEntity.ok(new ResponsePhysicianData(physician)); //status - 200 - ok
    }

    /* Borrar un physician en la BD. metodo no recomendado */
    /*@DeleteMapping("/{id}")
    @Transactional
    public void deletePhysician(@PathVariable Long id) {
        Physician physician = physicianRepository.getReferenceById(id);
        physicianRepository.delete(physician);
    } */

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deactivatePhysician(@PathVariable Long id) {
        Physician physician = physicianRepository.getReferenceById(id);
        physician.deactivatePhysician();
        return ResponseEntity.noContent().build(); // status 204
    }

    // Obtener un item por su id
    @GetMapping("/{id}")
    public ResponseEntity<ResponsePhysicianData> getPhysicianById(@PathVariable Long id) {
        Physician physician = physicianRepository.getReferenceById(id);
        var responsePhysician = new ResponsePhysicianData(physician);
        return ResponseEntity.ok(responsePhysician); // Status - 200 - success
    }
}
