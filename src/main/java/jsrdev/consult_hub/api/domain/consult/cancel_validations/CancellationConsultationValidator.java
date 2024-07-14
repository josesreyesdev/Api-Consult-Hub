package jsrdev.consult_hub.api.domain.consult.cancel_validations;

import jsrdev.consult_hub.api.domain.consult.CancelConsultationData;

public interface CancellationConsultationValidator {

    void validate(CancelConsultationData data);
}
