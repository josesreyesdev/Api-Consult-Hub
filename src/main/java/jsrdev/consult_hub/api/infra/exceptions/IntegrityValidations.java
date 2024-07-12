package jsrdev.consult_hub.api.infra.exceptions;

public class IntegrityValidations extends RuntimeException {

    public IntegrityValidations(String message) {
        super(message);
    }
}
