package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

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


}
