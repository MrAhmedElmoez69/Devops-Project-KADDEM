package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Test
    void testRetrieveAllEtudiants() {
        // Mock the behavior of the repository
        List<Etudiant> etudiantList = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        // Call the service method
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Verify the result
        assertEquals(etudiantList, result);
    }

    @Test
    void testAddEtudiant() {
        // Mock the behavior of the repository
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Call the service method
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Verify the result
        assertEquals(etudiant, result);
    }

    @Test
    void testUpdateEtudiant() {
        // Mock behavior
        when(etudiantRepository.existsById(anyInt())).thenReturn(true);

        // Sample data
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);

        // Call the service method
        Etudiant result = etudiantService.updateEtudiant(etudiant);

        // Verify the behavior
        verify(etudiantRepository).existsById(eq(1));
        verify(etudiantRepository).save(eq(etudiant));
        assertEquals(etudiant, result);
    }

    @Test
    void testRetrieveEtudiant() {
        // Mock behavior
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(new Etudiant()));

        // Call the service method
        Etudiant result = etudiantService.retrieveEtudiant(1);

        // Verify the behavior
        verify(etudiantRepository).findById(eq(1));
        assertEquals(new Etudiant(), result);
    }

    @Test
    void testRetrieveEtudiantNotFound() {
        // Mock behavior
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Call the service method
        assertThrows(EntityNotFoundException.class, () -> etudiantService.retrieveEtudiant(1));

        // Verify the behavior
        verify(etudiantRepository).findById(eq(1));
    }

    @Test
    void testRemoveEtudiant() {
        // Call the service method
        etudiantService.removeEtudiant(1);

        // Verify the behavior
        verify(etudiantRepository).deleteById(eq(1));
    }

    @Test
    void testAssignEtudiantToDepartement() {
        // Mock behavior
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(new Etudiant()));
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(new Departement()));

        // Call the service method
        etudiantService.assignEtudiantToDepartement(1, 2);

        // Verify the behavior
        verify(etudiantRepository).findById(eq(1));
        verify(departementRepository).findById(eq(2));
        verify(etudiantRepository).save(any(Etudiant.class));
    }

    @Test
    void testFindByDepartementIdDepartement() {
        // Mock behavior
        when(etudiantRepository.findByDepartementIdDepartement(anyInt())).thenReturn(Collections.singletonList(new Etudiant()));

        // Call the service method
        List<Etudiant> result = etudiantService.findByDepartementIdDepartement(1);

        // Verify the behavior
        verify(etudiantRepository).findByDepartementIdDepartement(eq(1));
        assertEquals(1, result.size());
    }

    @Test
    void testFindByEquipesNiveau() {
        // Mock behavior
        when(etudiantRepository.findByEquipesNiveau(any(Niveau.class))).thenReturn(Collections.singletonList(new Etudiant()));

        // Call the service method
        List<Etudiant> result = etudiantService.findByEquipesNiveau(Niveau.JUNIOR);

        // Verify the behavior
        verify(etudiantRepository).findByEquipesNiveau(eq(Niveau.JUNIOR));
        assertEquals(1, result.size());
    }

    @Test
    void testRetrieveEtudiantsByContratSpecialite() {
        // Mock behavior
        when(etudiantRepository.retrieveEtudiantsByContratSpecialite(any(Specialite.class)))
                .thenReturn(Collections.singletonList(new Etudiant()));

        // Call the service method
        List<Etudiant> result = etudiantService.retrieveEtudiantsByContratSpecialite(Specialite.SECURITE);

        // Verify the behavior
        verify(etudiantRepository).retrieveEtudiantsByContratSpecialite(eq(Specialite.SECURITE));
        assertEquals(1, result.size());
    }

    @Test
    void testRetrieveEtudiantsByContratSpecialiteSQL() {
        // Mock behavior
        when(etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(anyString()))
                .thenReturn(Collections.singletonList(new Etudiant()));

        // Call the service method
        List<Etudiant> result = etudiantService.retrieveEtudiantsByContratSpecialiteSQL("SECURITE");

        // Verify the behavior
        verify(etudiantRepository).retrieveEtudiantsByContratSpecialiteSQL(eq("SECURITE"));
        assertEquals(1, result.size());
    }

    @Test
    void testGetEtudiantsByDepartement() {
        // Mock behavior
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(new Departement()));

        // Call the service method
        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        // Verify the behavior
        verify(departementRepository).findById(eq(1));
        assertEquals(Collections.emptyList(), result); // Assuming empty list when department is found
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract_ContractNotFound() {
        // Mock behavior
        when(contratRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(equipeRepository.findById(anyInt())).thenReturn(Optional.of(new Equipe()));

        // Call the service method
        assertThrows(NoSuchElementException.class, () ->
                etudiantService.addAndAssignEtudiantToEquipeAndContract(new Etudiant(), 1, 1)
        );

        // Verify the behavior
        verify(contratRepository).findById(eq(1));
        verify(equipeRepository, never()).findById(anyInt());
        verify(etudiantRepository, never()).save(any(Etudiant.class));
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract_EquipeNotFound() {
        // Mock behavior
        when(contratRepository.findById(anyInt())).thenReturn(Optional.of(new Contrat()));
        when(equipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Call the service method
        assertThrows(NoSuchElementException.class, () ->
                etudiantService.addAndAssignEtudiantToEquipeAndContract(new Etudiant(), 1, 1)
        );

        // Verify the behavior
        verify(contratRepository).findById(eq(1));
        verify(equipeRepository).findById(eq(1));
        verify(etudiantRepository, never()).save(any(Etudiant.class));
    }

    @Test
    void testUpdateEtudiant_NotFound() {
        // Mock behavior
        when(etudiantRepository.existsById(anyInt())).thenReturn(false);

        // Call the service method
        assertThrows(EntityNotFoundException.class, () ->
                etudiantService.updateEtudiant(new Etudiant())
        );

        // Verify the behavior
        verify(etudiantRepository).existsById(eq(1));
        verify(etudiantRepository, never()).save(any(Etudiant.class));
    }

    @Test
    void testGetEtudiantsByDepartement_NoStudents() {
        // Mock behavior
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(new Departement()));

        // Call the service method
        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        // Verify the behavior
        verify(departementRepository).findById(eq(1));
        assertEquals(Collections.emptyList(), result);
    }













}
