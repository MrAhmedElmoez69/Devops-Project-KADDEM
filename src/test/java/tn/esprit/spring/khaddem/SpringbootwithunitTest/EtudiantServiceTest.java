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











}
