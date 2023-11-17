package tn.esprit.spring.khaddem.controllers;
/*
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.IEquipeService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
    IEquipeService equipeService;
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {

        return equipeService.retrieveAllEquipes();
    }



    // http://localhost:8089/Kaddem/equipe/add-equipe

    @PostMapping("/add-equipe")
    @ResponseBody
    public Equipe addEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipeService.addEquipe(equipe);
        return equipe;
    }


}
 */