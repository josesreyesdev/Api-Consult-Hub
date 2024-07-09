package jsrdev.consult_hub.api.domain.user;

public record UserAuthenticationData(
        String login,
        String pass
) {
}
