package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.repositories.ContratRepository;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{

    EtudiantRepository etudiantRepository;

    DepartementRepository departementRepository;

    ContratRepository contratRepository;

    EquipeRepository equipeRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        if (etudiantRepository.existsById(e.getIdEtudiant())) {
            etudiantRepository.save(e);
            return e;
        } else {
            return etudiantRepository.save(e);
        }
    }


    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);
        if (optionalEtudiant.isPresent()) {
            return optionalEtudiant.get();
        } else {
            // Handle the case where the student doesn't exist, e.g., throw an exception
            throw new EntityNotFoundException("Student with ID " + idEtudiant + " not found");
        }
    }


    @Override
    public void removeEtudiant(Integer idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);
        Optional<Departement> optionalDepartement = departementRepository.findById(departementId);

        if (optionalEtudiant.isPresent() && optionalDepartement.isPresent()) {
            Etudiant e = optionalEtudiant.get();
            Departement d = optionalDepartement.get();
            e.setDepartement(d);
            etudiantRepository.save(e);
        } else {
            // Handle the case where either the student or the department doesn't exist
            // You can choose to throw an exception or log an error message here
            throw new EntityNotFoundException("Student or Department not found");
        }
    }

    @Override
    public List<Etudiant> findByDepartementIdDepartement(Integer idDepartement) {
        return etudiantRepository.findByDepartementIdDepartement(idDepartement);
    }

    @Override
    public List<Etudiant> findByEquipesNiveau(Niveau niveau) {
        return etudiantRepository.findByEquipesNiveau(niveau);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(Specialite specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(String specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
        Optional<Contrat> contratOptional = contratRepository.findById(idContrat);
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);

        if (contratOptional.isPresent() && equipeOptional.isPresent()) {
            Contrat contrat = contratOptional.get();
            Equipe equipe = equipeOptional.get();
            Etudiant etudiant = etudiantRepository.save(e);
            log.info("contrat: " + contrat.getSpecialite());
            log.info("equipe: " + equipe.getNomEquipe());
            log.info("etudiant: " + etudiant.getNomE() + " " + etudiant.getPrenomE() + " " + etudiant.getOp());
            List<Equipe> equipesMisesAjour = new ArrayList<>();
            contrat.setEtudiant(etudiant);
            if (etudiant.getEquipes() != null) {
                equipesMisesAjour = etudiant.getEquipes();
            }
            equipesMisesAjour.add(equipe);
            log.info("taille apres ajout : " + equipesMisesAjour.size());
            etudiant.setEquipes(equipesMisesAjour);

            return e;
        } else {
            // Handle the case where the Optional is empty (not found in the repository).
            // You can throw an exception or return a default value, depending on your use case.
            throw new NoSuchElementException("Contrat or Equipe not found");
        }
    }


    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepartement);

        if (optionalDepartement.isPresent()) {
            Departement departement = optionalDepartement.get();
            return departement.getEtudiants();
        } else {
            // Handle the case where the department doesn't exist
            // You can choose to throw an exception or return an empty list, or handle it as needed
            throw new EntityNotFoundException("Department not found");
        }
    }



}
