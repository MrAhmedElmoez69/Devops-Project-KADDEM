package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Test
    void testRetrieveAllEtudiants_ShouldReturnEmptyList() {
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void testRetrieveEtudiant_ShouldReturnEtudiantById() {
        int etudiantId = 1;
        Etudiant expectedEtudiant = new Etudiant();
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(expectedEtudiant));

        Etudiant actualEtudiant = etudiantService.retrieveEtudiant(etudiantId);

        assertEquals(expectedEtudiant, actualEtudiant);
    }

    @Test
    void testAddEtudiant_ShouldAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("Test Etudiant");
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        assertEquals(etudiant, addedEtudiant);
    }

    @Test
    void testUpdateEtudiant_ShouldUpdateEtudiant() {
        int etudiantId = 1;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantId);
        etudiant.setNomE("Updated Etudiant");
        when(etudiantRepository.existsById(etudiant.getIdEtudiant())).thenReturn(true);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertEquals(etudiant, updatedEtudiant);
    }

    @Test
    void testRemoveEtudiant_ShouldRemoveEtudiantById() {
        int etudiantId = 1;
        doNothing().when(etudiantRepository).deleteById(etudiantId);

        etudiantService.removeEtudiant(etudiantId);

        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    void testAssignEtudiantToDepartement_ShouldAssignDepartementToEtudiant() {
        int etudiantId = 1;
        int departementId = 2;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantId);
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant));
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);

        assertEquals(departementId, etudiant.getDepartement().getIdDepartement());
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract_ShouldAddAndAssign() {
        int contratId = 1;
        int equipeId = 2;
        Etudiant etudiant = new Etudiant();
        when(contratRepository.findById(contratId)).thenReturn(Optional.of(new Contrat()));
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(new Equipe()));
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant resultEtudiant = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, contratId, equipeId);

        assertNotNull(resultEtudiant);
        // Add more assertions based on your implementation
    }

    @Test
    void testGetEtudiantsByDepartement_ShouldReturnEtudiants() {
        int departementId = 1;
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findByDepartementIdDepartement(departementId)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.getEtudiantsByDepartement(departementId);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void testFindByDepartementIdDepartement_ShouldReturnEtudiants() {
        int departementId = 1;
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findByDepartementIdDepartement(departementId)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.findByDepartementIdDepartement(departementId);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void testFindByEquipesNiveau_ShouldReturnEtudiants() {
        Niveau niveau = Niveau.JUNIOR;
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findByEquipesNiveau(niveau)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.findByEquipesNiveau(niveau);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void testRetrieveEtudiantsByContratSpecialite_ShouldReturnEtudiants() {
        Specialite specialite = Specialite.SECURITE;
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveEtudiantsByContratSpecialite(specialite);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void testRetrieveEtudiantsByContratSpecialiteSQL_ShouldReturnEtudiants() {
        String specialite = "SECURITE";
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveEtudiantsByContratSpecialiteSQL(specialite);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void testRetrieveEtudiant_ThrowExceptionForInvalidId() {
        int etudiantId = 1;
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> etudiantService.retrieveEtudiant(etudiantId));
    }

    @Test
    void testUpdateEtudiant_ThrowExceptionForInvalidId() {
        int etudiantId = 1;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantId);
        etudiant.setNomE("Updated Etudiant");
        when(etudiantRepository.existsById(etudiant.getIdEtudiant())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> etudiantService.updateEtudiant(etudiant));
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract_ThrowExceptionForInvalidIds() {
        int contratId = 1;
        int equipeId = 2;
        Etudiant etudiant = new Etudiant();
        when(contratRepository.findById(contratId)).thenReturn(Optional.empty());
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(new Equipe()));

        assertThrows(NoSuchElementException.class, () -> etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, contratId, equipeId));
    }

    @Test
    void testGetEtudiantsByDepartement_ThrowExceptionForInvalidId() {
        int departementId = 1;
        when(etudiantRepository.findByDepartementIdDepartement(departementId)).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> etudiantService.getEtudiantsByDepartement(departementId));
    }









}
