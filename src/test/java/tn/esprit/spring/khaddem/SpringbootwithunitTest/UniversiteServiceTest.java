package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Test
    void testRetrieveAllUniversites_ShouldReturnEmptyList() {
        List<Universite> expectedUniversites = new ArrayList<>();
        when(universiteRepository.findAll()).thenReturn(expectedUniversites);

        List<Universite> actualUniversites = universiteService.retrieveAllUniversites();

        assertEquals(expectedUniversites, actualUniversites);
    }

    @Test
    void testRetrieveUniversite_ShouldReturnUniversiteById() {
        int universiteId = 1;
        Universite expectedUniversite = new Universite();
        when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(expectedUniversite));

        Universite actualUniversite = universiteService.retrieveUniversite(universiteId);

        assertEquals(expectedUniversite, actualUniversite);
    }

    @Test
    void testAddUniversite_ShouldAddUniversite() {
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setName("Test University");
        universiteDTO.setLocation("Test Location");

        Universite addedUniversite = new Universite();
        addedUniversite.setNomUniv(universiteDTO.getName());
        addedUniversite.setAdresse(universiteDTO.getLocation());

        when(universiteRepository.save(addedUniversite)).thenReturn(addedUniversite);

        Universite actualUniversite = universiteService.addUniversite(addedUniversite);

        assertEquals(addedUniversite, actualUniversite);
    }
}

