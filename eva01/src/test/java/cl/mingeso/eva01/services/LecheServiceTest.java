package cl.mingeso.eva01.services;

import cl.mingeso.eva01.entities.LecheEntity;
import cl.mingeso.eva01.repositories.LecheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class LecheServiceTest {

    @Mock
    private LecheRepository lecheRepository;

    @InjectMocks
    private LecheService lecheService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardarDatos() {
        LecheEntity lecheEntity = new LecheEntity();
        lecheEntity.setProveedorId("123");
        lecheEntity.setGrasa(3.5f);
        lecheEntity.setSolido(2.5f);

        lecheService.guardarDatos(lecheEntity);

        verify(lecheRepository, times(1)).save(lecheEntity);
    }

    @Test
    void testObtenerDatos() {
        LecheEntity lecheEntity1 = new LecheEntity();
        lecheEntity1.setProveedorId("123");
        lecheEntity1.setGrasa(3.5f);
        lecheEntity1.setSolido(2.5f);

        LecheEntity lecheEntity2 = new LecheEntity();
        lecheEntity2.setProveedorId("456");
        lecheEntity2.setGrasa(4.0f);
        lecheEntity2.setSolido(3.0f);

        List<LecheEntity> datosEsperados = new ArrayList<>();
        datosEsperados.add(lecheEntity1);
        datosEsperados.add(lecheEntity2);

        when(lecheRepository.findAll()).thenReturn(datosEsperados);

        List<LecheEntity> datosObtenidos = lecheService.obtenerDatos();

        assertEquals(datosEsperados, datosObtenidos);
        verify(lecheRepository, times(1)).findAll();
    }

    @Test
    void testGuardarDB() {
        String proveedorId = "123";
        String grasa = "3.5";
        String solido = "2.5";

        lecheService.guardarDB(proveedorId, grasa, solido);

        verify(lecheRepository, times(1)).save(any(LecheEntity.class));
    }

    @Test
    void testEliminarDatos() {
        // Crear una lista de datos de prueba
        ArrayList<LecheEntity> datos = new ArrayList<>();
        datos.add(new LecheEntity());
        datos.add(new LecheEntity());
        datos.add(new LecheEntity());

        // Llamar al método eliminarDatos
        lecheService.eliminarDatos(datos);

        // Verificar que el método deleteAll del repositorio se haya llamado con los datos adecuados
        verify(lecheRepository, times(1)).deleteAll(datos);
    }


}
