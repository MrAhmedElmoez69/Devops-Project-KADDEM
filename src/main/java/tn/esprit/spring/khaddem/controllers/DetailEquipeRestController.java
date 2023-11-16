package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.services.IDetailEquipeService;
import tn.esprit.spring.khaddem.dto.DetailEquipeDTO;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/detailequipe")
@CrossOrigin(origins = {"http://172.18.0.3:3000","http://172.18.0.2:3000", "http://localhost:3000"})
public class DetailEquipeRestController {
    IDetailEquipeService detailEquipeService;

    @GetMapping("/retrieve-all-detail-equipes")
    @ResponseBody
    public List<DetailEquipe> getDetailEquipes() {
        return detailEquipeService.retrieveAllDetailEquipes();
    }


    @GetMapping("/retrieve-detail-equipe/{detail-equipe-id}")
    @ResponseBody
    public DetailEquipe retrieveDetailEquipe(@PathVariable("detail-equipe-id") Integer detailEquipeId) {
        return detailEquipeService.retrieveDetailEquipe(detailEquipeId);
    }

    @PostMapping("/add-detail-equipe")
    @ResponseBody
    public DetailEquipe addDetailEquipe(@RequestBody DetailEquipeDTO detailEquipeDTO) {
        // Créez une instance de DetailEquipe en copiant les données pertinentes
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(detailEquipeDTO.getSalle());
        detailEquipe.setThematique(detailEquipeDTO.getThematique());

        // Appelez la méthode d'ajout de votre service
        return detailEquipeService.addDetailEquipe(detailEquipe);
    }


    @PutMapping("/update-detail-equipe")
    @ResponseBody
    public DetailEquipeDTO updateDetailEquipe(@RequestBody DetailEquipeDTO detailEquipeDTO) {
        // Créez une instance de DetailEquipe en copiant les données pertinentes
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setIdDetailEquipe(detailEquipeDTO.getIdDetailEquipe());
        detailEquipe.setSalle(detailEquipeDTO.getSalle());
        detailEquipe.setThematique(detailEquipeDTO.getThematique());

        // Appelez la méthode de mise à jour de votre service
        DetailEquipe updatedDetailEquipe = detailEquipeService.updateDetailEquipe(detailEquipe);

        // Créez une nouvelle instance de DetailEquipeDTO pour renvoyer en réponse
        DetailEquipeDTO updatedDTO = new DetailEquipeDTO();
        updatedDTO.setIdDetailEquipe(updatedDetailEquipe.getIdDetailEquipe());
        updatedDTO.setSalle(updatedDetailEquipe.getSalle());
        updatedDTO.setThematique(updatedDetailEquipe.getThematique());

        // Renvoyez le DTO en réponse à la requête
        return updatedDTO;
    }





}
