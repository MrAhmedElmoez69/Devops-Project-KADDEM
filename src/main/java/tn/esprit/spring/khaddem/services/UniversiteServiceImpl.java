package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UniversiteServiceImpl implements IUniversiteService {

    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    DepartementRepository departementRepository;


    public void setUniversiteRepository(UniversiteRepository universiteRepository) {
        this.universiteRepository = universiteRepository;
    }

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite updateUniversite(Universite updatedUniversite) {
        String nomUniv = updatedUniversite.getNomUniv();
        Optional<Universite> optionalUniversite = universiteRepository.findByNomUniv(nomUniv);

        if (optionalUniversite.isPresent()) {
            Universite existingUniversite = optionalUniversite.get();
            existingUniversite.setNomUniv(updatedUniversite.getNomUniv());
            existingUniversite.setAdresse(updatedUniversite.getAdresse());
            universiteRepository.save(existingUniversite);
            return existingUniversite;
        } else {
            throw new NoSuchElementException("Universite not found with NomUniv: " + nomUniv);
        }
    }


    @Override
    public Universite retrieveUniversite(Integer idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);

    }
    @Override
    public void removeUniversite(Integer idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }
    @Transactional
    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(universiteId);
        Optional<Departement> optionalDepartement = departementRepository.findById(departementId);

        if (optionalUniversite.isPresent() && optionalDepartement.isPresent()) {
            Universite universite = optionalUniversite.get();
            Departement departement = optionalDepartement.get();
            universite.getDepartements().add(departement);
            // You may want to save the changes to the entities or handle it accordingly
        } else {
            throw new NoSuchElementException("Universite or Departement not found with IDs: " + universiteId + ", " + departementId);
        }
    }

}