package cl.mingeso.eva01.services;

import cl.mingeso.eva01.entities.ProveedorEntity;
import cl.mingeso.eva01.repositories.ProveedorRepository;
import cl.mingeso.eva01.services.ProveedorService;
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
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProveedor() {
        // Arrange
        String id = "123";
        String nombre = "Proveedor1";
        String categoria = "A";
        String retencion = "Si";

        ProveedorEntity proveedor = new ProveedorEntity(id, nombre, categoria, retencion);

        // Act
        proveedorService.saveProveedor(id, nombre, categoria, retencion);

        // Assert
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    void testObtenerProveedores() {
        // Arrange
        List<ProveedorEntity> proveedores = new ArrayList<>();
        proveedores.add(new ProveedorEntity("1", "Proveedor1", "A", "Si"));
        proveedores.add(new ProveedorEntity("2", "Proveedor2", "B", "No"));

        when(proveedorRepository.findAll()).thenReturn(proveedores);

        // Act
        ArrayList<ProveedorEntity> result = proveedorService.obtenerProveedores();

        // Assert
        assertEquals(proveedores.size(), result.size());
        assertEquals(proveedores, result);
    }

    @Test
    void testGetCategoria() {
        // Arrange
        String id = "123";
        String categoria = "A";

        when(proveedorRepository.findCategoryById(id)).thenReturn(categoria);

        // Act
        String result = proveedorService.getCategoria(id);

        // Assert
        assertEquals(categoria, result);
    }

    @Test
    void testGetProveedor() {
        // Arrange
        String id = "123";
        ProveedorEntity proveedor = new ProveedorEntity(id, "Proveedor1", "A", "Si");

        when(proveedorRepository.findProveedorById(id)).thenReturn(proveedor);

        // Act
        ProveedorEntity result = proveedorService.getProveedor(id);

        // Assert
        assertEquals(proveedor, result);
    }
}
