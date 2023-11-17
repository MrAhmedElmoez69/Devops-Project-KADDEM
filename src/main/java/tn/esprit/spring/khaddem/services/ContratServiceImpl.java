package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import javax.transaction.Transactional;
import java.util.Date;
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
    public Contrat updateContrat(Contrat ce) {
        log.info("debut methode updateContrat");
        contratRepository.save(ce);
        return ce;
    }

    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        log.info("debut methode retrieveContrat");
        return contratRepository.findById(idContrat).orElse(null);
    }


    @Override
    public void removeContrat(Integer idContrat) {
        log.info("debut methode removeContrat");
        contratRepository.deleteById(idContrat);
    }

    @Override
    public Contrat addContrat(Contrat c) {
        // start date t1
        contratRepository.save(c);


        return c;
    }

    @Transactional
    public Contrat addAndAffectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        Long startDate = new Date().getTime();
        log.info("startDate: "+startDate);
        log.info("debut methode addAndAffectContratToEtudiant");
        Etudiant etudiant=etudiantRepository.findByNomEAndPrenomE(nomE,prenomE);
        log.info("etudiant: "+etudiant.getNomE()+" "+etudiant.getPrenomE());
        // nb contrats actifs
        Integer nbContratsActifs= etudiant.getContrats().size();
        if(nbContratsActifs>5) {
            log.info("nombre de contrats autorisés est atteint");
            Long endDate = new Date().getTime();
            Long executionTime = endDate-startDate;
            log.info("endDate: "+startDate);
            log.info("executionTime: "+executionTime+ " ms");
            return ce;
        }
        log.info("nb Contrats en cours: "+nbContratsActifs);
        contratRepository.save(ce);
        ce.setEtudiant(etudiant);
        log.info("fin methode addAndAffectContratToEtudiant");
        Long endDate = new Date().getTime();
        Long executionTime = endDate-startDate;

        log.info("endDate: "+startDate);
        log.info("executionTime: "+executionTime+ " ms");

        return ce;
    }

    public 	Integer nbContratsValides(Date startDate, Date endDate){
        return contratRepository.getnbContratsValides(startDate, endDate);
    }

    public void retrieveAndUpdateStatusContrat(){
        log.info("debut methode retrieveAndUpdateStatusContrat");
        List<Contrat>contrats=contratRepository.findAll();
        log.info("total contrats :"+contrats.size());

        for (Contrat contrat : contrats) {
            log.info("id: "+contrat.getIdContrat());
            log.info("date fin"+contrat.getDateFinContrat());
            log.info("archived "+contrat.getArchived());

            Date dateSysteme = new Date();

            if (contrat.getArchived() == null || !contrat.getArchived()) {
                long timeDifference = contrat.getDateFinContrat().getTime()-dateSysteme.getTime();
                long daysDifference= (timeDifference / (1000 * 60 * 60 * 24)) % 365;
                log.info("difference in days : "+daysDifference);
                if (daysDifference==15){  // pour 15 jours exactement
                    log.info(" Contrat Commencant le : " + contrat.getDateDebutContrat()+"pour l'etudiant "+contrat.getEtudiant().getNomE()+
                            " "+contrat.getEtudiant().getPrenomE()+"  va bientot s achever le "
                            +contrat.getDateFinContrat());
                }
                if (daysDifference==0) {
                    log.info("jour j: " + contrat.getIdContrat());
                    contrat.setArchived(true);
                    contratRepository.save(contrat);
                }
            }

            log.info("debut methode retrieveAndUpdateStatusContrat");
        }
    }


}