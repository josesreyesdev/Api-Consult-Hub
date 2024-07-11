package jsrdev.consult_hub.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Component: Es el esteretipo mas generico de spring para definir un componente
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //obtener token del header Authorization
        String token = request.getHeader("Authorization");
        System.out.println(token);

        //Evaluar si el token es valido
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token enviado es invalido");
        }

        token = token.replace("Bearer ", "");

        System.out.println(tokenService.getSubject(token));

        //paso el request y el response a la cadena de filtros
        filterChain.doFilter(request, response);

    }
}
