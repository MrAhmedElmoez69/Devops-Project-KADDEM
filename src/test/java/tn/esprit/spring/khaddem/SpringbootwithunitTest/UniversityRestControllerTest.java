package tn.esprit.spring.khaddem.SpringbootwithunitTest;
/*
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.controllers.UniversiteRestController;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.services.IUniversiteService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class UniversiteRestControllerTest {

    @InjectMocks
    private UniversiteRestController universiteController;

    @Mock
    private IUniversiteService universiteService;

    @Test
    void testRetrieveUniversite() throws Exception {
        int universiteId = 1;

        // Mock the behavior of retrieveUniversite method
        when(universiteService.retrieveUniversite(universiteId)).thenReturn(new Universite());

        universiteController.retrieveUniversite(universiteId);

        // Verify that the service method was called with the correct argument
        verify(universiteService, times(1)).retrieveUniversite(universiteId);
    }


}


 */