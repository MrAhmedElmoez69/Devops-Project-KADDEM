package SpringbootwithunitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Use the non-deprecated method
    }

    @Test
    void testRetrieveDepartement() {
        // Create a sample Departement
        Departement departement = new Departement();
        departement.setIdDepartement(1);
        departement.setNomDepart("Test Departement");

        // Mock repository behavior
        Mockito.when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Retrieve the Departement
        Departement retrievedDepartement = departementService.retrieveDepartement(1);

        // Check if the retrieved Departement exists and has the correct name
        assertNotNull(retrievedDepartement);
        assertEquals("Test Departement", retrievedDepartement.getNomDepart());
    }

}
