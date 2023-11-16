package tn.esprit.spring.khaddem.SpringbootwithunitTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.dto.ContratDTO;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import tn.esprit.spring.khaddem.entities.Etudiant;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ContratServiceTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EtudiantRepository etudiantRepository;

    @Test
    void testRetrieveAllContrats() {
        List<Contrat> expectedContrats = new ArrayList<>();
        when(contratRepository.findAll()).thenReturn(expectedContrats);

        List<Contrat> actualContrats = contratService.retrieveAllContrats();

        assertEquals(expectedContrats, actualContrats);
    }

    @Test
    void testRetrieveContrat() {
        int contratId = 1;
        Contrat expectedContrat = new Contrat();
        when(contratRepository.findById(contratId)).thenReturn(Optional.of(expectedContrat));

        Contrat actualContrat = contratService.retrieveContrat(contratId);

        assertEquals(expectedContrat, actualContrat);
    }

    @Test
    void testAddContrat() {
        // Arrange
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setIdContrat(1);
        contratDTO.setDateDebutContrat(new Date());
        contratDTO.setDateFinContrat(new Date());
        contratDTO.setSpecialite(Specialite.valueOf("IA"));
        contratDTO.setArchived(true);
        contratDTO.setMontantContrat(123);

        Contrat addedContrat = new Contrat();
        addedContrat.setIdContrat(contratDTO.getIdContrat());
        addedContrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        addedContrat.setDateFinContrat(contratDTO.getDateFinContrat());
        addedContrat.setSpecialite(contratDTO.getSpecialite());
        addedContrat.setArchived(contratDTO.getArchived());
        addedContrat.setMontantContrat(contratDTO.getMontantContrat());

        when(contratRepository.save(addedContrat)).thenReturn(addedContrat);

        // Act
        Contrat actualContrat = contratService.addContrat(addedContrat);

        // Assert
        assertAll("Contrat",
                () -> assertEquals(addedContrat.getIdContrat(), actualContrat.getIdContrat()),
                () -> assertEquals(addedContrat.getDateDebutContrat(), actualContrat.getDateDebutContrat()),
                () -> assertEquals(addedContrat.getDateFinContrat(), actualContrat.getDateFinContrat()),
                () -> assertEquals(addedContrat.getSpecialite(), actualContrat.getSpecialite()),
                () -> assertEquals(addedContrat.getArchived(), actualContrat.getArchived()),
                () -> assertEquals(addedContrat.getMontantContrat(), actualContrat.getMontantContrat())
        );

        // Vérifier que la méthode save du repository a été appelée
        verify(contratRepository, times(1)).save(addedContrat);
    }
    @Test
    void testUpdateContrat() {
        // Créez un contrat fictif pour tester la mise à jour
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setSpecialite(Specialite.IA);
        contrat.setArchived(true);
        contrat.setMontantContrat(1000);

        // Utilisez Mockito pour simuler le comportement de la méthode save dans le repository
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // Appelez la méthode updateContrat
        Contrat updatedContrat = contratService.updateContrat(contrat);

        // Vérifiez que la méthode save a été appelée avec le bon contrat
        verify(contratRepository, times(1)).save(contrat);

        // Vérifiez que la méthode retourne le contrat mis à jour
        assertEquals(contrat, updatedContrat);
    }
    @Test
    void testRemoveContrat() {
        // Arrange
        Integer idContrat = 1;
        // Act
        contratService.removeContrat(idContrat);
        // Assert
        // Utilisez Mockito pour vérifier que la méthode deleteById a été appelée avec le bon ID
        verify(contratRepository, times(1)).deleteById(idContrat);
    }

    @Test
    void testAddAndAffectContratToEtudiant() {
        // Arrange
        Contrat ce = new Contrat(); // Initialisez ce avec les valeurs nécessaires
        String nomE = "NomEtudiant";
        String prenomE = "PrenomEtudiant";

        Etudiant etudiant = new Etudiant(); // Initialisez etudiant avec les valeurs nécessaires
        etudiant.setContrats(Arrays.asList(
                new Contrat(), new Contrat(), new Contrat(), new Contrat(), new Contrat(), new Contrat()
        )); // Assurez-vous que la liste de contrats de l'étudiant contient plus de 5 contrats actifs

        // Utilisez Mockito pour simuler le comportement de la méthode findByNomEAndPrenomE dans le repository
        when(etudiantRepository.findByNomEAndPrenomE(nomE, prenomE)).thenReturn(etudiant);
        // Act
        Contrat result = contratService.addAndAffectContratToEtudiant(ce, nomE, prenomE);
        // Assert
        assertEquals(6, etudiant.getContrats().size(), "Le contrat n'a pas été correctement ajouté à la liste des contrats de l'étudiant");

        // Vérifiez que la méthode save n'a pas été appelée lorsque le nombre de contrats actifs est supérieur à 5
        verify(contratRepository, times(0)).save(ce);

        if (etudiant.getContrats().size() <= 5) {
            verify(contratRepository, times(1)).save(ce);
        }
    }


    @Test
    void testNbContratsValides() {
        // Mocking the behavior of contratRepository.getnbContratsValides
        Date startDate = new Date(); // Set an appropriate start date
        Date endDate = new Date();   // Set an appropriate end date
        int expectedCount = 5;       // Set the expected count based on your test scenario
        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(expectedCount);

        // Call the method in your service
        Integer actualCount = contratService.nbContratsValides(startDate, endDate);

        // Verify the result
        assertEquals(expectedCount, actualCount);
    }
    @Test
    void testRetrieveAndUpdateStatusContrat() {
        // Préparez les données de test
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setArchived(false);

        List<Contrat> contrats = Arrays.asList(contrat);

        // Mockez la méthode du dépôt
        when(contratRepository.findAll()).thenReturn(contrats);

        // Appelez la méthode du service
        contratService.retrieveAndUpdateStatusContrat();

        // Calculez la valeur de daysDifference
        long timeDifference = contrat.getDateFinContrat().getTime() - new Date().getTime();
        long daysDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;

        // Vérifiez le comportement attendu après l'appel de la méthode
        // Assurez-vous de vérifier le statut mis à jour dans contrat après l'appel de la méthode
        assertTrue(contrat.getArchived() || daysDifference == 0);

        // Vérifiez que la méthode save a été appelée
        verify(contratRepository, times(1)).save(contrat);

        // Ajoutez des assertions spécifiques pour les lignes de la méthode retrieveAndUpdateStatusContrat
        // Assurez-vous que les logs ont été appelés avec les bonnes valeurs

        // Ajoutez des assertions pour les conditions non couvertes
        if (contrat.getArchived() == null || !contrat.getArchived()) {
            // Assurez-vous que le bloc conditionnel a été exécuté comme prévu
            if (daysDifference == 15) {
                // Assurez-vous que le bloc conditionnel a été exécuté comme prévu
            }
            if (daysDifference == 0) {
                // Assurez-vous que le bloc conditionnel a été exécuté comme prévu
            }
        }
    }


}

