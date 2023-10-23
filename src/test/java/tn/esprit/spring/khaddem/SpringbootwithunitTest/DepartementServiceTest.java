package tn.esprit.spring.khaddem.SpringbootwithunitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Test
    void testRetrieveAllDepartements() {
        List<Departement> expectedDepartements = new ArrayList<>();
        when(departementRepository.findAll()).thenReturn(expectedDepartements);

        List<Departement> actualDepartements = departementService.retrieveAllDepartements();

        assertEquals(expectedDepartements, actualDepartements);
    }
}
