package jsrdev.consult_hub.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jsrdev.consult_hub.api.physician.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    @Autowired // no recomendable para hacer testing
    private PhysicianRepository physicianRepository;

    @PostMapping
    public void registerPhysician(@RequestBody @Valid RegisterPhysicianData registerPhysicianData) {
        physicianRepository.save(new Physician(registerPhysicianData));
    }

    /* Con @PageableDefault() configuramos algunos valores por defecto si es que el front no
     * envia estos parametros
     * */
    @GetMapping
    public Page<PhysicianListData> getListOfPhysicians(@PageableDefault(size = 15) Pageable pagination) {
        return physicianRepository.findAll(pagination)
                .map(PhysicianListData::new);
    }

    @PutMapping
    @Transactional
    // libera la transaccion para hacer un commit en la BD o hace un rollback si hubo alguna inconsistencia de datos
    public void updatePhysician(@RequestBody @Valid UpdatePhysicianData updatePhysicianData) {
        Physician physician = physicianRepository.getReferenceById(updatePhysicianData.id());
        physician.updatePhysicianData(updatePhysicianData);
    }

    /* Borrar un physician en la BD. metodo no recomendado */
    @DeleteMapping("/{id}")
    @Transactional
    public void deletePhysician(@PathVariable Long id) {
        Physician physician = physicianRepository.getReferenceById(id);
        physicianRepository.delete(physician);
    }
}
