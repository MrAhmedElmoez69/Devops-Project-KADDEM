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
    void testRetrieveAllEtudiants_ShouldReturnEmptyList() {
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void testRetrieveEtudiant_ShouldReturnUniversiteById() {
        int etudiantId = 1;
        Etudiant expectedEtudiant = new Etudiant();
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(expectedEtudiant));

        Etudiant actualEtudiant = etudiantService.retrieveEtudiant(etudiantId);

        assertEquals(expectedEtudiant, actualEtudiant);
    }

    @Test
    void testAddEtudiant_ShouldAddEtudiant() {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setNomE("Test Etudiant");
        etudiantDTO.setPrenomE("Test Etudiant");
        etudiantDTO.setOp(Option.GAMIX);

        Etudiant addedEtudiant = new Etudiant();
        addedEtudiant.setNomE(etudiantDTO.getNomE());
        addedEtudiant.setOp(etudiantDTO.getOp());
        addedEtudiant.setPrenomE(etudiantDTO.getPrenomE());

        when(etudiantRepository.save(addedEtudiant)).thenReturn(addedEtudiant);

        Etudiant actualEtudiant = etudiantService.addEtudiant(addedEtudiant);

        assertEquals(addedEtudiant, actualEtudiant);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    public void testRetrieveEtudiant() {
        int etudiantId = 1;
        Etudiant expectedEtudiant = new Etudiant();
        when(etudiantRepository.findById(etudiantId)).thenReturn(java.util.Optional.of(expectedEtudiant));

        Etudiant actualEtudiant = etudiantService.retrieveEtudiant(etudiantId);

        assertEquals(expectedEtudiant, actualEtudiant);
    }

    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("New Etudiant");
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        assertEquals(etudiant, addedEtudiant);
    }

    @Test
    public void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Updated Etudiant");

        when(etudiantRepository.existsById(etudiant.getIdEtudiant())).thenReturn(true);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertEquals(etudiant, updatedEtudiant);
    }

    @Test
    public void testRemoveEtudiant() {
        int etudiantId = 1;
        doNothing().when(etudiantRepository).deleteById(etudiantId);

        etudiantService.removeEtudiant(etudiantId);

        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    public void testAssignEtudiantToDepartement() {
        int etudiantId = 1;
        int departementId = 1;

        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantId);

        when(etudiantRepository.findById(etudiantId)).thenReturn(java.util.Optional.of(etudiant));
        when(departementRepository.findById(departementId)).thenReturn(java.util.Optional.of(new Departement()));

        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
    }




}