package cl.mingeso.eva01.services;

import cl.mingeso.eva01.entities.ImportarDatosEntity;
import cl.mingeso.eva01.repositories.ImportarDatosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ImportarDatosServiceTest {

    @Mock
    private ImportarDatosRepository dataRepository;

    @InjectMocks
    private ImportarDatosService importarDatosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardar() throws IOException {
        MultipartFile file = new MockMultipartFile("test.csv", "test.csv", "text/csv", "test data".getBytes());

        String result = importarDatosService.guardar(file);

        assertEquals("Archivo guardado con Éxito", result);
        verify(dataRepository, never()).save(any());
    }

    @Test
    void testGuardarDB() {
        String fecha = "01/01/2023";
        String turno = "M";
        String proveedorId = "12345";
        String kgLeche = "10.0";

        importarDatosService.guardarDB(fecha, turno, proveedorId, kgLeche);

        verify(dataRepository, times(1)).save(any(ImportarDatosEntity.class));
    }
    @Test
    void testGuardarDB2() {
        String fecha = "23/01";
        String turno = "M";
        String proveedorId = "12345";
        String kgLeche = "10.0";

        importarDatosService.guardarDB(fecha, turno, proveedorId, kgLeche);

        verify(dataRepository, times(1)).save(any(ImportarDatosEntity.class));
    }

    @Test
    void testEliminarData() {
        ArrayList<ImportarDatosEntity> datos = new ArrayList<>();
        datos.add(new ImportarDatosEntity());

        importarDatosService.eliminarData(datos);

        verify(dataRepository, times(1)).deleteAll(datos);
    }
}
