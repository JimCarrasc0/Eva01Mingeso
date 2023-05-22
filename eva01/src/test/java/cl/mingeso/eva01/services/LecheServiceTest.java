package cl.mingeso.eva01.services;

import cl.mingeso.eva01.repositories.LecheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class LecheServiceTest {

    @Mock
    private LecheRepository lecheRepository;

    @InjectMocks
    private LecheService lecheService;
    @MockBean
    private LecheRepository datosLeche;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        lecheService = new LecheService();

    }

    @Test
    void testGuardar() throws IOException {
        MultipartFile file = new MockMultipartFile("test.csv", "test.csv", "text/csv", "test data".getBytes());

        String result = lecheService.guardar(file);

        assertEquals("Archivo guardado con éxito", result);
        verify(lecheRepository, never()).save(any());
    }

}
