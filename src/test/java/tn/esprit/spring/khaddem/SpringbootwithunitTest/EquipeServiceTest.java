package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipeServiceTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    EquipeRepository equipeRepository = Mockito.mock(EquipeRepository.class);

    @Test
    void testRetrieveAllEquipes(){
        List<Equipe> expectedEquipes = new ArrayList<>();
        when(equipeRepository.findAll()).thenReturn(expectedEquipes);
        List<Equipe> actyalEquipes = equipeService.retrieveAllEquipes();
        assertEquals(expectedEquipes, actyalEquipes);
    }


    @Test
    void testRertrieveEquipe(){
        int equipeId = 1;
        Equipe expectedEquipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(java.util.Optional.of(expectedEquipe));
        Equipe actualEquipe = equipeService.retrieveEquipe(equipeId);
        assertEquals(expectedEquipe, actualEquipe);
    }

    @Test
    void testAddEqiupe(){
        Equipe equipe = new Equipe();
        equipe.setNomEquipe("New Equipe");
        when(equipeRepository.save(equipe)).thenReturn(equipe);
        Equipe addEquipe = equipeService.addEquipe(equipe);
        assertEquals(equipe, addEquipe);
    }
}
