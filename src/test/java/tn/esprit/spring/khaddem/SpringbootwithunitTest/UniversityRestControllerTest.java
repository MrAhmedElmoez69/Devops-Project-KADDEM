package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.controllers.UniversiteRestController;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.services.IUniversiteService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversiteRestControllerTest {

    @InjectMocks
    private UniversiteRestController universiteController;

    @Mock
    private IUniversiteService universiteService;

    @Test
    void testRetrieveUniversite() throws Exception {
        int universiteId = 1;

        when(universiteService.retrieveUniversite(universiteId)).thenReturn(new Universite());

        universiteController.retrieveUniversite(universiteId);

        verify(universiteService, times(1)).retrieveUniversite(universiteId);
    }

    @Test
    void testAssignUniversiteToDepartement() throws Exception {
        int universiteId = 1;
        int departementId = 1;

        doNothing().when(universiteService).assignUniversiteToDepartement(universiteId, departementId);

        universiteController.assignUniversiteToDepartement(universiteId, departementId);

        verify(universiteService, times(1)).assignUniversiteToDepartement(universiteId, departementId);
    }
}