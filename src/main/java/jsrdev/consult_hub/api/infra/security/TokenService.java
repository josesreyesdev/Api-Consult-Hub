package jsrdev.consult_hub.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generateToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456"); // sirve para validar la firma
            return JWT.create()
                    .withIssuer("jsr-dev")
                    .withSubject("Jose-SR")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
}
