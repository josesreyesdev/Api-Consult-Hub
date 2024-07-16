package jsrdev.consult_hub.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import jsrdev.consult_hub.api.domain.consult.AddScheduleConsultData;
import jsrdev.consult_hub.api.domain.consult.ConsultScheduleService;
import jsrdev.consult_hub.api.domain.consult.DetailConsultData;
import jsrdev.consult_hub.api.domain.physician.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@SpringBootTest // usada para componentes como servicios, controllers, repositories, etc
@AutoConfigureMockMvc // Conf. los componentes necesarios para la simulación de una peticion para ese controlador,
@AutoConfigureJsonTesters //encargada de cargar los jacksonTesters
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

    // Agarra un formato java y convierte a formato json
    @Autowired
    private JacksonTester<AddScheduleConsultData> addScheduleConsultDataJacksonTester;

    // Agarra un formato json y convierte a formato java
    @Autowired
    private JacksonTester<DetailConsultData> detailConsultDataJacksonTester;

    @MockBean //Simular la clase de servicio
    private ConsultScheduleService consultScheduleService;

    @Test
    @DisplayName("Retornar estado 400 cuando los datos ingresado sean invalidos")
    @WithMockUser //agregar dependencia,
    void scheduleScenario1() throws Exception {
        //Given  // When
        var response = mvc.perform(post("/consults")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Retornar estado 200 cuando los datos ingresado con validos")
    @WithMockUser //agregar dependencia,
    void scheduleScenario2() throws Exception {
        //Given
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var data = new DetailConsultData(null, 2L, 5L, date);

        // When

        // en vez de any podemos a gregar una instancia misma la del response
        when(consultScheduleService.schedule(any())).thenReturn(data);

        var response = mvc.perform(post("/consults")
                .contentType(MediaType.APPLICATION_JSON)
                .content(addScheduleConsultDataJacksonTester
                                .write(new AddScheduleConsultData(null, 2L, 5L, date, specialty))
                        .getJson()
                )
        ).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        //Json Esperado
        var expectedJSON = detailConsultDataJacksonTester.write(data).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJSON);
    }
}