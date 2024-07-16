package jsrdev.consult_hub.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest // usada para componentes como servicios, controllers, repositories, etc
@AutoConfigureMockMvc // Conf. los componentes necesarios para la simulación de una peticion para ese controlador,
class ConsultControllerTest {

    /*
     * Verificar los diferentes estados al realizar una peticion:
     * 1.- Estado 400 => Valores invalidos
     * 2.- Estado 404 => Usuario no encontrado
     * 3.- Estado 403 => Autorización no ha sido aceptado, por el token
     * 4.- Estado 200 => Usuario encontrado exitosamente
     * */

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Retornar estado 400 cuando los datos ingresado sean invalidos")
    @WithMockUser //agregar dependencia,
    void scheduleScenario1() throws Exception {
        //Given  // When
        var response = mvc.perform(post("/consults")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}