package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.services.IUniversiteService;

import java.util.List;

@Tag(name = "Gestion des universités")
@RestController
@RequestMapping("/universite")
public class UniversiteRestController {
    @Autowired
    IUniversiteService universiteService;

    // http://localhost:8089/Kaddem/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    @Operation(description = "récupérer la liste des universités")
    @ResponseBody
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    // http://localhost:8089/Kaddem/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universite-id}")
    @Operation(description = "récupérer une université par son id")
    @ResponseBody
    public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    // http://localhost:8089/Kaddem/universite/add-universite
    @PostMapping("/add-universite")
    @Operation(description = "ajouter une université")
    @ResponseBody
    public Universite addUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setNomUniv(universiteDTO.getName());
        universite.setAdresse(universiteDTO.getLocation());
        return universiteService.addUniversite(universite);
    }


    // http://localhost:8089/Kaddem/universite/update-universite
    @PutMapping("/update-universite")
    @Operation(description = "modifier une université")
    @ResponseBody
    public UniversiteDTO updateUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setNomUniv(universiteDTO.getName());
        universite.setAdresse(universiteDTO.getLocation());
        Universite updatedUniversite = universiteService.updateUniversite(universite);
        UniversiteDTO updatedUniversiteDTO = new UniversiteDTO();
        updatedUniversiteDTO.setName(updatedUniversite.getNomUniv());
        updatedUniversiteDTO.setLocation(updatedUniversite.getAdresse());
        return updatedUniversiteDTO;
    }

    // http://localhost:8089/Kaddem/universite/assignUniversiteToDepartement/1/1
    @PutMapping("/assignUniversiteToDepartement/{universiteId}/{departementId}")
    @Operation(description = "assigner une université à un département")
    @ResponseBody
    public void assignUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId, @PathVariable("departementId") Integer departementId) {
        universiteService.assignUniversiteToDepartement(universiteId, departementId);
    }
}
