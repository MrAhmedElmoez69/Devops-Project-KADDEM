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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

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
    void testUpdateEtudiantExists() {
        Integer idEtudiant = 1;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(idEtudiant);

        when(etudiantRepository.existsById(idEtudiant)).thenReturn(true);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.updateEtudiant(etudiant);

        assertEquals(etudiant, result);
        verify(etudiantRepository).existsById(idEtudiant);
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void testUpdateEtudiantNotExists() {
        Integer idEtudiant = 1;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(idEtudiant);

        when(etudiantRepository.existsById(idEtudiant)).thenReturn(false);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.updateEtudiant(etudiant);

        assertEquals(etudiant, result);
        verify(etudiantRepository).existsById(idEtudiant);
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void testRetrieveEtudiantExists() {
        Integer idEtudiant = 1;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(idEtudiant);

        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(idEtudiant);

        assertEquals(etudiant, result);
        verify(etudiantRepository).findById(idEtudiant);
    }

    @Test
    void testRetrieveEtudiantNotExists() {
        Integer idEtudiant = 1;

        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> etudiantService.retrieveEtudiant(idEtudiant));
        verify(etudiantRepository).findById(idEtudiant);
    }

}
