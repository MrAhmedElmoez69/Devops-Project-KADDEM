package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.services.IUniversiteService;

import java.util.List;
@RestController
@RequestMapping("/universite")
public class UniversiteRestController {
    @Autowired
    IUniversiteService universiteService;
    @Autowired
    public UniversiteRestController(IUniversiteService universiteService) {
        this.universiteService = universiteService;
    }
    // http://localhost:8089/Kaddem/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
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
    @ResponseBody
    public Universite addUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setNomUniv(universiteDTO.getNomUniv());
        universite.setAdresse(universiteDTO.getAdresse());
        universiteService.addUniversite(universite);
        return universite;
    }


    // http://localhost:8089/Kaddem/universite/update-universite
    @PutMapping("/update-universite")
    @Operation(description = "modifier une université")
    @ResponseBody
    public UniversiteDTO updateUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setIdUniversite(universiteDTO.getIdUniversite());
        universite.setNomUniv(universiteDTO.getNomUniv());
        universite.setAdresse(universiteDTO.getAdresse());
        UniversiteDTO updatedUniversite = universiteService.updateUniversite(universiteDTO);
        UniversiteDTO updatedUniversiteDTO = new UniversiteDTO();
        updatedUniversiteDTO.setIdUniversite(updatedUniversite.getIdUniversite());
        updatedUniversiteDTO.setNomUniv(updatedUniversite.getNomUniv());
        updatedUniversiteDTO.setAdresse(updatedUniversite.getAdresse());
        return updatedUniversiteDTO;
    }

    // http://localhost:8089/Kaddem/universite/assignUniversiteToDepartement/1/1

}
