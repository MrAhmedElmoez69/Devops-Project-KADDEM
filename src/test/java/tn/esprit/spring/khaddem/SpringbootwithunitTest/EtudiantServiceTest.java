package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private ContratRepository contratRepository;
    private EquipeRepository equipeRepository;

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
    public void testFindByDepartementIdDepartement() {
        int departementId = 1;
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findByDepartementIdDepartement(departementId)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.findByDepartementIdDepartement(departementId);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    public void testFindByEquipesNiveau() {
        Niveau niveau = Niveau.JUNIOR; // Replace with your actual enum value
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findByEquipesNiveau(niveau)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.findByEquipesNiveau(niveau);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    public void testRetrieveEtudiantsByContratSpecialite() {
        Specialite specialite = Specialite.SECURITE; // Replace with your actual enum value
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveEtudiantsByContratSpecialite(specialite);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    public void testRetrieveEtudiantsByContratSpecialiteSQL() {
        String specialite = "SECURITE"; // Replace with your actual value
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.retrieveEtudiantsByContratSpecialiteSQL(specialite);

        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    public void testGetEtudiantsByDepartement() {
        int departementId = 1;
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        when(etudiantRepository.findByDepartementIdDepartement(departementId)).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.getEtudiantsByDepartement(departementId);

        assertEquals(expectedEtudiants, actualEtudiants);
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
        when(etudiantRepository.existsById(etudiantId)).thenReturn(true);
        doNothing().when(etudiantRepository).deleteById(etudiantId);

        etudiantService.removeEtudiant(etudiantId);

        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    public void testAssignEtudiantToDepartement() {
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
    public void testAddAndAssignEtudiantToEquipeAndContract() {
        int contratId = 1;
        int equipeId = 2;
        Etudiant etudiant = new Etudiant();
        // Set up your etudiant, contrat, and equipe data
        when(contratRepository.findById(contratId)).thenReturn(Optional.of(new Contrat()));
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(new Equipe()));
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant resultEtudiant = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, contratId, equipeId);

        assertNotNull(resultEtudiant);
        // Add more assertions based on your implementation
    }






}
