package jsrdev.consult_hub.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jsrdev.consult_hub.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Component: Es el esteretipo mas generico de spring para definir un componente
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //obtener token del header Authorization
        String authHeader = request.getHeader("Authorization");

        //Evaluar si el token es valido
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");

            // Verificar usuario si tiene sesion
            var username = tokenService.getSubject(token);

            if (username != null) {
                //token válido
                var user = userRepository.findByLogin(username);

                // forzamos inicio de sesion
                var authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //paso el request y el response a la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
