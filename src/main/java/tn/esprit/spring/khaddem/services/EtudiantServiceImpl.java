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
    public Etudiant updateEtudiant(Etudiant updatedEtudiant) {
        Integer idEtudiant = updatedEtudiant.getIdEtudiant();
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);

        if (optionalEtudiant.isPresent()) {
            Etudiant existingEtudiant = optionalEtudiant.get();
            existingEtudiant.setPrenomE(updatedEtudiant.getPrenomE());
            existingEtudiant.setNomE(updatedEtudiant.getNomE());
            existingEtudiant.setOp(updatedEtudiant.getOp());
            etudiantRepository.save(existingEtudiant);

            return existingEtudiant;
        } else {
            throw new NoSuchElementException("Etudiant not found with ID: " + idEtudiant);
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





}
