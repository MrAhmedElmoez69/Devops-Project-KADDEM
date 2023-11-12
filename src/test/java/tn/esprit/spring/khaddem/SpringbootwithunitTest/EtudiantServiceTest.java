package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

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

    // Additional tests can be added for other methods as needed...
}
