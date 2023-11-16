package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;

import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
;
import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class ContratServiceImpl implements  IContratService{



    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        log.info("debut methode retrieveAllContrats");
        return contratRepository.findAll();
    }


    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        log.info("debut methode retrieveContrat");
        return contratRepository.findById(idContrat).orElse(null);
    }



    @Override
    public Contrat addContrat(Contrat c) {
        // start date t1
        contratRepository.save(c);


        return c;
    }








}
