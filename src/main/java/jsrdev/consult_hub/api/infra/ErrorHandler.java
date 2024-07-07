package jsrdev.consult_hub.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* Rev. programación orientada a aspectos
*
* Actúa como proxy para todos los controllers, para interceptar las llamadas
* en caso suceda alguna exception
* */

@RestControllerAdvice
public class ErrorHandler {

    // Sirve para indicar que el metodo debe ser llamado en caso exista el tipo de exception
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handlerError404() {
        return ResponseEntity.notFound().build();
    }
}
