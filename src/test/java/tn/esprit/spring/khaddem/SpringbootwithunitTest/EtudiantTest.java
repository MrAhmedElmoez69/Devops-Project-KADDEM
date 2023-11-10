package tn.esprit.spring.khaddem.SpringbootwithunitTest;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import tn.esprit.spring.khaddem.controllers.EtudiantRestController;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;


import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class EtudiantTest {


    private MockMvc mockMvc;

    @InjectMocks
    private EtudiantRestController etudiantRestController;

    @Mock
    private IEtudiantService etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private DepartementRepository departementRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEtudiantEntity() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test Etudiant");

        assertEquals(1, etudiant.getIdEtudiant());
        assertEquals("Test Etudiant", etudiant.getNomE());
    }

    @Test
     void testEnumValues() {
        Option[] values = Option.values();

        // Check that there are 4 enum values
        assertEquals(4, values.length);

        // Test individual enum values
        assertEquals(Option.GAMIX, Option.valueOf("GAMIX"));
        assertEquals(Option.SE, Option.valueOf("SE"));
        assertEquals(Option.SAE, Option.valueOf("SAE"));
        assertEquals(Option.INFINI, Option.valueOf("INFINI"));
    }


    @Test
    void testGetEtudiants() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setPrenomE("John");
        etudiant.setNomE("Doe");
        etudiant.setOp(Option.GAMIX);

        List<Etudiant> etudiants = Collections.singletonList(etudiant);

        when(etudiantService.retrieveAllEtudiants()).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantRestController.getEtudiants();

        // Assert
        assertEquals(etudiants, result);
    }

    @Test
    void testRetrieveEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setPrenomE("John");
        etudiant.setNomE("Doe");
        etudiant.setOp(Option.GAMIX);

        when(etudiantService.retrieveEtudiant(1)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantRestController.retrieveContrat(1);

        // Assert
        assertEquals(etudiant, result);
    }


    @Test
    void testAddEtudiant() {
        // Arrange
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(1);
        etudiantDTO.setPrenomE("Mariem");
        etudiantDTO.setNomE("Nacib");
        etudiantDTO.setOp(Option.SAE);

        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
        etudiant.setPrenomE(etudiantDTO.getPrenomE());
        etudiant.setNomE(etudiantDTO.getNomE());
        etudiant.setOp(etudiantDTO.getOp());

        when(etudiantService.addEtudiant(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantRestController.addEtudiant(etudiantDTO);

        // Assert
        assertEquals(etudiant, result);
    }

    @Test
    void testUpdateEtudiant() {
        // Arrange
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(1);
        etudiantDTO.setPrenomE("Mariem");
        etudiantDTO.setNomE("Necib");
        etudiantDTO.setOp(Option.GAMIX);

        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
        etudiant.setPrenomE(etudiantDTO.getPrenomE());
        etudiant.setNomE(etudiantDTO.getNomE());
        etudiant.setOp(etudiantDTO.getOp());

        when(etudiantService.updateEtudiant(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantRestController.updateEtudiant(etudiantDTO);

        // Assert
        assertEquals(etudiant, result);
    }


    @Test
    void testRemoveEtudiant() {
        // Arrange
        Integer idEtudiant = 1;

        // No need to return anything since it's a void method
        doNothing().when(etudiantService).removeEtudiant(idEtudiant);

        // Act and Assert
        assertDoesNotThrow(() -> etudiantRestController.removeEtudiant(idEtudiant));
    }

    @Test
    void testAssignEtudiantToDepartement() {
        // Arrange
        Integer etudiantId = 1;
        Integer departementId = 1;

        // No need to return anything since it's a void method
        doNothing().when(etudiantService).assignEtudiantToDepartement(etudiantId, departementId);

        // Act and Assert
        assertDoesNotThrow(() -> etudiantRestController.assignEtudiantToDepartement(etudiantId, departementId));
    }

    @Test
    void testFindByDepartement() {
        // Arrange
        Integer departementId = 1;
        List<Etudiant> etudiants = Collections.singletonList(new Etudiant());

        // Assume that the 'Etudiant' class has a default constructor
        when(etudiantService.findByDepartementIdDepartement(departementId)).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantRestController.findByDepartement(departementId);

        // Assert
        assertEquals(etudiants, result);
    }

    @Test
    void testRetrieveEtudiantsByContratSpecialite() {
        // Arrange
        Specialite specialite = Specialite.SECURITE;
        List<Etudiant> etudiants = Collections.singletonList(new Etudiant());

        when(etudiantService.retrieveEtudiantsByContratSpecialite(specialite)).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantRestController.retrieveEtudiantsByContratSpecialite(specialite);

        // Assert
        assertEquals(etudiants, result);
    }


    @Test
    void testRetrieveEtudiantsByContratSpecialiteSQL() {
        // Arrange
        String specialite = "SECURITE";
        List<Etudiant> etudiants = Collections.singletonList(new Etudiant());

        when(etudiantService.retrieveEtudiantsByContratSpecialiteSQL(specialite)).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantRestController.retrieveEtudiantsByContratSpecialiteSQL(specialite);

        // Assert
        assertEquals(etudiants, result);
    }

    @Test
    void testGetEtudiantsByDepartement() {
        // Arrange
        Integer idDepartement = 1;
        List<Etudiant> etudiants = Collections.singletonList(new Etudiant());

        when(etudiantService.getEtudiantsByDepartement(idDepartement)).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantRestController.getEtudiantsByDepartement(idDepartement);

        // Assert
        assertEquals(etudiants, result);
    }


    /*
    @Test
    void testAddAndAssignEtudiantToEquipeAndContract() {
        // Arrange
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(1);
        etudiantDTO.setPrenomE("John");
        etudiantDTO.setNomE("Doe");
        etudiantDTO.setOp(Option.GAMIX);
        Integer contratId = 1;
        Integer equipeId = 1;

        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
        etudiant.setPrenomE(etudiantDTO.getPrenomE());
        etudiant.setNomE(etudiantDTO.getNomE());
        etudiant.setOp(etudiantDTO.getOp());

        // No need to return anything since it's a void method
        doNothing().when(etudiantService).addAndAssignEtudiantToEquipeAndContract(any(Etudiant.class), eq(contratId), eq(equipeId));

        // Act and Assert
        assertDoesNotThrow(() -> etudiantRestController.addAndAssignEtudiantToEquipeAndContract(etudiantDTO, contratId, equipeId));
    }



}



 */