package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.controllers.EquipeRestController;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipeServiceTest {

    @InjectMocks
    public EquipeServiceImpl equipeService;

    EquipeRepository equipeRepository = Mockito.mock(EquipeRepository.class);

    @Test
    public void testRetrieveAllEquipes(){
        List<Equipe> expectedEquipes = new ArrayList<>();
        when(equipeRepository.findAll()).thenReturn(expectedEquipes);
        List<Equipe> actyalEquipes = equipeService.retrieveAllEquipes();
        assertEquals(expectedEquipes, actyalEquipes);
    }


    @Test
    public void testRertrieveEquipe(){
        int equipeId = 1;
        Equipe expectedEquipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(java.util.Optional.of(expectedEquipe));
        Equipe actualEquipe = equipeService.retrieveEquipe(equipeId);
        assertEquals(expectedEquipe, actualEquipe);
    }

    @Test
    public void testAddEqiupe(){
        Equipe equipe = new Equipe();
        equipe.setNomEquipe("New Equipe");
        when(equipeRepository.save(equipe)).thenReturn(equipe);
        Equipe addEquipe = equipeService.addEquipe(equipe);
        assertEquals(equipe, addEquipe);
    }

    @Test
    public void testUpdateEquipe() {
        int equipeId = 2;
        String updatedName = "Updated Equipe";
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setIdEquipe(equipeId);
        equipeDTO.setNomEquipe(updatedName);

        Equipe existingEquipe = new Equipe();
        existingEquipe .setIdEquipe(equipeId);
        existingEquipe .setNomEquipe("Old Department");

        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(existingEquipe));
        EquipeRestController equipeController = new EquipeRestController(equipeService);
        Equipe updateEquipe= equipeController.updateEquipe(equipeDTO);
        assertEquals(updatedName, updateEquipe.getNomEquipe());
    }
}
