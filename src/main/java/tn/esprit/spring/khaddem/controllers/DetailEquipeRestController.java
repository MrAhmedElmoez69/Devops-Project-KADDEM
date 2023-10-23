package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.services.IDetailEquipeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/detailequipe")
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
    public DetailEquipe addDetailEquipe(@RequestBody DetailEquipe detailEquipe) {
        return detailEquipeService.addDetailEquipe(detailEquipe);
    }

    @PutMapping("/update-detail-equipe")
    @ResponseBody
    public DetailEquipe updateDetailEquipe(@RequestBody DetailEquipe detailEquipe) {
        return detailEquipeService.updateDetailEquipe(detailEquipe);
    }

}
