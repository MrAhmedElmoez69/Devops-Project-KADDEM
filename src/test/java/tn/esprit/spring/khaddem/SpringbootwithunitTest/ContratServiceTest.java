package tn.esprit.spring.khaddem.SpringbootwithunitTest;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ContratServiceTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

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

        Contrat actualContrat = contratService.addContrat(addedContrat);

        assertEquals(addedContrat, actualContrat);
    }
}

