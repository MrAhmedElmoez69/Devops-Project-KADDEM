package tn.esprit.spring.khaddem.controllers;
/*

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.services.IContratService;
import java.util.List;
import tn.esprit.spring.khaddem.dto.ContratDTO;


@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {
    IContratService contratService;

    // http://localhost:8089/Kaddem/contrat/retrieve-all-contrats
    @GetMapping("/retrieve-all-contrats")
    @ResponseBody
    public List<Contrat> getContrats() {
        return contratService.retrieveAllContrats();
    }


    // http://localhost:8089/Kaddem/contrat/retrieve-contrat/8
    @GetMapping("/retrieve-contrat/{contrat-id}")
    @ResponseBody
    public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
        return contratService.retrieveContrat(contratId);
    }

    // http://localhost:8089/Kaddem/contrat/add-contrat
    @PostMapping("/add-contrat")
    @ResponseBody
    public Contrat addContrat(@RequestBody ContratDTO contratDTO) {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(contratDTO.getIdContrat());
        contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        contrat.setDateFinContrat(contratDTO.getDateFinContrat());
        contrat.setSpecialite(contratDTO.getSpecialite());
        contrat.setArchived(contratDTO.getArchived());
        contrat.setMontantContrat(contratDTO.getMontantContrat());

        contratService.addContrat(contrat);

        return contrat;
    }






}


 */