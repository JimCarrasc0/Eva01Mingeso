package cl.mingeso.eva01.services;

import cl.mingeso.eva01.entities.PaymentEntity;
import cl.mingeso.eva01.repositories.PaymentRepository;
import cl.mingeso.eva01.repositories.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testCalcularCostoProveedor() {
        // Datos de prueba
        String proveedorId = "45110";
        float expectedPagoFinal = -30601.0f;

        List<Object[]> varLeche = new ArrayList<>();
        Object[] f1 = new Object[] {null, 87.5f};
        Object[] f2 = new Object[] {400.0f, -2.9126215f};
        Object[] f3 = new Object[] {50.0f, -87.5f};
        varLeche.add(f1);
        varLeche.add(f2);
        varLeche.add(f3);

        List<Object[]> varGrasa = new ArrayList<>();
        f1 = new Object[] {27.0f, null};
        f2 = new Object[] {20.0f, -25.92596f};

        varGrasa.add(f1);
        varGrasa.add(f2);

        List<Object[]> varSolido = new ArrayList<>();
        f1 = new Object[] {94.0f, null};
        f2 = new Object[] {30.0f, -68.085106f};
        varSolido.add(f1);
        varSolido.add(f2);

        ArrayList turno=new ArrayList<>();
        turno.add("M");
        turno.add("T");

        when(paymentRepository.getLecheProveedor(proveedorId)).thenReturn(862.0f);
        when(paymentRepository.getGrasaProveedor(proveedorId)).thenReturn(23.5f);
        when(paymentRepository.getSolidoProveedor(proveedorId)).thenReturn(62.0f);
        when(proveedorRepository.findCategoryById(proveedorId)).thenReturn("C");
        when(proveedorRepository.findRetencionById(proveedorId)).thenReturn("No");
        when(paymentRepository.getVariacionKgLecheByProveedorId(proveedorId)).thenReturn(varLeche);
        when(paymentRepository.getVariacionGrasaByProveedorId(proveedorId)).thenReturn(varGrasa);
        when(paymentRepository.getVariacionSolidoByProveedorId(proveedorId)).thenReturn(varSolido);
        when(paymentRepository.getTurnosProveedor(proveedorId)).thenReturn(turno);


        PaymentEntity paymentEntity = paymentService.calcularCostoProveedor(proveedorId);
        float actualPagoFinal = paymentEntity.getPagoFinal();

        // Verificación de resultados
        assertEquals(expectedPagoFinal, actualPagoFinal, 0.01);


    }
    @Test
    void testCalcularCostoProveedor2() {
        // Datos de prueba
        String proveedorId = "45110";
        float expectedPagoFinal = 1.0133739E7f;

        List<Object[]> varLeche = new ArrayList<>();
        Object[] f1 = new Object[] {null, 0.0f};
        Object[] f2 = new Object[] {35862.0f, 0.0f};
        Object[] f3 = new Object[] {35862.0f, 0.0f};
        varLeche.add(f1);
        varLeche.add(f2);
        varLeche.add(f3);

        List<Object[]> varGrasa = new ArrayList<>();
        f1 = new Object[] {29.7f, null};
        f2 = new Object[] {23.4f, -26.92f};

        varGrasa.add(f1);
        varGrasa.add(f2);

        List<Object[]> varSolido = new ArrayList<>();
        f1 = new Object[] {25.4f, null};
        f2 = new Object[] {22.8f, -11.4f};
        varSolido.add(f1);
        varSolido.add(f2);

        ArrayList turno=new ArrayList<>();
        turno.add("M");
        turno.add("T");

        when(paymentRepository.getLecheProveedor(proveedorId)).thenReturn(35862.0f);
        when(paymentRepository.getGrasaProveedor(proveedorId)).thenReturn(26.55f);
        when(paymentRepository.getSolidoProveedor(proveedorId)).thenReturn(24.1f);
        when(proveedorRepository.findCategoryById(proveedorId)).thenReturn("A");
        when(proveedorRepository.findRetencionById(proveedorId)).thenReturn("Si");
        when(paymentRepository.getVariacionKgLecheByProveedorId(proveedorId)).thenReturn(varLeche);
        when(paymentRepository.getVariacionGrasaByProveedorId(proveedorId)).thenReturn(varGrasa);
        when(paymentRepository.getVariacionSolidoByProveedorId(proveedorId)).thenReturn(varSolido);
        when(paymentRepository.getTurnosProveedor(proveedorId)).thenReturn(turno);


        PaymentEntity paymentEntity = paymentService.calcularCostoProveedor(proveedorId);
        float actualPagoFinal = paymentEntity.getPagoFinal();

        // Verificación de resultados
        assertEquals(expectedPagoFinal, actualPagoFinal, 0.01);

    }

    @Test
    void testCalcularCostoProveedor3() {
        // Datos de prueba
        String proveedorId = "45110";
        float expectedPagoFinal = 48555.296875f;

        List<Object[]> varLeche = new ArrayList<>();
        Object[] f1 = new Object[] {null, 0.0f};
        Object[] f2 = new Object[] {127.0f, -27.0f};
        varLeche.add(f1);
        varLeche.add(f2);

        List<Object[]> varGrasa = new ArrayList<>();
        f1 = new Object[] {58.5f, null};
        f2 = new Object[] {50.0f, -17.0f};

        varGrasa.add(f1);
        varGrasa.add(f2);

        List<Object[]> varSolido = new ArrayList<>();
        f1 = new Object[] {58.5f, null};
        f2 = new Object[] {50.0f, -17.0f};
        varSolido.add(f1);
        varSolido.add(f2);

        ArrayList turno=new ArrayList<>();
        turno.add("M");
        turno.add("T");

        when(paymentRepository.getLecheProveedor(proveedorId)).thenReturn(113.5f);
        when(paymentRepository.getGrasaProveedor(proveedorId)).thenReturn(54.25f);
        when(paymentRepository.getSolidoProveedor(proveedorId)).thenReturn(54.25f);
        when(proveedorRepository.findCategoryById(proveedorId)).thenReturn("B");
        when(proveedorRepository.findRetencionById(proveedorId)).thenReturn("No");
        when(paymentRepository.getVariacionKgLecheByProveedorId(proveedorId)).thenReturn(varLeche);
        when(paymentRepository.getVariacionGrasaByProveedorId(proveedorId)).thenReturn(varGrasa);
        when(paymentRepository.getVariacionSolidoByProveedorId(proveedorId)).thenReturn(varSolido);
        when(paymentRepository.getTurnosProveedor(proveedorId)).thenReturn(turno);


        PaymentEntity paymentEntity = paymentService.calcularCostoProveedor(proveedorId);
        float actualPagoFinal = paymentEntity.getPagoFinal();

        // Verificación de resultados
        assertEquals(expectedPagoFinal, actualPagoFinal, 0.01);


    }

    @Test
    void testCalcularCostoProveedor4() {
        // Datos de prueba
        String proveedorId = "45110";
        float expectedPagoFinal = 4968.0f;
        List<Object[]> varLeche = new ArrayList<>();
        Object[] f1 = new Object[] {null, 0.0f};
        Object[] f2 = new Object[] {120.0f, -9.09f};
        varLeche.add(f1);
        varLeche.add(f2);

        List<Object[]> varGrasa = new ArrayList<>();
        f1 = new Object[] {20.0f, null};
        f2 = new Object[] {18.0f, 10.0f};

        varGrasa.add(f1);
        varGrasa.add(f2);

        List<Object[]> varSolido = new ArrayList<>();
        f1 = new Object[] {18.0f, null};
        f2 = new Object[] {18.0f, 0};
        varSolido.add(f1);

        ArrayList turno=new ArrayList<>();
        turno.add("M");
        turno.add("T");

        when(paymentRepository.getLecheProveedor(proveedorId)).thenReturn(115.0f);
        when(paymentRepository.getGrasaProveedor(proveedorId)).thenReturn(19.5f);
        when(paymentRepository.getSolidoProveedor(proveedorId)).thenReturn(18.0f);
        when(proveedorRepository.findCategoryById(proveedorId)).thenReturn("D");
        when(proveedorRepository.findRetencionById(proveedorId)).thenReturn("Si");
        when(paymentRepository.getVariacionKgLecheByProveedorId(proveedorId)).thenReturn(varLeche);
        when(paymentRepository.getVariacionGrasaByProveedorId(proveedorId)).thenReturn(varGrasa);
        when(paymentRepository.getVariacionSolidoByProveedorId(proveedorId)).thenReturn(varSolido);
        when(paymentRepository.getTurnosProveedor(proveedorId)).thenReturn(turno);


        PaymentEntity paymentEntity = paymentService.calcularCostoProveedor(proveedorId);
        float actualPagoFinal = paymentEntity.getPagoFinal();

        // Verificación de resultados
        assertEquals(expectedPagoFinal, actualPagoFinal, 0.01);


    }

    @Test
    void testCalcularCostoProveedor5() {
        // Datos de prueba
        String proveedorId = "45110";
        float expectedPagoFinal = 179309.75f;
        List<Object[]> varLeche = new ArrayList<>();
        Object[] f1 = new Object[] {null, 0.0f};
        Object[] f2 = new Object[] {35862.0f, 0.0f};
        Object[] f3 = new Object[] {35862.0f, 0.0f};
        varLeche.add(f1);
        varLeche.add(f2);
        varLeche.add(f3);

        List<Object[]> varGrasa = new ArrayList<>();
        f1 = new Object[] {15.0f, null};
        f2 = new Object[] {16.0f, 1.0f};

        varGrasa.add(f1);
        varGrasa.add(f2);

        List<Object[]> varSolido = new ArrayList<>();
        f1 = new Object[] {5.0f, null};
        f2 = new Object[] {5.0f, 0};
        varSolido.add(f1);

        ArrayList turno=new ArrayList<>();
        turno.add("M");
        turno.add("T");

        when(paymentRepository.getLecheProveedor(proveedorId)).thenReturn(35862.0f);
        when(paymentRepository.getGrasaProveedor(proveedorId)).thenReturn(15.5f);
        when(paymentRepository.getSolidoProveedor(proveedorId)).thenReturn(5.0f);
        when(proveedorRepository.findCategoryById(proveedorId)).thenReturn("E");
        when(proveedorRepository.findRetencionById(proveedorId)).thenReturn("Si");
        when(paymentRepository.getVariacionKgLecheByProveedorId(proveedorId)).thenReturn(varLeche);
        when(paymentRepository.getVariacionGrasaByProveedorId(proveedorId)).thenReturn(varGrasa);
        when(paymentRepository.getVariacionSolidoByProveedorId(proveedorId)).thenReturn(varSolido);
        when(paymentRepository.getTurnosProveedor(proveedorId)).thenReturn(turno);


        PaymentEntity paymentEntity = paymentService.calcularCostoProveedor(proveedorId);
        float actualPagoFinal = paymentEntity.getPagoFinal();

        // Verificación de resultados
        assertEquals(expectedPagoFinal, actualPagoFinal, 0.01);


    }
}
