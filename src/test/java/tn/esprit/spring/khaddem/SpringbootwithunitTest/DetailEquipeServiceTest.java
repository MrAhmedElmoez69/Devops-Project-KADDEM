package tn.esprit.spring.khaddem.SpringbootwithunitTest;
/*
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.dto.DetailEquipeDTO;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.khaddem.services.DetailEquipeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DetailEquipeServiceTest {

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeService;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @Test
    void testRetrieveAllDetailEquipes() {
        List<DetailEquipe> expectedDetailEquipes = new ArrayList<>();
        when(detailEquipeRepository.findAll()).thenReturn(expectedDetailEquipes);

        List<DetailEquipe> actualDetailEquipes = detailEquipeService.retrieveAllDetailEquipes();

        assertEquals(expectedDetailEquipes, actualDetailEquipes);
    }

    @Test
    void testRetrieveDetailEquipe() {
        int detailEquipeId = 1;
        DetailEquipe expectedDetailEquipe = new DetailEquipe();
        when(detailEquipeRepository.findById(detailEquipeId)).thenReturn(java.util.Optional.of(expectedDetailEquipe));

        DetailEquipe actualDetailEquipe = detailEquipeService.retrieveDetailEquipe(detailEquipeId);

        assertEquals(expectedDetailEquipe, actualDetailEquipe);
    }

    @Test
    void testAddDetailEquipe() {
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setSalle(123);
        detailEquipeDTO.setThematique("Test Thematique");

        DetailEquipe addedDetailEquipe = new DetailEquipe();
        addedDetailEquipe.setSalle(detailEquipeDTO.getSalle());
        addedDetailEquipe.setThematique(detailEquipeDTO.getThematique());

        when(detailEquipeRepository.save(addedDetailEquipe)).thenReturn(addedDetailEquipe);

        DetailEquipe actualDetailEquipe = detailEquipeService.addDetailEquipe(addedDetailEquipe);

        assertEquals(addedDetailEquipe, actualDetailEquipe);
    }
}

 */
