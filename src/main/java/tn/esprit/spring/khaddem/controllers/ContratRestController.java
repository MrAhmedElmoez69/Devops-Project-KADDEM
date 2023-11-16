package tn.esprit.spring.khaddem.controllers;


import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.services.IContratService;
import java.util.Date;
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



    // http://localhost:8089/Kaddem/contrat/update-contrat
    @PutMapping("/update-contrat")
    @ResponseBody
    public ContratDTO updateContrat(@RequestBody ContratDTO contratDTO) {
        // Transformez le ContratDTO en entité Contrat si nécessaire
        Contrat contrat = new Contrat();
        contrat.setIdContrat(contratDTO.getIdContrat());
        contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        contrat.setDateFinContrat(contratDTO.getDateFinContrat());
        contrat.setSpecialite(contratDTO.getSpecialite());
        contrat.setArchived(contratDTO.getArchived());
        contrat.setMontantContrat(contratDTO.getMontantContrat());

        // Utilisez le service pour mettre à jour le contrat
        contrat = contratService.updateContrat(contrat);

        // Transformez l'entité Contrat en ContratDTO si nécessaire
        ContratDTO resultDTO = new ContratDTO();
        resultDTO.setIdContrat(contrat.getIdContrat());
        resultDTO.setDateDebutContrat(contrat.getDateDebutContrat());
        resultDTO.setDateFinContrat(contrat.getDateFinContrat());
        resultDTO.setSpecialite(contrat.getSpecialite());
        resultDTO.setArchived(contrat.getArchived());
        resultDTO.setMontantContrat(contrat.getMontantContrat());

        return resultDTO;
    }


    // http://localhost:8089/Kaddem/contrat/addAndAffectContratToEtudiant/salah/ahmed
    @PostMapping("/addAndAffectContratToEtudiant/{nomE}/{prenomE}")
    @ResponseBody
    public ContratDTO addAndAffectContratToEtudiant(@RequestBody ContratDTO contratDTO, @PathVariable("nomE") String nomE, @PathVariable("prenomE") String prenomE) {
        // Transformez le ContratDTO en entité Contrat si nécessaire
        Contrat contrat = new Contrat();
        contrat.setIdContrat(contratDTO.getIdContrat());
        contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        contrat.setDateFinContrat(contratDTO.getDateFinContrat());
        contrat.setSpecialite(contratDTO.getSpecialite());
        contrat.setArchived(contratDTO.getArchived());
        contrat.setMontantContrat(contratDTO.getMontantContrat());

        // Utilisez le service pour ajouter et affecter le contrat à l'étudiant
        contrat = contratService.addAndAffectContratToEtudiant(contrat, nomE, prenomE);

        // Transformez l'entité Contrat en ContratDTO si nécessaire
        ContratDTO resultDTO = new ContratDTO();
        resultDTO.setIdContrat(contrat.getIdContrat());
        resultDTO.setDateDebutContrat(contrat.getDateDebutContrat());
        resultDTO.setDateFinContrat(contrat.getDateFinContrat());
        resultDTO.setSpecialite(contrat.getSpecialite());
        resultDTO.setArchived(contrat.getArchived());
        resultDTO.setMontantContrat(contrat.getMontantContrat());

        return resultDTO;
    }


    //The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
    @GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
    public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.nbContratsValides(startDate, endDate);
    }

    //Only no-arg methods may be annotated with @Scheduled
    @Scheduled(cron="0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    //  @Scheduled(cron="45 * * * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    @PutMapping(value = "/majStatusContrat")
    public void majStatusContrat (){
        contratService.retrieveAndUpdateStatusContrat();
    }



}