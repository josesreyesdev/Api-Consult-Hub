package jsrdev.consult_hub.api.controller;

import jakarta.validation.Valid;
import jsrdev.consult_hub.api.domain.user.UserAuthenticationData;
import jsrdev.consult_hub.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    // sirve para disparar el proceso de autenticación en spring
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> userAuthentication(
            @RequestBody @Valid UserAuthenticationData userAuthenticationData
    ) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userAuthenticationData.login(), userAuthenticationData.pass()
        );

        authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generateToken();

        return ResponseEntity.ok(JWToken);
    }

}
