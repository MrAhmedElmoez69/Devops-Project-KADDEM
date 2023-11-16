package tn.esprit.spring.khaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/etudiant")

public class EtudiantRestController {

    @Autowired
    IEtudiantService etudiantService;

    // http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
    @GetMapping("/retrieve-all-etudiants")
    @ResponseBody
    public List<Etudiant> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    // http://localhost:8089/Kaddem/etudiant/add-etudiant
    @PostMapping("/add-etudiant")
    @ResponseBody
    public Etudiant addEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
        etudiant.setPrenomE(etudiantDTO.getPrenomE());
        etudiant.setNomE(etudiantDTO.getNomE());
        etudiant.setOp(etudiantDTO.getOp());

        return etudiantService.addEtudiant(etudiant);
    }

    // http://localhost:8089/Kaddem/etudiant/update-etudiant
    @PutMapping("/update-etudiant")
    @ResponseBody
    public Etudiant updateEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        Etudiant equipe = etudiantService.retrieveEtudiant(etudiantDTO.getIdEtudiant());

        if (equipe != null) {
            equipe.setNomE(etudiantDTO.getNomE());
            return etudiantService.updateEtudiant(equipe);
        } else {
            return null;
        }
    }

    // http://localhost:8089/Kaddem/etudiant/removeEtudiant
    @DeleteMapping("/removeEtudiant/{idEtudiant}")
    @ResponseBody
    public void removeEtudiant(@PathVariable("idEtudiant") Integer idEtudiant) {
        etudiantService.removeEtudiant(idEtudiant);
    }



}
