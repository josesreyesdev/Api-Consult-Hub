package jsrdev.consult_hub.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValidation>> handlerError400(MethodArgumentNotValidException ex) {
        List<DataErrorValidation> errors = ex.getFieldErrors().stream()
                .map(DataErrorValidation::new).toList();

        return ResponseEntity.badRequest().body(errors);
    }

    private record DataErrorValidation(String field, String error) {

        public DataErrorValidation(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
