package tn.esprit.spring.khaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import java.util.List;

@RestController
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

    // http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-etudiant/{etudiantId}")
    @ResponseBody
    public Etudiant retrieveContrat(@PathVariable("etudiantId") Integer etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
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
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant()); // Assuming you have an ID field in the DTO
        etudiant.setPrenomE(etudiantDTO.getPrenomE());
        etudiant.setNomE(etudiantDTO.getNomE());
        etudiant.setOp(etudiantDTO.getOp());

        return etudiantService.updateEtudiant(etudiant);
    }
    // http://localhost:8089/Kaddem/etudiant/removeEtudiant
    @DeleteMapping("/removeEtudiant/{idEtudiant}")
    @ResponseBody
    public void removeEtudiant(@PathVariable("idEtudiant") Integer idEtudiant) {
        etudiantService.removeEtudiant(idEtudiant);
    }

    // http://localhost:8089/Kaddem/etudiant/assignEtudiantToDepartement/1/1
    @PutMapping("/assignEtudiantToDepartement/{etudiantId}/{departementId}")
    @ResponseBody
    public void assignEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId
            ,@PathVariable("departementId") Integer departementId) {
        etudiantService.assignEtudiantToDepartement(etudiantId,departementId);
    }

    // http://localhost:8089/Kaddem/etudiant/findByDepartement/1
    @GetMapping("/findByDepartement/{departement-id}")
    @ResponseBody
    public List<Etudiant> findByDepartement(@PathVariable("departement-id") Integer departementId) {
        return etudiantService.findByDepartementIdDepartement(departementId);
    }

    // http://localhost:8089/Kaddem/etudiant/findByEquipesNiveau/JUNIOR
    @GetMapping("/findByEquipesNiveau/{niveau}")
    @ResponseBody
    public List<Etudiant> findByEquipesNiveau(@PathVariable("niveau") Niveau niveau) {
        return etudiantService.findByEquipesNiveau(niveau);
    }

    // http://localhost:8089/Kaddem/etudiant/retrieveEtudiantsByContratSpecialite/SECURITE
    @GetMapping("/retrieveEtudiantsByContratSpecialite/{specialite}")
    @ResponseBody
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(@PathVariable("specialite") Specialite specialite) {
        return etudiantService.retrieveEtudiantsByContratSpecialite(specialite);
    }

    // http://localhost:8089/Kaddem/etudiant/retrieveEtudiantsByContratSpecialiteSQL/SECURITE
    @GetMapping("/retrieveEtudiantsByContratSpecialiteSQL/{specialite}")
    @ResponseBody
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(@PathVariable("specialite") String specialite) {
        return etudiantService.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

    // http://localhost:8089/Kaddem/etudiant/addAndAssignEtudiantToEquipeAndContract/1/1
    @PostMapping("/addAndAssignEtudiantToEquipeAndContract/{equipeId}/{contratId}")
    @ResponseBody
    public void addAndAssignEtudiantToEquipeAndContract(@RequestBody EtudiantDTO etudiantDTO,@PathVariable("contratId") Integer contratId,@PathVariable("equipeId") Integer equipeId) {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant()); // Assuming you have an ID field in the DTO
        etudiant.setPrenomE(etudiantDTO.getPrenomE());
        etudiant.setNomE(etudiantDTO.getNomE());
        etudiant.setOp(etudiantDTO.getOp());
        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant,contratId,equipeId);
    }

    // http://localhost:8089/Kaddem/etudiant/getEtudiantsByDepartement/1
    @GetMapping("/getEtudiantsByDepartement/{idDepartement}")
    @ResponseBody
    public List<Etudiant> getEtudiantsByDepartement(@PathVariable("idDepartement") Integer idDepartement) {
        return etudiantService.getEtudiantsByDepartement(idDepartement);
    }
}
