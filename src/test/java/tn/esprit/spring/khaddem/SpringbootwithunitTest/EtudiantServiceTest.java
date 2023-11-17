package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private DepartementRepository departementRepository;

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("New Etudiant");
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        assertEquals(etudiant, addedEtudiant);
    }








}