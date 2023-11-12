package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.ArgumentMatchers.any;
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
    private DepartementRepository departementRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    private Etudiant sampleEtudiant;

    @BeforeEach
    void setUp() {
        // Initialize a sample Etudiant for testing
        sampleEtudiant = new Etudiant();
        sampleEtudiant.setIdEtudiant(1);
        sampleEtudiant.setPrenomE("John");
        sampleEtudiant.setNomE("Doe");
        sampleEtudiant.setOp(Option.GAMIX);

        // Mocking behavior of etudiantRepository
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(sampleEtudiant));
        when(etudiantRepository.findAll()).thenReturn(Collections.singletonList(sampleEtudiant));
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertEquals(1, etudiants.size());
        assertEquals(sampleEtudiant, etudiants.get(0));
    }

    @Test
    public void testAddEtudiant() {
        Etudiant newEtudiant = new Etudiant();
        newEtudiant.setPrenomE("Jane");
        newEtudiant.setNomE("Doe");
        newEtudiant.setOp(Option.GAMIX);

        when(etudiantRepository.save(newEtudiant)).thenReturn(newEtudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(newEtudiant);

        assertEquals(newEtudiant, addedEtudiant);
        verify(etudiantRepository, times(1)).save(newEtudiant);
    }

    @Test
    public void testUpdateEtudiant() {
        Etudiant updatedEtudiant = new Etudiant();
        updatedEtudiant.setIdEtudiant(1);
        updatedEtudiant.setPrenomE("UpdatedName");
        updatedEtudiant.setNomE("UpdatedLastName");
        updatedEtudiant.setOp(Option.GAMIX);

        when(etudiantRepository.findById(1)).thenReturn(Optional.of(sampleEtudiant));
        when(etudiantRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Etudiant result = etudiantService.updateEtudiant(updatedEtudiant);

        assertEquals(updatedEtudiant.getPrenomE(), result.getPrenomE());
        assertEquals(updatedEtudiant.getNomE(), result.getNomE());
        assertEquals(updatedEtudiant.getOp(), result.getOp());
    }

    @Test
    public void testRetrieveEtudiant() {
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);
        assertEquals(sampleEtudiant, retrievedEtudiant);
    }

    @Test
    public void testRemoveEtudiant() {
        etudiantService.removeEtudiant(1);
        verify(etudiantRepository, times(1)).deleteById(1);
    }

    @Test
    public void testAssignEtudiantToDepartement() {
        Departement departement = new Departement();
        departement.setIdDepartement(1);

        when(etudiantRepository.findById(1)).thenReturn(Optional.of(sampleEtudiant));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        when(etudiantRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        etudiantService.assignEtudiantToDepartement(1, 1);

        assertEquals(departement, sampleEtudiant.getDepartement());
    }











}
